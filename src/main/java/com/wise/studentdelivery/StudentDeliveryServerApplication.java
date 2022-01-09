package com.wise.studentdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StudentDeliveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDeliveryServerApplication.class, args);

	}

}
