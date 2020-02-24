package com.eMarket.online;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.eMarket.online.model.Category;
import com.eMarket.online.model.Product;
import com.eMarket.online.model.Subcategory;
import com.eMarket.online.respository.CategoryRepository;
import com.eMarket.online.respository.ProductRepository;
import com.eMarket.online.respository.SubcategoryRepository;
import com.eMarket.online.utils.EmarketUtils;

@SpringBootApplication
public class EMarketOnlineApplication {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(EMarketOnlineApplication.class, args);
	}

	/**
	 * @param categoryRepository
	 * @param productRepository
	 * @param subcategoryRepository
	 * @return
	 */
	@Bean
	CommandLineRunner start (CategoryRepository categoryRepository, ProductRepository productRepository, SubcategoryRepository subcategoryRepository) {
		
		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class, Subcategory.class);
		
		return args->{
			categoryRepository.deleteAll();
			subcategoryRepository.deleteAll();
			Stream.of("Tv Tv Disponible", "Smartphone Smartphone Disponible", "Laptop Laptop Disponible").forEach(cat -> {
				categoryRepository.save(new Category(cat.split(" ")[0], cat.split(" ")[1], cat.split(" ")[2], "design_app", true, new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);
			
			productRepository.deleteAll();
			
			// Loading Products Category Smartphone
			Category categorie1 = categoryRepository.findById("Smartphone").get();
			Subcategory subcategory1 = new Subcategory("Samsung", "Samsung", "Smartphone Samsung", new ArrayList<>());
			Subcategory subcategory2 = new Subcategory("Iphone", "Iphone", "Smartphone Iphone", new ArrayList<>());
			Subcategory subcategory3 = new Subcategory("Nokia", "Nokia", "Smartphone Nokia", new ArrayList<>());
			Subcategory subcategory4 = new Subcategory("Wiko", "Wiko", "Smartphone Wiko", new ArrayList<>());
			
			Product p1_1 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Samsung S7", "The new Samsung s7 available in the OnlineShopping Store", 45000, 32000, "Blue Sombre", "", true, EmarketUtils.percentReductuon(45000, 32000), "https://i.gadgets360cdn.com/products/large/1555507135_635_samsung_galaxy_a60.jpg", 3, true, 15, 3, 20, subcategory1));
			Product p2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Iphone 11", "The new Iphone 11 available in the OnlineShopping Store", 32000, 15000, "Noir", "", false, EmarketUtils.percentReductuon(32000, 15000), "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MWYK2?wid=1144&hei=1144&fmt=jpeg&qlt=80&op_usm=0.5,0.5&.v=1567304928359", 7, true, 45, 23, 89, subcategory2));
			Product p1_2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Samsung Galaxy A20", "The new Samsung Galaxy A20 available in the OnlineShopping Store", 3000, 2500, "Blanc", "", true, EmarketUtils.percentReductuon(3000, 2500), "https://www.cellularishop.com/1848-large_default/samsung-galaxy-a20e-32gb-black-italia-no-brand-dual-sim.jpg", 8, true, 22, 13, 10, subcategory1));
			Product p3 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Nokia 3310", "The new Nokia 3310 available in the OnlineShopping Store", 2500, 1000, "Gris Sombre", "", true, EmarketUtils.percentReductuon(2500, 1000), "https://www.mytrendyphone.it/images/Nokia-3310-Factory-Refurbished-Dark-Blue-14082018-01-p.jpg", 4, true, 100, 50, 150, subcategory3));
			Product p4 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Wiko Lenny5", "The new Wiko Lenny5 available in the OnlineShopping Store", 3300, 2000, "Blue", "", false, EmarketUtils.percentReductuon(3300, 2000), "https://s3.eu-central-1.amazonaws.com/static2.euronics.it/immagini/img-catalogo-converted/full/192005940.jpg", 5, true, 89, 11, 50, subcategory4));
			
			subcategory1.getProducts().add(p1_1);
			subcategory2.getProducts().add(p2);
			subcategory1.getProducts().add(p1_2);
			subcategory3.getProducts().add(p3);
			subcategory4.getProducts().add(p4);
			subcategoryRepository.save(subcategory1);
			subcategoryRepository.save(subcategory2);
			subcategoryRepository.save(subcategory3);
			subcategoryRepository.save(subcategory4);
			
			categorie1.getSubCategories().add(subcategory1);
			categorie1.getSubCategories().add(subcategory2);
			categorie1.getSubCategories().add(subcategory3);
			categorie1.getSubCategories().add(subcategory4);
			categoryRepository.save(categorie1);
			
			/*Category categorie2 = categoryRepository.findById("LAPTOP").get();
			Product p2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Lenovo", "Laptop", "The new lenovo Pc available in the OnlineShopping Store", 7000, 10, "https://images-na.ssl-images-amazon.com/images/I/51KWdrCyQQL._SX425_.jpg", 3, true, 3, 13, categorie2));
			categorie2.getProducts().add(p2);
			categoryRepository.save(categorie2);*/
			
			// Loading Products Category Tv
			Category categorie3 = categoryRepository.findById("Tv").get();
			Subcategory sub1 = new Subcategory("Tv Samsung", "Tv Samsung", "Télevision Samsung", new ArrayList<>());
			Subcategory sub2 = new Subcategory("Tv Akkai", "Tv Akkai", "Télevision Akkai", new ArrayList<>());
			Subcategory sub3 = new Subcategory("Tv LG", "Tv LG", "Télevision LG", new ArrayList<>());
			
			Product pd1 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Samsung Smart Tv, 4K Ultra Hd Wi-Fi Nero", "The new Samsung Smart Tv available in the OnlineShopping Store", 345000, 232000, "Noir", "44 Pouces", true, EmarketUtils.percentReductuon(345000, 232000), "https://images-na.ssl-images-amazon.com/images/I/91uZe9P9LgL._SX425_.jpg", 23, true, 25, 23, 120, sub1));
			Product pd2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Akkai AKTV3222T 32pc HD Smart TV Wi-Fi White DVB T/2 - LED TVs", "The new Akkai AKTV3222T available in the OnlineShopping Store", 202000, 115000, "Blanc", "32 Pouces", true, EmarketUtils.percentReductuon(202000, 115000), "https://images-eu.ssl-images-amazon.com/images/I/41shAK7vJCL._SL500_AC_SS350_.jpg", 17, true, 45, 23, 89, sub2));
			Product pd3 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Smart TV LG 65UM7000PLA 65' 4K Ultra HD LED WiFi Nero", "The new Smart TV LG available in the OnlineShopping Store", 523300, 452000, "Blanc", "65 Pouces", false, EmarketUtils.percentReductuon(523300, 452000), "https://images-na.ssl-images-amazon.com/images/I/817kIItUz9L._SX466_.jpg", 5, false, 89, 11, 50, sub3));
			
			sub1.getProducts().add(pd1);
			sub2.getProducts().add(pd2);
			sub3.getProducts().add(pd3);
			subcategoryRepository.save(sub1);
			subcategoryRepository.save(sub2);
			subcategoryRepository.save(sub3);
			
			categorie3.getSubCategories().add(sub1);
			categorie3.getSubCategories().add(sub2);
			categorie3.getSubCategories().add(sub3);
			categoryRepository.save(categorie3);
			
			productRepository.findAll().forEach(System.out::println);
		};
	}
}
