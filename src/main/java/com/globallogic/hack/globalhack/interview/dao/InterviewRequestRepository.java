package com.globallogic.hack.globalhack.interview.dao;

import com.globallogic.hack.globalhack.interview.model.InterviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, Integer> {

  @Query("from  InterviewRequest where interviewDate < :NOW and status='pending' ")
  Set<InterviewRequest> findByTodayInterview( @Param("NOW")Date now);

  @Query("update  InterviewRequest set status= :STATUS where id= :candidateId ")
  void updateInterviewStatus(@Param("STATUS") String status,@Param("candidateId") Integer candidateId);

  @Query("Select r from InterviewRequest r  where r.phoneNumber = :phoneNumber")
  InterviewRequest findByPhoneNumber(@Param("phoneNumber") Long phoneNumber);

  @Query("Select r from InterviewRequest r  where r.status = :status")
  List<InterviewRequest> findByStatus(@Param("status") String status);
}
