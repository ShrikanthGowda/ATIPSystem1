package com.globallogic.hack.interview.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
public class TwiXmlController {

  @RequestMapping(value = "/twilioxml", method = RequestMethod.POST,produces = "application/xml")
  @ResponseBody
  public ClassPathResource getFile() {
    return new ClassPathResource("static/voice.xml");
  }

  @RequestMapping(value = "/twilioxml1", method = RequestMethod.POST,produces = "application/xml")
  @ResponseBody
  public ClassPathResource getFile1() {
    return new ClassPathResource("static/voice1.xml");
  }


  @RequestMapping(value = "/twilioxml2", method = RequestMethod.POST,produces = "application/xml")
  @ResponseBody
  public ClassPathResource getFile2(@RequestBody String object, @RequestParam("RecordingSid") String recordingSid) {
    System.out.print("Printing Object:"+recordingSid);

    return new ClassPathResource("static/voice2.xml");

  }


  @RequestMapping(value = "/twilioxml3", method = RequestMethod.POST,produces = "application/xml")
  @ResponseBody
  public ClassPathResource getFile3() {
    return new ClassPathResource("static/voice3.xml");
  }

}
