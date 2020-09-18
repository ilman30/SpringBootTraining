package com.ilman.latihan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ilman.latihan.ui","com.ilman.latihan.config","com.ilman.latihan.impl"})
public class LatihanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LatihanApplication.class, args);
	}

}
