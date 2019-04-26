package com.globallogic.hack.globalhack.interview.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record
{
  @XmlAttribute
  private Boolean transcribe;

  @XmlAttribute
  private String action;

  @XmlAttribute
  private Integer timeout;

  public Boolean getTranscribe() {
    return transcribe;
  }

  public void setTranscribe(Boolean transcribe) {
    this.transcribe = transcribe;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }
}
