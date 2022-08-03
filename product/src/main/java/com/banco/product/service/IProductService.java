package com.banco.product.service;

import com.banco.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IProductService {
    Mono<Product> create(Product e);

    Mono<Product> findById(Integer id);

    Flux<Product> findAll();

    Mono<Product> findByProduct(String nameProduct);

    Mono<Product> update(Integer id, Mono<Product> product);

    Mono<Void> delete(Integer id);
}
