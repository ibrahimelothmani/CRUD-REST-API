package com.demo.crud.RestAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudRestApiApplication {

	private final static Logger Log = LoggerFactory.getLogger(CrudRestApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CrudRestApiApplication.class, args);
		Log.info("CrudRestApiApplication started");
	}

}
