package com.lijuyong.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class HjyWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HjyWebApplication.class, args);
	}
}
