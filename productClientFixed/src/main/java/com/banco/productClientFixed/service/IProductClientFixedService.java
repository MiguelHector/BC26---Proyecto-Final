package com.banco.productClientFixed.service;

import com.banco.productClientFixed.model.ProductClientFixed;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductClientFixedService {

    Mono<ProductClientFixed> create(ProductClientFixed e);

    Mono<ProductClientFixed> findById(Integer id);

    Flux<ProductClientFixed> findAll();

    Mono<ProductClientFixed> findByIdClientProduct(Integer idClientProduct);

    Mono<ProductClientFixed> update(Integer id, Mono<ProductClientFixed> productClientFixed);

    Mono<Void> delete(Integer id);

}
