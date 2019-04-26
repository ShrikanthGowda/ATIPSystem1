package com.globallogic.hack.globalhack.interview.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class HangupResponse {

  @XmlElement(name = "Say")
  private Say say;

  @XmlElement(name = "Hangup")
  private Hangup hangup;

  public Say getSay() {
    return say;
  }

  public void setSay(Say say) {
    this.say = say;
  }

  public Hangup getHangup() {
    return hangup;
  }

  public void setHangup(Hangup hangup) {
    this.hangup = hangup;
  }
}
