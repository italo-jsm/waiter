package com.italo.waiter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italo.waiter.model.*;
import com.italo.waiter.repository.ProductRepository;
import com.italo.waiter.repository.RoleRepository;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.service.s3.S3Service;
import com.italo.waiter.utils.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WaiterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WaiterApplication.class, args);
	}

	@Autowired
	ProductRepository productRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	SystemUserRepository systemUserRepository;

	@Autowired
	S3Service s3Service;

	Logger logger = LoggerFactory.getLogger(WaiterApplication.class);

	@Bean
	public void createSuperUser(){
		if(systemUserRepository.findAll().isEmpty()){
			logger.info("Creating super user...");
			Role r1 = new Role("ROLE_ADMIN");
			roleRepository.save(r1);
			SystemUser u = new SystemUser();
			u.setUsername("admin");
			u.setRegistrationNumber("1");
			u.getRoles().add(r1);
			u.setPassword(new BCryptPasswordEncoder().encode("password"));
			systemUserRepository.save(u);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.putObject();
	}
}
