package com.eMarket.online.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.eMarket.online.model.EmarketSubCategory;

@RepositoryRestResource
public interface EmarketSubCategoryRepository extends MongoRepository<EmarketSubCategory, String> {

}
