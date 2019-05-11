package com.globallogic.hack.globalhack.interview.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class InterviewRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String candidateName;

  private char subject;

  private Timestamp interviewDate;

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

  public Timestamp getInterviewDate() {
    return interviewDate;
  }

  public void setInterviewDate(Timestamp interviewDate) {
    System.out.println("*********1 "+interviewDate);
    new Date();
    this.interviewDate = interviewDate;
  }

  public long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
