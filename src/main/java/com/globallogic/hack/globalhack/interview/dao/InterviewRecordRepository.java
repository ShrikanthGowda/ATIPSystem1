package com.globallogic.hack.globalhack.interview.dao;

import com.globallogic.hack.globalhack.interview.record.InterviewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterviewRecordRepository extends JpaRepository<InterviewRecord, Integer> {

  @Query("Select i from InterviewRecord i where i.candidateId=:candidateId ")
  List<InterviewRecord> findAllByCandidateId(@Param("candidateId") Integer candidateId);
}
