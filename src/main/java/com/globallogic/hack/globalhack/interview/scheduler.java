package com.globallogic.hack.globalhack.interview;

import com.globallogic.hack.globalhack.interview.dao.InterviewRequestRepository;
import com.globallogic.hack.globalhack.interview.model.InterviewRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;
import java.util.Set;

@Service
public class scheduler {


  public static final  String ACCOUNT_SID = "AC21eeaf9d83cf9ddb8f325fb434122dec";


  public static final String AUTH_TOKEN = "d44d7795d4c93457deec1b045ff00b98";

  @Value("${server.port}")
  private String serverPort;

  @Autowired
  InterviewRequestRepository repository;

  @Scheduled(initialDelay = 10000, fixedRate = 10000)
  public void getScheduledInterview() throws Exception {
    Set<InterviewRequest> interviewRequests = repository.findByTodayInterview(new Date());
    for (InterviewRequest request : interviewRequests) {
      callToCandidate(request);
      request.setStatus("INT-ONGOING");
      repository.save(request);
    }
  }

  public void callToCandidate(InterviewRequest request) throws Exception {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    String from = "+12015290698";
    Call.creator(new PhoneNumber(String.valueOf(request.getPhoneNumber())), new PhoneNumber(from), new URI("http://68.183.92.11:"+serverPort+"/interview/"+request.getId()+"/"+request.getCandidateName()+"/"+request.getSubject())).create();
  }
}