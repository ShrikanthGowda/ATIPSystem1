package com.globallogic.hack.interview.dao.impl;

import com.globallogic.hack.interview.dao.InterviewDao;
import com.globallogic.hacks.interview.model.InterviewRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class InterviewDaoImpl implements InterviewDao {

  @Autowired
  SessionFactory sessionFactory;

  @Override
  public Integer saveInterviewDetail(InterviewRequest interviewRequest) {
    Session session = sessionFactory.getCurrentSession();
    Integer interviewId  = (Integer)session.save(interviewRequest);
    return interviewId;
  }
}
