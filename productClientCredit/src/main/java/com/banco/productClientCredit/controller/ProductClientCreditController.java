package com.banco.productClientCredit.controller;

import com.banco.productClientCredit.model.ProductClientCredit;
import com.banco.productClientCredit.service.IProductClientCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productClientCredit")
public class ProductClientCreditController {
    @Autowired
    private IProductClientCreditService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<ProductClientCredit> findAll() {
        return service.findAll();
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object> create(@RequestBody ProductClientCredit productClientCredit) {
        Mono<ProductClientCredit> response=service.create(productClientCredit);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{id}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Integer id,
                                          @RequestBody Mono<ProductClientCredit> productClientCredit) throws Exception {
        Mono<ProductClientCredit> response=service.update(id,  productClientCredit);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }


}
