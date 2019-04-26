package com.globallogic.hack.globalhack.interview.dao;

import com.globallogic.hack.globalhack.interview.record.InterviewRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRecordRepository extends JpaRepository<InterviewRecord, Integer> {
}
