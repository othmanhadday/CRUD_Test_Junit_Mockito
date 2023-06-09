package com.springbootcrudexample.main;

import com.springbootcrudexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springbootcrudexample.*")
@EntityScan("com.springbootcrudexample.*")
public class SpringMain {


	public static void main(String[] args) {

		SpringApplication.run(SpringMain.class, args);




	}
}
