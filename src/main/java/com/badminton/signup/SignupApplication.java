package com.badminton.signup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SignupApplication {

	public static void main(String[] args){
		SpringApplication.run(SignupApplication.class, args);
	}
}
