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
			Stream.of("Tv Télevision Samsung", "Smartphone Smartphone Nokia", "Laptop Laptop Asus").forEach(cat -> {
				categoryRepository.save(new Category(cat.split(" ")[0], cat.split(" ")[1], cat.split(" ")[2], "design_app", true, new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);
			
			productRepository.deleteAll();
			
			Category categorie1 = categoryRepository.findById("Smartphone").get();
			Subcategory subcategory1 = new Subcategory("Samsung", "Samsung", "Smartphone Samsung", new ArrayList<>());
			Subcategory subcategory2 = new Subcategory("Iphone", "Iphone", "Smartphone Iphone", new ArrayList<>());
			Subcategory subcategory3 = new Subcategory("Nokia", "Nokia", "Smartphone Nokia", new ArrayList<>());
			Subcategory subcategory4 = new Subcategory("Wiko", "Wiko", "Smartphone Wiko", new ArrayList<>());
			
			Product p1 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Samsung S7", "The new Samsung s7 available in the OnlineShopping Store", 32000, 45000, true, EmarketUtils.percentReductuon(32000, 45000), "https://i.gadgets360cdn.com/products/large/1555507135_635_samsung_galaxy_a60.jpg", 3, true, 15, 3, 20, subcategory1));
			Product p1_1 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Iphone 11", "The new Iphone 11 available in the OnlineShopping Store", 15000, 32000, false, EmarketUtils.percentReductuon(15000, 32000), "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MWYK2?wid=1144&hei=1144&fmt=jpeg&qlt=80&op_usm=0.5,0.5&.v=1567304928359", 7, true, 45, 23, 89, subcategory2));
			Product p1_2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Samsung Galaxy A20", "The new Samsung Galaxy A20 available in the OnlineShopping Store", 2500, 3000, true, EmarketUtils.percentReductuon(2500, 3000), "https://www.cellularishop.com/1848-large_default/samsung-galaxy-a20e-32gb-black-italia-no-brand-dual-sim.jpg", 8, true, 22, 13, 10, subcategory1));
			Product p1_3 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Wiko Lenny5", "The new Wiko Lenny5 available in the OnlineShopping Store", 2000, 3300, false, EmarketUtils.percentReductuon(2000, 3300), "https://s3.eu-central-1.amazonaws.com/static2.euronics.it/immagini/img-catalogo-converted/full/192005940.jpg", 5, true, 89, 11, 50, subcategory4));
			
			subcategory1.getProducts().add(p1);
			subcategory2.getProducts().add(p1_1);
			subcategory1.getProducts().add(p1_2);
			subcategory4.getProducts().add(p1_3);
			subcategoryRepository.save(subcategory1);
			subcategoryRepository.save(subcategory2);
			subcategoryRepository.save(subcategory4);
			
			categorie1.getSubCategories().add(subcategory1);
			categorie1.getSubCategories().add(subcategory2);
			categorie1.getSubCategories().add(subcategory4);
			categoryRepository.save(categorie1);
			
			/*Category categorie2 = categoryRepository.findById("LAPTOP").get();
			Product p2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Lenovo", "Laptop", "The new lenovo Pc available in the OnlineShopping Store", 7000, 10, "https://images-na.ssl-images-amazon.com/images/I/51KWdrCyQQL._SX425_.jpg", 3, true, 3, 13, categorie2));
			categorie2.getProducts().add(p2);
			categoryRepository.save(categorie2);
			
			Category categorie3 = categoryRepository.findById("TV").get();
			Product p3 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Akkai", "Tv", "The new Akkai Tv available in the OnlineShopping Store", 897000, 3, "https://images-na.ssl-images-amazon.com/images/I/81qPFjKF4ZL._SX466_.jpg", 1, true, 1, 11, categorie3));
			categorie3.getProducts().add(p3);
			categoryRepository.save(categorie3);*/
			
			productRepository.findAll().forEach(System.out::println);
		};
	}
}
