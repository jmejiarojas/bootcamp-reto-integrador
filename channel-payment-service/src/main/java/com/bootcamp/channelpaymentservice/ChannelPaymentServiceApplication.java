package com.bootcamp.channelpaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ChannelPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChannelPaymentServiceApplication.class, args);
	}

}
