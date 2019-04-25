package com.globallogic.hack.globalhack.interview.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InterviewRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String candidateName;

  private char subject;

  private Date interview_date;

  private long phoneNumber;

  private String status ="Pending";

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCandidateName() {
    return candidateName;
  }

  public void setCandidateName(String candidateName) {
    this.candidateName = candidateName;
  }

  public char getSubject() {
    return subject;
  }

  public void setSubject(char subject) {
    this.subject = subject;
  }

  public Date getInterview_date() {
    return interview_date;
  }

  public void setInterview_date(Date interview_date) {
    this.interview_date = interview_date;
  }

  public long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
