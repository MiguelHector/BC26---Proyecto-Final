package com.banco.client.repository;

import com.banco.client.model.Client;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, Integer> {
    @Query("{'identityDocument': ?0}")
    Mono<Client> findByDocument(String identityDocument);

}
