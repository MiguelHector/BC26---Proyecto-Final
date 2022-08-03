package com.banco.productClientCredit.service;

import com.banco.productClientCredit.model.ProductClientCredit;
import com.banco.productClientCredit.repository.ProductClientCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductClientCreditService implements IProductClientCreditService {

    @Autowired
    ProductClientCreditRepository productClientCreditRepository;

    @Override
    public Mono<ProductClientCredit> create(ProductClientCredit productClientCredit) {
        return productClientCreditRepository.save(productClientCredit);
    }

    @Override
    public Mono<ProductClientCredit> findById(Integer id) {
        return productClientCreditRepository.findById(id);
    }

    @Override
    public Flux<ProductClientCredit> findAll() {
        Flux<ProductClientCredit> c=productClientCreditRepository.findAll();

        c.subscribe(p->System.out.println(p.getClass()));

        return productClientCreditRepository.findAll();
    }

    @Override
    public Mono<ProductClientCredit> findByIdClientProduct(Integer idClientProduct)
    {
        return productClientCreditRepository.findAll().filter(x -> x.getIdClientProduct().equals(idClientProduct)).last();
    }

    @Override
    public Mono<ProductClientCredit> update(Integer id,Mono<ProductClientCredit> productClientCredit) {

        return this.findById(id)
                .flatMap(p -> productClientCredit.map(u -> {
                    p.setId (u.getId());
                    p.setIdClientProduct(u.getIdClientProduct());
                    p.setAmountMinimum(u.getAmountMinimum());
                    p.setArrearsRate(u.getArrearsRate());
                    p.setRate(u.getRate());
                    p.setQuantityShare(u.getQuantityShare());
                    return p;
                }))
                .flatMap(p -> this.productClientCreditRepository.save(p));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return productClientCreditRepository.deleteById(id);
    }


}
