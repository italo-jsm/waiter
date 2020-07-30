package com.italo.waiter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italo.waiter.model.*;
import com.italo.waiter.repository.ProductRepository;
import com.italo.waiter.repository.RoleRepository;
import com.italo.waiter.repository.SystemUserRepository;
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
		ConsumingUnit unit = new ConsumingUnit();
		unit.setNumber(2);
		unit.setPeoples(3);
		CommandItem i1 = new CommandItem();
		CommandItem i2 = new CommandItem();
		Product p = new Product();
		p.setDescription("Desc");
		i1.setProduct(p);
		i2.setProduct(p);
		i1.setQuantity(2);
		i2.setQuantity(1);
		unit.getCommandItems().add(i1);
		unit.getCommandItems().add(i2);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		String s = mapper.writeValueAsString(unit);
		System.out.println();
	}
}
