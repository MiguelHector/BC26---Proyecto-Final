package com.banco.productClientCreditCard.repository;

import com.banco.productClientCreditCard.model.ProductClientCreditCard;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductClientCreditCardRepository extends ReactiveMongoRepository<ProductClientCreditCard, Integer> {
    @Query("{'idClientProduct': ?0}")
    Mono<ProductClientCreditCard> findByIdClientProduct(Integer idClientProduct);
}
