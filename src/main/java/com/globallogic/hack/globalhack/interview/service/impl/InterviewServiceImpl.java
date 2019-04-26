package com.globallogic.hack.globalhack.interview.service.impl;

import com.globallogic.hack.globalhack.interview.dao.InterviewRequestRepository;
import com.globallogic.hack.globalhack.interview.model.InterviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class InterviewServiceImpl {

  @Autowired
  InterviewRequestRepository interviewRequestRepository;

  @Transactional
  public InterviewRequest saveInterviewDetail(InterviewRequest interviewRequest) {
  InterviewRequest interviewRequest1 = interviewRequestRepository.save(interviewRequest);
    return interviewRequest1;
  }
}
