package com.globallogic.hack.globalhack.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.hack.globalhack.interview.dao.InterviewQuestionnairesRepository;
import com.globallogic.hack.globalhack.interview.dao.InterviewRecordRepository;
import com.globallogic.hack.globalhack.interview.dao.InterviewRequestRepository;
import com.globallogic.hack.globalhack.interview.model.InterviewRequest;
import com.globallogic.hack.globalhack.interview.model.ScoreResponseDTO;
import com.globallogic.hack.globalhack.interview.model.TranscribedDTO;
import com.globallogic.hack.globalhack.interview.record.InterviewRecord;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class scheduler {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  OkHttpClient okHttpClient;

  public static final  String ACCOUNT_SID = "###ACCOUNT_SID###";


  public static final String AUTH_TOKEN = "###AUTH_TOKEN###";

  @Value("${server.port}")
  private String serverPort;//="9090";

  @Autowired
  InterviewRequestRepository repository;

  @Autowired
  InterviewRecordRepository recordRepository;

  @Autowired
  InterviewQuestionnairesRepository questionnairesRepository;

  @Scheduled(initialDelay = 10000, fixedRate = 10000)
  public void getScheduledInterview() throws Exception {
    Set<InterviewRequest> interviewRequests = repository.findByTodayInterview(new Date());
    for (InterviewRequest request : interviewRequests) {
      callToCandidate(request);
      request.setStatus("INT-ONGOING");
      repository.save(request);
    }
  }

  public void callToCandidate(InterviewRequest request) throws Exception {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    String from = "+17752776776";//"+12015290698";
    Call.creator(new PhoneNumber(String.valueOf(request.getPhoneNumber())), new PhoneNumber(from), new URI("http://134.209.153.28:"+serverPort+"/interview/"+request.getId()+"/"+request.getSubject())).create();
  }


  @Scheduled(initialDelay = 20000, fixedRate = 20000)
  public void getRecordsToTranscribe() throws Exception{
    List<TranscribedDTO> transcribedDTOS = new ArrayList<>();
     List<InterviewRequest> interviewRequests = repository.findByStatus("INT-RECORDING_DONE");
     for(InterviewRequest interviewRequest: interviewRequests) {
       if (interviewRequest != null) {
         interviewRequest.setStatus("INT-TRANSCRIBE-PROGRESS");
         Integer requestId = interviewRequest.getId();
         System.out.println("Before saving the request");
         repository.save(interviewRequest);
         System.out.println("After saving the request");
         List<InterviewRecord> interviewRecords = recordRepository.findAllByCandidateId(requestId);
         for (InterviewRecord record : interviewRecords) {
           System.out.println("In Loop");
           String transcribedScript = transcribeData(record.getURL());

           String modelAnswer = questionnairesRepository.findModelAnswer(record.getQuestionId());
           TranscribedDTO transcribedDTO = new TranscribedDTO(modelAnswer, transcribedScript);
           transcribedDTOS.add(transcribedDTO);

         }
         Integer score = getScore(transcribedDTOS);
         notifyScore(interviewRequest.getId(), score);
       }
     }

  }

  public String transcribeData(String audioURL) throws Exception {
    StringBuilder builder = new StringBuilder();

    try (SpeechClient speech = SpeechClient.create()) {
      Path path = Paths.get(audioURL);
      byte[] data = Files.readAllBytes(path);
      ByteString audioBytes = ByteString.copyFrom(data);

      // Configure request with local raw PCM audio
      RecognitionConfig config =
          RecognitionConfig.newBuilder()
              .setLanguageCode("en-IN")
              .build();
      RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

      // Use blocking call to get audio transcript
      RecognizeResponse response = speech.recognize(config, audio);
      List<SpeechRecognitionResult> results = response.getResultsList();
      for (SpeechRecognitionResult result : results) {
        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
        builder.append(alternative.getTranscript());
      }
    }

    return builder.toString();
  }


public Integer getScore(List<TranscribedDTO> transcribedDTOS) throws Exception{
 MediaType JSON = MediaType.get("application/json; charset=utf-8");
 String jsonValue = objectMapper.writeValueAsString(transcribedDTOS);
  RequestBody requestBody = RequestBody.create(JSON,jsonValue);
  final Request request = new Request.Builder()
      .url("http://localhost:8555/calculate-score")
      .post(requestBody)
      .build();

  System.out.println("Printing the json:"+jsonValue);
  final Response response = okHttpClient.newCall(request).execute();
  String responseString = response.body().string();

  ScoreResponseDTO scoreResponseDTO = objectMapper.readValue(responseString, ScoreResponseDTO.class);
  System.out.println("Printing score"+scoreResponseDTO);
  return scoreResponseDTO.getPayload().getScore();
}




  public void notifyScore(Integer interviewId, Integer scoreId) throws Exception{

    MediaType JSON = MediaType.get("application/json; charset=utf-8");
    RequestBody requestBody = RequestBody.create(JSON,"{}");
    final Request request = new Request.Builder()
        .url("http://localhost:3000/interview/updateScore?int_id="+interviewId+"&score="+scoreId)
        .put(requestBody)
        .build();

    okHttpClient.newCall(request).execute();

  }

}