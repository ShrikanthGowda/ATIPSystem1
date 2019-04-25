package com.globallogic.hack.globalhack.interview.controller;

import com.globallogic.hack.globalhack.interview.service.impl.InterviewServiceImpl;
import com.globallogic.hack.globalhack.interview.model.InterviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterviewRequestController {

  @Autowired
  private InterviewServiceImpl interviewService;

  @RequestMapping(value = "/interview", method = RequestMethod.POST)
  public ResponseEntity<String> saveInterviewDetail(@RequestBody InterviewRequest interviewRequest){
    InterviewRequest interviewRequest1 = interviewService.saveInterviewDetail(interviewRequest);
    return new ResponseEntity<String>(String.valueOf(interviewRequest1.getId()), HttpStatus.CREATED);
  }
}