package com.italo.waiter;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.italo.waiter.model.*;
import com.italo.waiter.repository.CompanyRepository;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class WaiterApplication {

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
	CompanyRepository companyRepository;

	Logger logger = LoggerFactory.getLogger(WaiterApplication.class);

	@Bean
	public void seedDatabase(){
		if(roleRepository.findAll().size() == 0)roleRepository.save(new Role("ROLE_ADMIN"));
		if(companyRepository.findAll().size() == 0)seedCompanies();
		if(systemUserRepository.findAll().size() == 0)seedUsernames();
		if(productRepository.findAll().size() == 0)seedProducts();
	}


	private void seedCompanies(){
		File f = new File("Companies.csv");
		try (Scanner sc = new Scanner(f)) {
			while(sc.hasNextLine()){
				Company c = new Company();
				c.setName(sc.nextLine());
				companyRepository.save(c);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void seedUsernames(){
		File f = new File("Usernames.csv");
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()){
				SystemUser su = new SystemUser();
				List<Company> all = companyRepository.findAll();
				su.setCompany(all.get(new Random().nextInt(20)));
				su.setPassword(new BCryptPasswordEncoder().encode("password"));
				su.setUsername(s.nextLine());
				su.getRoles().add(roleRepository.findById(1L).get());
				systemUserRepository.save(su);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void seedProducts(){
		File f = new File("Products.csv");
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine()){
				Product p = new Product();
				String username = systemUserRepository.findAll().get(new Random().nextInt(80)).getUsername();
				p.setCreatedByUsername(username);
				p.setUpdatedByUsername(username);
				String[] split = s.nextLine().split(",");
				p.setDescription(split[0]);
				p.setSaleCost(Double.parseDouble(split[1]));
				p.setProductionCost(Double.parseDouble(split[2]));
				productRepository.save(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
