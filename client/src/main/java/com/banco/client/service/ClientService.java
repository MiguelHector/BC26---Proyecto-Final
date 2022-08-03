package com.banco.client.service;

import com.banco.client.model.Client;
import com.banco.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements IClientService{
    @Autowired
    ClientRepository repository;

    @Override
    public Mono<Client> create(Client client) {
        //client.setId(sequeenceId());
        return repository.save(client);
    }

    @Override
    public Mono<Client> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Client> findAll() {
        Flux<Client> c=repository.findAll();

        c.subscribe(p->System.out.println(p.getNames()));

        return repository.findAll();
    }

    @Override
    public Mono<Client> findByDocument(String identityDocument)
    {
        return repository.findAll().filter(x -> x.getIdentityDocument().equals(identityDocument)).last();
    }

    @Override
    public Mono<Client> update(String identityDocument,Mono<Client> client) {

        return this.findByDocument(identityDocument)
                .flatMap(p -> client.map(u -> {
                    p.setId (u.getId());
                    p.setTypeClient(u.getTypeClient());
                    p.setTypeDocument(u.getTypeDocument());
                    p.setIdentityDocument(u.getIdentityDocument());
                    p.setNames(u.getNames());
                    p.setCompany(u.getCompany());
                    return p;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return repository.deleteById(id);
    }


}
