package com.eMarket.online.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.eMarket.online.model.Product;

@RepositoryRestResource
public interface ProductRepository extends MongoRepository<Product, String>{
	@RestResource(path = "/productsActivated")
	List<Product> findByActivationIsTrue();

	@RestResource(path = "/productsPromotion")
	List<Product> findBySaleIsTrue();
}
