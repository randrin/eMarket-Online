package com.eMarket.online;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.eMarket.online.model.Category;
import com.eMarket.online.model.Product;
import com.eMarket.online.respository.CategoryRepository;
import com.eMarket.online.respository.ProductRepository;

@SpringBootApplication
public class EMarketOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EMarketOnlineApplication.class, args);
	}

	@Bean
	CommandLineRunner start (CategoryRepository categoryRepository, ProductRepository productRepository)  {
		return args->{
			categoryRepository.deleteAll();
			Stream.of("TV Télevision Samsung", "SMARPHONE Smarphone Nokia", "LAPTOP Laptop Asus").forEach(cat -> {
				categoryRepository.save(new Category(cat.split(" ")[0], cat.split(" ")[1], cat.split(" ")[2], "cat_1.png", true, new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);
			
			productRepository.deleteAll();
			String code1 = "PRD" +UUID.randomUUID().toString().substring(20).toUpperCase();
			String code2 = "PRD" +UUID.randomUUID().toString().substring(20).toUpperCase();
			String code3 = "PRD" +UUID.randomUUID().toString().substring(20).toUpperCase();
			

			Product p1 = productRepository.save(new Product(code1.substring(code1.length() - 1), code1, "Samsung S7", "Samsung", "The new samsung s7 available in the OnlineShopping Store", 32000, 5, 3, true, 2, 3, categoryRepository.findById("SMARPHONE").get()));
			categoryRepository.findById("SMARPHONE").get().getProducts().add(p1);
			categoryRepository.save(categoryRepository.findById("SMARPHONE").get());
			
			Product p2 = productRepository.save(new Product(code2.substring(code2.length() - 1), code2, "Lenovo", "Laptop", "The new lenovo Pc available in the OnlineShopping Store", 7000, 10, 3, true, 3, 13, categoryRepository.findById("LAPTOP").get()));
			categoryRepository.findById("LAPTOP").get().getProducts().add(p2);
			categoryRepository.save(categoryRepository.findById("LAPTOP").get());
			
			Product p3 = productRepository.save(new Product(code3.substring(code3.length() - 1), code3, "Akkai", "Tv", "The new Akkai Tv available in the OnlineShopping Store", 897000, 3, 1, true, 1, 11, categoryRepository.findById("TV").get()));
			categoryRepository.findById("TV").get().getProducts().add(p3);
			categoryRepository.save(categoryRepository.findById("TV").get());
			
			productRepository.findAll().forEach(System.out::println);
		};
	}
}
