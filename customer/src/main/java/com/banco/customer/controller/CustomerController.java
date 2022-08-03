package com.banco.customer.controller;

import com.banco.customer.model.Customer;
import com.banco.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/get/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<Customer> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody Customer client) {
        Mono<Customer> response=service.create(client);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{identityDocument}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Long id,
            @RequestBody Mono<Customer> client) throws Exception {
        Mono<Customer> response=service.update(id,  client);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }


}
