package com.globallogic.hack.globalhack.interview.model;

import javax.persistence.*;
import java.util.Date;

public class InterviewRequestDTO {

    private String candidateName;

    private char subject;

    private String interviewDate;

    private long phoneNumber;

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

  public String getInterviewDate() {
    return interviewDate;
  }

  public void setInterviewDate(String interviewDate) {
    this.interviewDate = interviewDate;
  }

  public long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override public String toString() {
    return "InterviewRequestDTO{" + "candidateName='" + candidateName + '\'' + ", subject=" + subject
        + ", interviewDate='" + interviewDate + '\'' + ", phoneNumber=" + phoneNumber + '}';
  }
}



