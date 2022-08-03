package com.banco.vendor.service;

import com.banco.vendor.model.Vendor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVendorService {
    Mono<Vendor> create(Vendor e);

    Mono<Vendor> findById(Long id);

    Flux<Vendor> findAll();

    Mono<Vendor> findByDocumentNumber(String documentNumber);

    Mono<Vendor> update(Long id, Mono<Vendor> client);

    Mono<Void> delete(Long id);
}
