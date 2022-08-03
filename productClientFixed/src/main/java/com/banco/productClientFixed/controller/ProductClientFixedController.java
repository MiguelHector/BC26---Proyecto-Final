package com.banco.productClientFixed.controller;

import com.banco.productClientFixed.model.ProductClientFixed;
import com.banco.productClientFixed.service.IProductClientFixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productClientFixed")
public class ProductClientFixedController {

    @Autowired
    private IProductClientFixedService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<ProductClientFixed> findAll() {
        return service.findAll();
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object> create(@RequestBody ProductClientFixed productClientFixed) {
        Mono<ProductClientFixed> response=service.create(productClientFixed);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{id}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Integer id,
                                          @RequestBody Mono<ProductClientFixed> productClientFixed) throws Exception {
        Mono<ProductClientFixed> response=service.update(id,  productClientFixed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }

}
