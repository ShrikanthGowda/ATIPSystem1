package com.globallogic.hack.globalhack.interview.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Say")
@XmlAccessorType(XmlAccessType.FIELD)
public class Say
{
  @XmlAttribute
  private String voice;
  @XmlValue
  private String content;


  public String getVoice ()
  {
    return voice;
  }

  public void setVoice (String voice)
  {
    this.voice = voice;
  }

  public String getContent ()
  {
    return content;
  }

  public void setContent (String content)
  {
    this.content = content;
  }

}
