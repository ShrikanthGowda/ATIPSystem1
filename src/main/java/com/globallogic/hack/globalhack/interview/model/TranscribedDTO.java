package com.globallogic.hack.globalhack.interview.model;

public class TranscribedDTO {

  String modelAnswer;

  public TranscribedDTO(String modelAnswer, String transcribedAnswer) {
    this.modelAnswer = modelAnswer;
    this.transcribedAnswer = transcribedAnswer;
  }

  String transcribedAnswer;

  public String getTranscribedAnswer() {
    return transcribedAnswer;
  }

  public void setTranscribedAnswer(String transcribedAnswer) {
    this.transcribedAnswer = transcribedAnswer;
  }

  public String getModelAnswer() {
    return modelAnswer;
  }

  public void setModelAnswer(String modelAnswer) {
    this.modelAnswer = modelAnswer;
  }
}
