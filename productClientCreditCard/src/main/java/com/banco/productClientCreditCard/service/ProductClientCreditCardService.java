package com.banco.productClientCreditCard.service;

import com.banco.productClientCreditCard.model.ProductClientCreditCard;
import com.banco.productClientCreditCard.repository.ProductClientCreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductClientCreditCardService implements IProductClientCreditCardService {

    @Autowired
    ProductClientCreditCardRepository productClientCreditCardRepository;

    @Override
    public Mono<ProductClientCreditCard> create(ProductClientCreditCard productClientCreditCard) {
        return productClientCreditCardRepository.save(productClientCreditCard);
    }

    @Override
    public Mono<ProductClientCreditCard> findById(Integer id) {
        return productClientCreditCardRepository.findById(id);
    }

    @Override
    public Flux<ProductClientCreditCard> findAll() {
        Flux<ProductClientCreditCard> c=productClientCreditCardRepository.findAll();

        c.subscribe(p->System.out.println(p.getClass()));

        return productClientCreditCardRepository.findAll();
    }

    @Override
    public Mono<ProductClientCreditCard> findByIdClientProduct(Integer idClientProduct)
    {
        return productClientCreditCardRepository.findAll().filter(x -> x.getIdClientProduct().equals(idClientProduct)).last();
    }

    @Override
    public Mono<ProductClientCreditCard> update(Integer id,Mono<ProductClientCreditCard> productClientCreditCard) {

        return this.findById(id)
                .flatMap(p -> productClientCreditCard.map(u -> {
                    p.setId (u.getId());
                    p.setIdClientProduct(u.getIdClientProduct());
                    p.setDateEnd(u.getDateEnd());
                    p.setCreditLine(u.getCreditLine());
                    p.setDatePayment(u.getDatePayment());
                    return p;
                }))
                .flatMap(p -> this.productClientCreditCardRepository.save(p));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return productClientCreditCardRepository.deleteById(id);
    }


}
