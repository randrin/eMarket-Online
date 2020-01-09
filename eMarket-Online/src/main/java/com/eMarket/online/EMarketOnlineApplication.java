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

@SpringBootApplication
public class EMarketOnlineApplication {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(EMarketOnlineApplication.class, args);
	}

	@Bean
	CommandLineRunner start (CategoryRepository categoryRepository, ProductRepository productRepository, SubcategoryRepository subcategoryRepository) {
		
		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class, Subcategory.class);
		
		return args->{
			categoryRepository.deleteAll();
			subcategoryRepository.deleteAll();
			Stream.of("TV Télevision Samsung", "SMARTPHONE Smartphone Nokia", "LAPTOP Laptop Asus").forEach(cat -> {
				categoryRepository.save(new Category(cat.split(" ")[0], cat.split(" ")[1], cat.split(" ")[2], "design_app", true, new ArrayList<>()));
			});
			categoryRepository.findAll().forEach(System.out::println);
			
			productRepository.deleteAll();
			
			Category categorie1 = categoryRepository.findById("SMARTPHONE").get();
			List<Subcategory> lisSubcaterory1 = new ArrayList<>();
			lisSubcaterory1.add(new Subcategory(null, "Samsung S9", "New Samsung S9"));

			
			Product p1 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Samsung S7", "The new Samsung s7 available in the OnlineShopping Store", 32000, "https://i.gadgets360cdn.com/products/large/1555507135_635_samsung_galaxy_a60.jpg", 3, true, 15, 3, categorie1));
			Product p1_1 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Iphone 11", "Iphone", "The new Iphone 11 available in the OnlineShopping Store", 85000, 15, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MWYK2?wid=1144&hei=1144&fmt=jpeg&qlt=80&op_usm=0.5,0.5&.v=1567304928359", 7, true, 8, 33, categorie1));
			Product p1_2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Wiko Lenny5", "Wiko Lenny5", "The new Wiko Lenny5 available in the OnlineShopping Store", 2000, 8, "https://s3.eu-central-1.amazonaws.com/static2.euronics.it/immagini/img-catalogo-converted/full/192005940.jpg", 5, true, 89, 11, categorie1));
			categorie1.getProducts().add(p1);
			categorie1.getProducts().add(p1_1);
			categorie1.getProducts().add(p1_2);
			categoryRepository.save(categorie1);
			
			Category categorie2 = categoryRepository.findById("LAPTOP").get();
			Product p2 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Lenovo", "Laptop", "The new lenovo Pc available in the OnlineShopping Store", 7000, 10, "https://images-na.ssl-images-amazon.com/images/I/51KWdrCyQQL._SX425_.jpg", 3, true, 3, 13, categorie2));
			categorie2.getProducts().add(p2);
			categoryRepository.save(categorie2);
			
			Category categorie3 = categoryRepository.findById("TV").get();
			Product p3 = productRepository.save(new Product(null, ("PRD" +UUID.randomUUID().toString().substring(20).toUpperCase()), "Akkai", "Tv", "The new Akkai Tv available in the OnlineShopping Store", 897000, 3, "https://images-na.ssl-images-amazon.com/images/I/81qPFjKF4ZL._SX466_.jpg", 1, true, 1, 11, categorie3));
			categorie3.getProducts().add(p3);
			categoryRepository.save(categorie3);
			
			productRepository.findAll().forEach(System.out::println);
		};
	}
}
