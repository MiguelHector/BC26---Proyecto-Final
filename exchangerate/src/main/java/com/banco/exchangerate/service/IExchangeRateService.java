package com.banco.exchangerate.service;

import com.banco.exchangerate.model.ExchangeRate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IExchangeRateService {
    Mono<ExchangeRate> create(ExchangeRate e);

    Mono<ExchangeRate> findById(Long id);

    Flux<ExchangeRate> findAll();

    Mono<ExchangeRate> findByDate(String dateExchange);

    Mono<ExchangeRate> update(String identityDocument, Mono<ExchangeRate> client);

    Mono<Void> delete(Integer id);
}
