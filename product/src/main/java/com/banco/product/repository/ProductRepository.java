package com.banco.product.repository;

import com.banco.product.model.Product;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Integer> {
     @Query("{'nameProduct': ?0}")
     Mono<Product> findByNameProduct(String nameProduct);
}
