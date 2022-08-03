package com.banco.exchangerate.repository;

import com.banco.exchangerate.model.ExchangeRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRateRepository extends ReactiveMongoRepository<ExchangeRate, Long> {

   default Mono<ExchangeRate> findExchangeRate(Long id)  {
        System.out.println("Call id=" + id);
        return findById(id);
    }

}
