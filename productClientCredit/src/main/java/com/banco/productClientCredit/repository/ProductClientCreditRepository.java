package com.banco.productClientCredit.repository;

import com.banco.productClientCredit.model.ProductClientCredit;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductClientCreditRepository extends ReactiveMongoRepository<ProductClientCredit, Integer> {
    @Query("{'idClientProduct': ?0}")
    Mono<ProductClientCredit> findByIdClientProduct(Integer idClientProduct);
}
