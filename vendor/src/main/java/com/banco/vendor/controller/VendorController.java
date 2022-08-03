package com.banco.vendor.controller;

import com.banco.vendor.model.Vendor;
import com.banco.vendor.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private IVendorService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Vendor> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/get/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<Vendor> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody Vendor client) {
        Mono<Vendor> response=service.create(client);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{identityDocument}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Long id,
            @RequestBody Mono<Vendor> client) throws Exception {
        Mono<Vendor> response=service.update(id,  client);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }


}
