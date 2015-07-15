package com.compass.microservices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	 public static void main(String[] args) throws Exception {
		 SpringApplication.run(new Object[] { Application.class }, args);
		 System.out.println("inside spring boot app");
		 }
}
