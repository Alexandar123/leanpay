package com.leanpay.Leanpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LeanpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeanpayApplication.class, args);
	}

}
