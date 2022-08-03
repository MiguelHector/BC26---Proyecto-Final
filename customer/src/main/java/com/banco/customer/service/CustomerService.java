package com.banco.customer.service;

import com.banco.customer.config.CacheConfig;
import com.banco.customer.model.Customer;
import com.banco.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    CustomerRepository repository;

    @Override
    public Mono<Customer> create(Customer exchangeRate) {
        //client.setId(sequeenceId());
        return repository.save(exchangeRate);
    }

//    @Override
    @Cacheable(cacheNames = CacheConfig.CUSTOMER_CACHE, unless = "#result == null")
    public Mono<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Customer> findAll() {
        Flux<Customer> c=repository.findAll();
        c.subscribe(p->System.out.println(p.getDocumentNumber() + " - " + p.getAmount().toString() ));
        return c;
    }

    @Override
    public Mono<Customer> findByDocumentNumber(String documentNumber)
    {
        return repository.findAll().filter(x -> x.getDocumentNumber().equals(documentNumber)).last();
    }

    @Override
    @CachePut(cacheNames = CacheConfig.CUSTOMER_CACHE, key = "#id", unless = "#result == null")
    public Mono<Customer> update(Long id, Mono<Customer> exchangeRate) {

        return this.findById(id)
                .flatMap(p -> exchangeRate.map(u -> {
                    p.setId (u.getId());
                    p.setAmount(u.getAmount());
                    p.setDocumentNumber(u.getDocumentNumber());
                    p.setEmail(u.getEmail());
                    p.setTelephoneNumber(u.getTelephoneNumber());
                    p.setDocumentType(u.getDocumentType());
                    return p;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    @CacheEvict(cacheNames = CacheConfig.CUSTOMER_CACHE, key = "#id")
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }


}
