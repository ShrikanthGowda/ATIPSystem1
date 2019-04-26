package com.globallogic.hack.globalhack.interview;

import com.globallogic.hack.globalhack.interview.dao.InterviewQuestionnairesRepository;
import com.globallogic.hack.globalhack.interview.dao.InterviewRecordRepository;
import com.globallogic.hack.globalhack.interview.dao.InterviewRequestRepository;
import com.globallogic.hack.globalhack.interview.model.InterviewRequest;
import com.globallogic.hack.globalhack.interview.model.TranscribedDTO;
import com.globallogic.hack.globalhack.interview.record.InterviewRecord;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
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


  public static final  String ACCOUNT_SID = "AC21eeaf9d83cf9ddb8f325fb434122dec";


  public static final String AUTH_TOKEN = "d44d7795d4c93457deec1b045ff00b98";

  //@Value("${server.port}")
  private String serverPort="6090";

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
    String from = "+12015290698";
    Call.creator(new PhoneNumber(String.valueOf(request.getPhoneNumber())), new PhoneNumber(from), new URI("http://68.183.92.11:"+serverPort+"/interview/"+request.getId()+"/"+request.getCandidateName()+"/"+request.getSubject())).create();
  }


  @Scheduled(initialDelay = 20000, fixedRate = 20000)
  public void getRecordsToTranscribe() throws Exception{
    List<TranscribedDTO> transcribedDTOS = new ArrayList<>();
     InterviewRequest interviewRequest = repository.findByStatus("INT-RECORDING_DONE");
     if(interviewRequest!=null) {
       interviewRequest.setStatus("INT-TRANSCRIBE-PROGRESS");
      Integer requestId =  interviewRequest.getId();
      System.out.println("Before saving the request");
       repository.save(interviewRequest);
       System.out.println("After saving the request");
       List<InterviewRecord> interviewRecords = recordRepository.findAllByCandidateId(requestId);
       for (InterviewRecord record : interviewRecords) {
         System.out.println("In Loop");
       //  String transcribedScript = transcribeData(record.getURL());

         String modelAnswer = questionnairesRepository.findModelAnswer(record.getQuestionId());
         //TranscribedDTO transcribedDTO = new TranscribedDTO(modelAnswer, transcribedScript);
         //transcribedDTOS.add(transcribedDTO);
//
       }
       for (TranscribedDTO transcribedDTO : transcribedDTOS) {
         System.out.println("Model Answer :" + transcribedDTO.getModelAnswer());
         System.out.println("Transcribed Answer :" + transcribedDTO.getTranscribedAnswer());

       }
     }

  }

  public static void main(String[] args) throws Exception {
    StringBuilder builder = new StringBuilder();

    try (SpeechClient speech = SpeechClient.create()) {
      Path path = Paths.get("/Users/darshan/Desktop/sample_audio_one.wav");
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

   // return builder.toString();
  }






}