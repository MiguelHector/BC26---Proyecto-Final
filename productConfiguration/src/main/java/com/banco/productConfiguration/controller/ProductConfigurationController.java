package com.banco.productConfiguration.controller;

import com.banco.productConfiguration.model.ProductConfiguration;
import com.banco.productConfiguration.service.IProductConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productConfiguration")
public class ProductConfigurationController {
    @Autowired
    private IProductConfigurationService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<ProductConfiguration> findAll() {
        return service.findAll();
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object> create(@RequestBody ProductConfiguration productConfiguration) {
        Mono<ProductConfiguration> response=service.create(productConfiguration);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{id}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Integer id,
                                          @RequestBody Mono<ProductConfiguration> productConfiguration) throws Exception {
        Mono<ProductConfiguration> response=service.update(id,  productConfiguration);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
    }

}
