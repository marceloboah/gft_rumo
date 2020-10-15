package com.gft.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gft.api.business.ProductBusinessObject;

@SpringBootApplication
public class GftApplication {
	
	@Autowired
	private ProductBusinessObject productBusinessObject;

	public static void main(String[] args) {
		SpringApplication.run(GftApplication.class, args);
	}
	
	@PostConstruct
	void postConstruct(){
		productBusinessObject.readFiles(2);
	}

}


