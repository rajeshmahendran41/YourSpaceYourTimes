package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;



@SpringBootApplication
public class Application  extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		System.setProperty("server.port", webPort);
		SpringApplication.run(Application.class, args);
	}
	
	
	
	
}