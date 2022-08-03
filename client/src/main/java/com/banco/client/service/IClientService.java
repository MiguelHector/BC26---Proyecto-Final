package com.banco.client.service;

import com.banco.client.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Mono<Client> create(Client e);

    Mono<Client> findById(Integer id);

    Flux<Client> findAll();

    Mono<Client> findByDocument(String identityDocument);

    Mono<Client> update(String identityDocument, Mono<Client> client);

    Mono<Void> delete(Integer id);
}
