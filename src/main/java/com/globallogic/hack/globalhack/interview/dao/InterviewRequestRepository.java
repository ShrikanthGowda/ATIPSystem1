package com.globallogic.hack.globalhack.interview.dao;

import com.globallogic.hack.globalhack.interview.model.InterviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, Integer> {
}
