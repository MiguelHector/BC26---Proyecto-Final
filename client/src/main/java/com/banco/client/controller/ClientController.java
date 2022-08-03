package com.banco.client.controller;

import com.banco.client.model.Client;
import com.banco.client.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private IClientService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Client> findAll() {
        return service.findAll();
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody Client client) {
        Mono<Client> response=service.create(client);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{identityDocument}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("identityDocument") String identityDocument,
            @RequestBody Mono<Client> client) throws Exception {
        Mono<Client> response=service.update(identityDocument,  client);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }


}
