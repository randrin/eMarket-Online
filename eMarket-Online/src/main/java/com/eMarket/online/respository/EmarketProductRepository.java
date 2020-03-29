package com.eMarket.online.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.eMarket.online.model.EmarketProduct;

@RepositoryRestResource
public interface EmarketProductRepository extends MongoRepository<EmarketProduct, String>{
	@RestResource(path = "/productsActivated")
	List<EmarketProduct> findByActivationIsTrue();

	@RestResource(path = "/productsPromotion")
	List<EmarketProduct> findBySaleIsTrue();
}
