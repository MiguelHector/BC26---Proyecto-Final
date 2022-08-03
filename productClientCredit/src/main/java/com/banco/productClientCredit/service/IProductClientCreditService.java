package com.banco.productClientCredit.service;

import com.banco.productClientCredit.model.ProductClientCredit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductClientCreditService {

    Mono<ProductClientCredit> create(ProductClientCredit e);

    Mono<ProductClientCredit> findById(Integer id);

    Flux<ProductClientCredit> findAll();

    Mono<ProductClientCredit> findByIdClientProduct(Integer idClientProduct);

    Mono<ProductClientCredit> update(Integer id, Mono<ProductClientCredit> productClientCredit);

    Mono<Void> delete(Integer id);


}
