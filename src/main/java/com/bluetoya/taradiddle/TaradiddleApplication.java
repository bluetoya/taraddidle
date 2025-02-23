package com.bluetoya.taradiddle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TaradiddleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaradiddleApplication.class, args);
	}

}
