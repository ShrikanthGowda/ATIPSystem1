package com.globallogic.hack.interview.controller;

import com.globallogic.hack.interview.service.InterviewService;
import com.globallogic.hacks.interview.model.InterviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InterviewRequestController {

  @Autowired
  private InterviewService interviewService;

  @RequestMapping(value = "/interview", method = RequestMethod.POST)
  public ResponseEntity<String> saveInterviewDetail(@RequestBody InterviewRequest interviewRequest){
    Integer interviewId = interviewService.saveInterviewDetail(interviewRequest);
    return new ResponseEntity<String>(String.valueOf(interviewId), HttpStatus.CREATED);
  }
}