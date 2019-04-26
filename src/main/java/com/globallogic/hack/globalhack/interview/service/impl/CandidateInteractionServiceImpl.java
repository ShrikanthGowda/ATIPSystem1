package com.globallogic.hack.globalhack.interview.service.impl;

import com.globallogic.hack.globalhack.interview.dao.InterviewQuestionnairesRepository;
import com.globallogic.hack.globalhack.interview.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateInteractionServiceImpl {

  @Autowired
  InterviewQuestionnairesRepository interviewQuestionnairesRepository;


  String voice = "woman";
  Integer timeOut = 5;
  Boolean transcribe = true;



  public Response greetCandidate(String subject, String candidateName, Integer candidateId) {

    Say say = new Say();
    Record record = new Record();
    Response response = new Response();

    Integer minOrder = interviewQuestionnairesRepository.findMinOrder(subject);

    InterviewQuestion interviewQuestion = interviewQuestionnairesRepository.findQuestion(subject, minOrder);
    InterviewQuestion nextInterviewQuestion = interviewQuestionnairesRepository.findQuestion(subject, minOrder+1);


    say.setVoice(voice);

    record.setTimeout(timeOut);
    if (minOrder != null) {
      say.setContent(
          "Hello " + candidateName + " hope you  remember your interview have been scheduled with Global Logic" + "all the best for your interview and here it starts" + interviewQuestion.getQuestion());
    }
    else{
        say.setContent("Hello " + candidateName + " hope you remember your interview have been scheduled with Global Logic"
            + "all the best for your interview and here it starts");
      }
      if (nextInterviewQuestion != null) {
        record.setAction("/interviewQuestion/" + subject + "/" +(++minOrder)+"/"+candidateId+"/"+interviewQuestion.getQuestionId());
    }
    else {
      record.setAction("/interview/hangup");
    }

    record.setTranscribe(transcribe);

    response.setSay(say);
    response.setRecord(record);

    return response;
  }

  public Response question(String subject, Integer order, Integer candidateId) {
    Say say = new Say();
    Record record = new Record();
    Response response = new Response();

    InterviewQuestion interviewQuestion = interviewQuestionnairesRepository.findQuestion(subject, order);
    InterviewQuestion nextInterviewQuestion = interviewQuestionnairesRepository.findQuestion(subject, order+1);

    say.setContent(interviewQuestion.getQuestion());
    say.setVoice(voice);

    record.setTimeout(timeOut);
    if(nextInterviewQuestion != null) {
      record.setAction("/interviewQuestion/" + subject + "/" + nextInterviewQuestion.getQuestionOrder()+"/"+candidateId+"/"+interviewQuestion.getQuestionId());
    }
    else {
      record.setAction("/interview/hangup/"+candidateId+"/"+interviewQuestion.getQuestionId());
    }
    record.setTranscribe(transcribe);

    response.setSay(say);
    response.setRecord(record);
    return response;
  }

  public HangupResponse hangup() {
    Say say = new Say();
    Hangup hangup = new Hangup();
    HangupResponse response = new HangupResponse();

    say.setContent("Thanks , for your time. We will reach you shorltly ");
    say.setVoice(voice);

    response.setSay(say);
    response.setHangup(hangup);

    return response;
  }
}
