package com.banco.product.service;


import com.banco.product.model.Product;
import com.banco.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService implements  IProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Mono<Product> create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Flux<Product> findAll() {
        Flux<Product> c=productRepository.findAll();

        c.subscribe(p->System.out.println(p.getNameProduct()));

        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findByProduct(String nameProduct)
    {
        return productRepository.findAll().filter(x -> x.getNameProduct().equals(nameProduct)).last();
    }

    @Override
    public Mono<Product> update(Integer id,Mono<Product> product) {

        return this.findById(id)
                .flatMap(p -> product.map(u -> {
                    p.setId (u.getId());
                    p.setNameProduct(u.getNameProduct());
                    p.setTypeProduct(u.getTypeProduct());
                    return p;
                }))
                .flatMap(p -> this.productRepository.save(p));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return productRepository.deleteById(id);
    }

}
