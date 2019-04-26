package com.globallogic.hack.globalhack.interview.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response
{
  @XmlElement(name = "Say")
  private Say say;

  @XmlElement(name = "Record")
  private Record record;

  public Say getSay ()
  {
    return say;
  }

  public void setSay (Say say)
  {
    this.say = say;
  }

  public Record getRecord ()
  {
    return record;
  }

  public void setRecord (Record record)
  {
    this.record = record;
  }

}