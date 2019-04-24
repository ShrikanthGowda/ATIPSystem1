package com.globallogic.hack.interview.service.impl;

import com.globallogic.hack.interview.dao.InterviewDao;
import com.globallogic.hack.interview.service.InterviewService;
import com.globallogic.hacks.interview.model.InterviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InterviewServiceImpl implements InterviewService {

  @Autowired
  private InterviewDao interviewDao;

  @Override
  @Transactional
  public Integer saveInterviewDetail(InterviewRequest interviewRequest) {
    Integer interviewId = interviewDao.saveInterviewDetail(interviewRequest);
    return interviewId;
  }
}
