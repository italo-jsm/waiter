package com.italo.waiter;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.model.Product;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumintUnitRepository;
import com.italo.waiter.repository.ProductRepository;
import com.italo.waiter.utils.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class WaiterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WaiterApplication.class, args);
	}

	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(final String... args) {
		CSVParser.parseProducts().forEach(product -> productRepository.save(product));
	}
}
