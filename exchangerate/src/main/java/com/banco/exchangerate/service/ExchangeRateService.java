package com.banco.exchangerate.service;

import com.banco.exchangerate.config.CacheConfig;
import com.banco.exchangerate.model.ExchangeRate;
import com.banco.exchangerate.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateService implements IExchangeRateService {
    @Autowired
    ExchangeRateRepository repository;

    @Override
    public Mono<ExchangeRate> create(ExchangeRate exchangeRate) {
        //client.setId(sequeenceId());
        return repository.save(exchangeRate);
    }

//    @Override
    @Cacheable(cacheNames = CacheConfig.EXCHANGERATE_CACHE, unless = "#result == null")
    public Mono<ExchangeRate> findById(Long id) {
        return repository.findExchangeRate(id);
    }

    @Override
    public Flux<ExchangeRate> findAll() {
        Flux<ExchangeRate> c=repository.findAll();

        c.subscribe(p->System.out.println(p.getValueRate()));

        return repository.findAll();
    }

    @Override
    public Mono<ExchangeRate> findByDate(String dateExchange)
    {
        return repository.findAll().filter(x -> x.getDateExchange().equals(dateExchange)).last();
    }

    @Override
    @CachePut(cacheNames = CacheConfig.EXCHANGERATE_CACHE, key = "#id", unless = "#result == null")
    public Mono<ExchangeRate> update(String dateExchange, Mono<ExchangeRate> exchangeRate) {

        return this.findByDate(dateExchange)
                .flatMap(p -> exchangeRate.map(u -> {
                    p.setId (u.getId());
                    p.setDateExchange(u.getDateExchange());
                    p.setValueRate(u.getValueRate());
                    return p;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    @CacheEvict(cacheNames = CacheConfig.EXCHANGERATE_CACHE, key = "#id")
    public Mono<Void> delete(Integer id) {
        return repository.deleteById(id);
    }


}
