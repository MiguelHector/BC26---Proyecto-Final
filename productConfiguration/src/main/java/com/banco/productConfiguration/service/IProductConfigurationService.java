package com.banco.productConfiguration.service;

import com.banco.productConfiguration.model.ProductConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductConfigurationService {

    Mono<ProductConfiguration> create(ProductConfiguration e);

    Mono<ProductConfiguration> findById(Integer id);

    Flux<ProductConfiguration> findAll();

    Mono<ProductConfiguration> findByTypeClient(String typeClient);

    Mono<ProductConfiguration> update(Integer id, Mono<ProductConfiguration> productConfiguration);

    Mono<Void> delete(Integer id);

}
