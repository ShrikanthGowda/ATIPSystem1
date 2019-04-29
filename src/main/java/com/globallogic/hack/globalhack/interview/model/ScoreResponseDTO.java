package com.globallogic.hack.globalhack.interview.model;

public class ScoreResponseDTO {

  private Payload payload;

  public Payload getPayload() {
    return payload;
  }

  public void setPayload(Payload payload) {
    this.payload = payload;
  }

  public class Payload{
    private Integer score;

    public Integer getScore() {
      return score;
    }

    public void setScore(Integer score) {
      this.score = score;
    }
  }
}
