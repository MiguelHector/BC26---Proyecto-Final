package com.banco.productConfiguration.repository;

import com.banco.productConfiguration.model.ProductConfiguration;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductConfigurationRepository extends ReactiveMongoRepository<ProductConfiguration, Integer> {
    @Query("{'typeClient': ?0}")
    Mono<ProductConfiguration> findByTypeClient(String typeClient);

}
