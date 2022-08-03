package com.banco.exchangerate.controller;

import com.banco.exchangerate.model.ExchangeRate;
import com.banco.exchangerate.service.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchangerate")
public class ExchangeRateController {
    @Autowired
    private IExchangeRateService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<ExchangeRate> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/get/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<ExchangeRate> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody ExchangeRate client) {
        Mono<ExchangeRate> response=service.create(client);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{identityDocument}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("identityDocument") String identityDocument,
            @RequestBody Mono<ExchangeRate> client) throws Exception {
        Mono<ExchangeRate> response=service.update(identityDocument,  client);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }


}
