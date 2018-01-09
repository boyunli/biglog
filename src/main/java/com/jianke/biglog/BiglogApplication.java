package com.jianke.biglog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication()
@EnableAsync
public class BiglogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiglogApplication.class, args);
	}
}
