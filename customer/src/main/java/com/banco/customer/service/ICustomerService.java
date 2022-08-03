package com.banco.customer.service;

import com.banco.customer.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    Mono<Customer> create(Customer e);

    Mono<Customer> findById(Long id);

    Flux<Customer> findAll();

    Mono<Customer> findByDocumentNumber(String documentNumber);

    Mono<Customer> update(Long id, Mono<Customer> client);

    Mono<Void> delete(Long id);
}
