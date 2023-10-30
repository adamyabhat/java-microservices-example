package com.adamyaserver.sampleproductmicroservice.repository;

import com.adamyaserver.sampleproductmicroservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
