package com.banco.movement.controller;

import com.banco.movement.model.Movement;
import com.banco.movement.service.IMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movement")
public class MovementController {
    @Autowired
    private IMovementService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Movement> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/get/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<Movement> findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody Movement client) {
        Mono<Movement> response=service.create(client);
        //return response;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = { "/update/{identityDocument}" }, produces = { "application/json" })
    public ResponseEntity<Object>  update(@PathVariable("id") Long id,
            @RequestBody Mono<Movement> client) throws Exception {
        Mono<Movement> response=service.update(id,  client);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = { "delete/{id}" })
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        service.delete(id);
    }


}
