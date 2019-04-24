package com.globallogic.hacks.globalhack;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.net.URI;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
@ComponentScan(value = "com.globallogic.*")
public class GlobalhackApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(GlobalhackApplication.class, args);
	}
}
