package com.banco.product.controller;

import com.banco.product.model.Product;
import com.banco.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Product> findAll() {
        return service.findAll();
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object> create(@RequestBody Product product) {
        Mono<Product> response=service.create(product);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{id}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Integer id,
                                          @RequestBody Mono<Product> product) throws Exception {
        Mono<Product> response=service.update(id,  product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }

}
