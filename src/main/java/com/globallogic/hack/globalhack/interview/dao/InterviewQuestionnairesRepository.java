package com.globallogic.hack.globalhack.interview.dao;

import com.globallogic.hack.globalhack.interview.model.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterviewQuestionnairesRepository extends JpaRepository<InterviewQuestion, Integer> {

  @Query("SELECT MIN(i.questionOrder) FROM InterviewQuestion i WHERE i.subject = :subject")
  Integer findMinOrder(@Param("subject") String subject);

  @Query("SELECT i FROM InterviewQuestion i WHERE i.subject = :subject AND i.questionOrder = :order")
  InterviewQuestion findQuestion(@Param("subject") String subject, @Param("order") Integer order);


}
