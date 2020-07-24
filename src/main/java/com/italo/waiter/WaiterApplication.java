package com.italo.waiter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italo.waiter.model.Address;
import com.italo.waiter.repository.ProductRepository;
import com.italo.waiter.repository.RoleRepository;
import com.italo.waiter.utils.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WaiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaiterApplication.class, args);
	}

	@Autowired
	ProductRepository productRepository;

	@Autowired
	RoleRepository roleRepository;

	Logger logger = LoggerFactory.getLogger(WaiterApplication.class);

}
