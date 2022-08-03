package com.banco.productClientFixed.repository;

import com.banco.productClientFixed.model.ProductClientFixed;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductClientFixedRepository extends ReactiveMongoRepository<ProductClientFixed, Integer> {
    @Query("{'idClientProduct': ?0}")
    Mono<ProductClientFixed> findByIdClientProduct(Integer idClientProduct);
}
