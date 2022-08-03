package com.banco.movement.service;

import com.banco.movement.model.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovementService {
    Mono<Movement> create(Movement e);

    Mono<Movement> findById(Long id);

    Flux<Movement> findAll();

    Flux<Movement> findByIdCustomer(Long id);

    Flux<Movement> findByIdVendor(Long id);

    Mono<Movement> findByIdTransactionNumber(String transactionNumber);

    Mono<Movement> update(Long id, Mono<Movement> client);

    Mono<Void> delete(Long id);
}
