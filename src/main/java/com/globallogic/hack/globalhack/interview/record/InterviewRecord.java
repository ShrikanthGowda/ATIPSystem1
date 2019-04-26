package com.globallogic.hack.globalhack.interview.record;

import com.globallogic.hack.globalhack.interview.model.InterviewRequest;

import javax.persistence.*;

@Entity
public class InterviewRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String URL;

  private int questionId;

  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

 /* @ManyToOne
  @JoinColumn*/
  private Integer candidateId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getURL() {
    return URL;
  }

  public void setURL(String URL) {
    this.URL = URL;
  }

  public Integer getCandidateId() {
    return candidateId;
  }

  public void setCandidateId(Integer candidateId) {
    this.candidateId = candidateId;
  }
}
