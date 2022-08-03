package com.banco.productClientCreditCard.controller;

import com.banco.productClientCreditCard.model.ProductClientCreditCard;
import com.banco.productClientCreditCard.service.IProductClientCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productClientCreditCard")
public class ProductClientCreditCardController {
    @Autowired
    private IProductClientCreditCardService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<ProductClientCreditCard> findAll() {
        return service.findAll();
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object> create(@RequestBody ProductClientCreditCard productClientCredit) {
        Mono<ProductClientCreditCard> response=service.create(productClientCredit);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{id}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Integer id,
                                          @RequestBody Mono<ProductClientCreditCard> productClientCreditCard) throws Exception {
        Mono<ProductClientCreditCard> response=service.update(id,  productClientCreditCard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }

}
