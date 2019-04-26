package com.globallogic.hack.globalhack.interview.model;

import javax.persistence.*;

@Entity
public class InterviewScore {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  @OneToOne
  @JoinColumn
  private InterviewRequest interviewRequest;
  private int score;


}
