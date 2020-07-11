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

	@Autowired
	ConsumintUnitRepository consumintUnitRepository;

	@Autowired
	CommandItemRepository commandItemRepository;

	@Override
	public void run(final String... args) {
	}

	private void populate() {
		ConsumingUnit u = new ConsumingUnit();
		u.setNumber(1);
		u.setPeoples(2);
		consumintUnitRepository.save(u);

		Product p = new Product();
		p.setCode("Code");
		p.setDescription("Description");
		p.setProductionCost(15.25);
		p.setSaleCost(22.90);
		productRepository.save(p);

		CommandItem c1 = new CommandItem();
		c1.setProduct(p);
		c1.setQuantity(1);
		c1.setConsumingUnit(u);
		commandItemRepository.save(c1);

		CommandItem c2 = new CommandItem();
		c2.setProduct(p);
		c2.setQuantity(1);
		c2.setConsumingUnit(u);
		commandItemRepository.save(c2);

		CommandItem c3 = new CommandItem();
		c3.setProduct(p);
		c3.setQuantity(1);
		c3.setConsumingUnit(u);
		commandItemRepository.save(c3);

		CommandItem c4 = new CommandItem();
		c4.setProduct(p);
		c4.setQuantity(1);
		c4.setConsumingUnit(u);
		commandItemRepository.save(c4);


	}

}
