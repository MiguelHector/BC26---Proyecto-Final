package com.banco.productClientCreditCard.service;

import com.banco.productClientCreditCard.model.ProductClientCreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductClientCreditCardService {

    Mono<ProductClientCreditCard> create(ProductClientCreditCard e);

    Mono<ProductClientCreditCard> findById(Integer id);

    Flux<ProductClientCreditCard> findAll();

    Mono<ProductClientCreditCard> findByIdClientProduct(Integer idClientProduct);

    Mono<ProductClientCreditCard> update(Integer id, Mono<ProductClientCreditCard> productClientCreditCard);

    Mono<Void> delete(Integer id);

}
