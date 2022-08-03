package com.banco.productConfiguration.service;

import com.banco.productConfiguration.model.ProductConfiguration;
import com.banco.productConfiguration.repository.ProductConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductConfigurationService implements IProductConfigurationService {

    @Autowired
    ProductConfigurationRepository productConfigurationRepository;

    @Override
    public Mono<ProductConfiguration> create(ProductConfiguration productConfiguration) {
        return productConfigurationRepository.save(productConfiguration);
    }

    @Override
    public Mono<ProductConfiguration> findById(Integer id) {
        return productConfigurationRepository.findById(id);
    }

    @Override
    public Flux<ProductConfiguration> findAll() {
        Flux<ProductConfiguration> c=productConfigurationRepository.findAll();

        c.subscribe(p->System.out.println(p.getClass()));

        return productConfigurationRepository.findAll();
    }

    @Override
    public Mono<ProductConfiguration> findByTypeClient(String typeClient)
    {
        return productConfigurationRepository.findAll().filter(x -> x.getTypeClient().equals(typeClient)).last();
    }

    @Override
    public Mono<ProductConfiguration> update(Integer id,Mono<ProductConfiguration> productConfiguration) {

        return this.findById(id)
                .flatMap(p -> productConfiguration.map(u -> {
                    p.setId (u.getId());
                    p.setIdProduct(u.getIdProduct());
                    p.setTypeClient(u.getTypeClient());
                    return p;
                }))
                .flatMap(p -> this.productConfigurationRepository.save(p));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return productConfigurationRepository.deleteById(id);
    }

}
