package com.fashion.eCommerce.repository;

import com.fashion.eCommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository <Product , String> {


}
