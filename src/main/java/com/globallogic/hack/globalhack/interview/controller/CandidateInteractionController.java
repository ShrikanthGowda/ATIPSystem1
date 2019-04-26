package com.globallogic.hack.globalhack.interview.controller;

import com.globallogic.hack.globalhack.interview.dao.InterviewRecordRepository;
import com.globallogic.hack.globalhack.interview.dao.InterviewRequestRepository;
import com.globallogic.hack.globalhack.interview.model.*;
import com.globallogic.hack.globalhack.interview.record.InterviewRecord;
import com.globallogic.hack.globalhack.interview.service.impl.CandidateInteractionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

@Controller
public class CandidateInteractionController {

  @Autowired
  CandidateInteractionServiceImpl candidateInteractionService;

  @Autowired
  InterviewRequestRepository interviewRequestRepository;

  @Autowired
  InterviewRecordRepository interviewRecordRepository;

  @RequestMapping(value = "/interview/{candidateId}/{candidateName}/{subject}", method = RequestMethod.POST, produces = "application/xml")
  @ResponseBody
  public Response greetCandidate(@PathVariable(name = "candidateId") Integer candidateId, @PathVariable(name = "candidateName") String candidateName ,@PathVariable(name = "subject") String subject)
          throws Exception{

    Response response = candidateInteractionService.greetCandidate(subject, candidateName, candidateId);
    return response;
  }

  @RequestMapping(value = "/interviewQuestion/{subject}/{order}/{candidateId}/{questionId}", method = RequestMethod.POST, produces = "application/xml")
  @ResponseBody
  public Response questionnaires(@PathVariable(name = "subject") String subject, @PathVariable(name = "order") Integer order,
  @PathVariable("candidateId") Integer candidateId ,@PathVariable("questionId") Integer questionId, @RequestParam("RecordingSid") String recordingId) throws Exception {

    Optional<InterviewRequest> interviewRequest = interviewRequestRepository.findById(Integer.valueOf(candidateId));
    InterviewRequest request = interviewRequest.get();

    InterviewRecord interviewRecord = new InterviewRecord();
    interviewRecord.setCandidateId(request.getId());
    String ServerPath = "/root/darshan/"+recordingId+".wav";
    downloadWithJavaNIO("https://api.twilio.com/2010-04-01/Accounts/AC21eeaf9d83cf9ddb8f325fb434122dec/Recordings/"+recordingId,ServerPath);
    interviewRecord.setURL(ServerPath);
    interviewRecord.setQuestionId(questionId);
    interviewRecordRepository.save(interviewRecord);
    Response response = candidateInteractionService.question(subject, order, candidateId);
    return response;
  }

  @RequestMapping(value = "/interview/hangup/{candidateId}/{questionId}", method = RequestMethod.POST, produces = "application/xml")
  @ResponseBody
  public HangupResponse hangup(@PathVariable("candidateId") Integer candidateId,@RequestParam("RecordingSid") String recordingId,@PathVariable("questionId") Integer questionId) throws Exception {
    Optional<InterviewRequest> interviewRequest = interviewRequestRepository.findById(Integer.valueOf(candidateId));

    InterviewRequest request = interviewRequest.get();
    InterviewRecord interviewRecord = new InterviewRecord();
    interviewRecord.setCandidateId(request.getId());
    String ServerPath = "/root/darshan/"+recordingId+".wav";
    downloadWithJavaNIO("https://api.twilio.com/2010-04-01/Accounts/AC21eeaf9d83cf9ddb8f325fb434122dec/Recordings/"+recordingId,ServerPath);
    interviewRecord.setURL(ServerPath);
    interviewRecord.setQuestionId(questionId);
    interviewRecordRepository.save(interviewRecord);
    request.setStatus("INT-RECORDING_DONE");
    interviewRequestRepository.save(request);
    HangupResponse response = candidateInteractionService.hangup();
    return response;
  }

  public  void downloadWithJavaNIO(String fileURL, String localFilename) throws IOException {
    URL url = new URL(fileURL);
    try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(localFilename); FileChannel fileChannel = fileOutputStream.getChannel()) {
        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        fileOutputStream.close();
    }
  }

}