package com.springbootcrudexample.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.springbootcrudexample.*")
@EntityScan("com.springbootcrudexample.entity")
@Configuration
public class SpringMain {

	public static void main(String[] args) {

		SpringApplication.run(SpringMain.class, args);




	}
}
