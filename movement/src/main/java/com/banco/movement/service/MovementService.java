package com.banco.movement.service;

import com.banco.movement.config.CacheConfig;
import com.banco.movement.model.Movement;
import com.banco.movement.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementService implements IMovementService {
    @Autowired
    MovementRepository repository;

    @Override
    public Mono<Movement> create(Movement exchangeRate) {
        //client.setId(sequeenceId());
        return repository.save(exchangeRate);
    }

//    @Override
    @Cacheable(cacheNames = CacheConfig.MOVEMENT_CACHE, unless = "#result == null")
    public Mono<Movement> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Movement> findAll() {
        Flux<Movement> c=repository.findAll();
        c.subscribe(p->System.out.println(p.getId().toString() + " - " + p.getAmount().toString() ));
        return c;
    }

    @Override
    public Flux<Movement> findByIdCustomer(Long id)
    {
        return repository.findAll().filter(x -> x.getIdCustomer().equals(id));
    }

    @Override
    public Flux<Movement> findByIdVendor(Long id)
    {
        return repository.findAll().filter(x -> x.getIdVendor().equals(id));
    }

    @Override
    public Mono<Movement> findByIdTransactionNumber(String transactionNumber)
    {
        return repository.findAll().filter(x -> x.getTransactionNumber().equals(transactionNumber)).last();
    }

    @Override
    @CachePut(cacheNames = CacheConfig.MOVEMENT_CACHE, key = "#id", unless = "#result == null")
    public Mono<Movement> update(Long id, Mono<Movement> exchangeRate) {

        return this.findById(id)
                .flatMap(p -> exchangeRate.map(u -> {
                    p.setId (u.getId());
                    p.setAmount(u.getAmount());
                    p.setExchangeRate(u.getExchangeRate());
                    p.setIdCustomer(u.getIdCustomer());
                    p.setIdVendor(u.getIdVendor());
                    p.setTransactionNumber(u.getTransactionNumber());
                    p.setPayType(u.getPayType());
                    return p;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    @CacheEvict(cacheNames = CacheConfig.MOVEMENT_CACHE, key = "#id")
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }


}
