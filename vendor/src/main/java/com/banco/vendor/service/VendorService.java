package com.banco.vendor.service;

import com.banco.vendor.config.CacheConfig;
import com.banco.vendor.model.Vendor;
import com.banco.vendor.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VendorService implements IVendorService {
    @Autowired
    VendorRepository repository;

    @Override
    public Mono<Vendor> create(Vendor exchangeRate) {
        //client.setId(sequeenceId());
        return repository.save(exchangeRate);
    }

//    @Override
    @Cacheable(cacheNames = CacheConfig.VENDOR_CACHE, unless = "#result == null")
    public Mono<Vendor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Vendor> findAll() {
        Flux<Vendor> c=repository.findAll();
        c.subscribe(p->System.out.println(p.getDocumentNumber() + " - " + p.getAmount().toString() ));
        return c;
    }

    @Override
    public Mono<Vendor> findByDocumentNumber(String documentNumber)
    {
        return repository.findAll().filter(x -> x.getDocumentNumber().equals(documentNumber)).last();
    }

    @Override
    @CachePut(cacheNames = CacheConfig.VENDOR_CACHE, key = "#id", unless = "#result == null")
    public Mono<Vendor> update(Long id, Mono<Vendor> exchangeRate) {

        return this.findById(id)
                .flatMap(p -> exchangeRate.map(u -> {
                    p.setId (u.getId());
                    p.setAmount(u.getAmount());
                    p.setDocumentNumber(u.getDocumentNumber());
                    p.setCurrentAccount(u.getCurrentAccount());
                    p.setSaveAccount(u.getSaveAccount());
                    p.setMobileWallet(u.getMobileWallet());
                    p.setDocumentType(u.getDocumentType());
                    return p;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    @CacheEvict(cacheNames = CacheConfig.VENDOR_CACHE, key = "#id")
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }


}
