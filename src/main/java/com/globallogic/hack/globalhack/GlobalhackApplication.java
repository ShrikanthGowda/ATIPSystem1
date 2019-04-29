package com.globallogic.hack.globalhack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class GlobalhackApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GlobalhackApplication.class, args);
	}
}
