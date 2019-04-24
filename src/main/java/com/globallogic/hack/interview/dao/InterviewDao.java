package com.globallogic.hack.interview.dao;

import com.globallogic.hacks.interview.model.InterviewRequest;

public interface InterviewDao {
   Integer saveInterviewDetail(InterviewRequest interviewRequest);
}
