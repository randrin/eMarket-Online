package com.eMarket.online.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.eMarket.online.model.Product;

@RepositoryRestResource
public interface ProductRepository extends MongoRepository<Product, String>{

}
