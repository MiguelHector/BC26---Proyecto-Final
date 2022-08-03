package com.banco.productClientFixed.service;

import com.banco.productClientFixed.model.ProductClientFixed;
import com.banco.productClientFixed.repository.ProductClientFixedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductClientFixedService implements IProductClientFixedService {

    @Autowired
    ProductClientFixedRepository productClientFixedRepository;

    @Override
    public Mono<ProductClientFixed> create(ProductClientFixed productClientFixed) {
        return productClientFixedRepository.save(productClientFixed);
    }

    @Override
    public Mono<ProductClientFixed> findById(Integer id) {
        return productClientFixedRepository.findById(id);
    }

    @Override
    public Flux<ProductClientFixed> findAll() {
        Flux<ProductClientFixed> c=productClientFixedRepository.findAll();

        c.subscribe(p->System.out.println(p.getClass()));

        return productClientFixedRepository.findAll();
    }

    @Override
    public Mono<ProductClientFixed> findByIdClientProduct(Integer idClientProduct)
    {
        return productClientFixedRepository.findAll().filter(x -> x.getIdClientProduct().equals(idClientProduct)).last();
    }

    @Override
    public Mono<ProductClientFixed> update(Integer id,Mono<ProductClientFixed> productClientFixed) {

        return this.findById(id)
                .flatMap(p -> productClientFixed.map(u -> {
                    p.setId (u.getId());
                    p.setIdClientProduct(u.getIdClientProduct());
                    p.setAmountMinimum(u.getAmountMinimum());
                    p.setDateEnd(u.getDateEnd());
                    p.setDeadline(u.getDeadline());
                    p.setInitialCapital(u.getInitialCapital());
                    return p;
                }))
                .flatMap(p -> this.productClientFixedRepository.save(p));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return productClientFixedRepository.deleteById(id);
    }


}
