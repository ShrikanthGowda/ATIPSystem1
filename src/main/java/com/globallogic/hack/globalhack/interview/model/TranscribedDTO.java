package com.globallogic.hack.globalhack.interview.model;

public class TranscribedDTO {

  String modelAnswer;

  public TranscribedDTO(String modelAnswer, String answer) {
    this.modelAnswer = modelAnswer;
    this.answer = answer;
  }

  String answer;

  public String getModelAnswer() {
    return modelAnswer;
  }

  public void setModelAnswer(String modelAnswer) {
    this.modelAnswer = modelAnswer;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  @Override public String toString() {
    return "TranscribedDTO{" + "modelAnswer='" + modelAnswer + '\'' + ", answer='" + answer + '\'' + '}';
  }
}
