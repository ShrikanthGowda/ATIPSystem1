package com.globallogic.hack.globalhack.interview.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "interview_questionnaires")
public class InterviewQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "question_id")
  private int questionId;

  @NotNull
  @Column(name = "question", nullable = false)
  private String question;

  @Column(name = "question_order", nullable = false)
  private int questionOrder;

  @NotNull
  @Column(name = "subject", nullable = false)
  private String subject;

  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public int getQuestionOrder() {
    return questionOrder;
  }

  public void setQuestionOrder(int questionOrder) {
    this.questionOrder = questionOrder;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }
}
