package com.olesya.psyCab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication (exclude = {UserDetailsServiceAutoConfiguration.class })
public class PsyCabApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsyCabApplication.class, args);
	}

}
