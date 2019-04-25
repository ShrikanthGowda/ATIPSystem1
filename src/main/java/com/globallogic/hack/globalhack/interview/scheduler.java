package com.globallogic.hack.globalhack.interview;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.net.URI;

public class scheduler {

  final String ACCOUNT_SID = "AC6b484d25bcb2ea56fe6b2679b554a6b7";
  final String AUTH_TOKEN = "e4b3fe77b6a22c4ef45b1505eef69fd9";

  public void callToCandidate(String candidateCellNumber) throws Exception {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    String from = "+17402794370";
    String to = "+919886323601";

    Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from), new URI("http://68.183.92.11:8080/twilioxml")).create();

    System.out.print(call.getSid());
  }
}
