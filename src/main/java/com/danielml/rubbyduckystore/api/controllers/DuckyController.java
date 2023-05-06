package com.danielml.rubbyduckystore.api.controllers;

import com.danielml.rubbyduckystore.api.mappers.DuckyMapper;
import com.danielml.rubbyduckystore.api.models.duckies.*;
import com.danielml.rubbyduckystore.application.service.ducky.IDuckyService;
import com.danielml.rubbyduckystore.domain.models.Ducky;
import com.danielml.rubbyduckystore.infrastructure.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/duckies")
@Slf4j
public class DuckyController {

    private final IDuckyService duckyService;

    public DuckyController(IDuckyService duckyService) {
        this.duckyService = duckyService;
    }

    @PostMapping
    public ResponseEntity<CreateDuckyResult> createDucky(@RequestBody CreateDuckyRequest duckyRequest) {
        Ducky ducky = DuckyMapper.toDucky(duckyRequest);

        return new ResponseEntity<>(DuckyMapper.toCreateDuckyResult(this.duckyService.saveDucky(ducky)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<ListDuckyResult>> getDuckies() {
        Iterable<ListDuckyResult> duckies = DuckyMapper.toDuckiesResult(this.duckyService.getDuckies());

        return new ResponseEntity<>(duckies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDuckyResult> getProduct(@PathVariable Integer id) {
        GetDuckyResult duckyResult = DuckyMapper.toGetDuckyResult(this.duckyService.getDuckyById(id));

        return new ResponseEntity<>(duckyResult, HttpStatus.OK);
    }

    @PutMapping
    public void deleteProduct(@RequestBody UpdateDuckyRequest updateDuckyRequest) {
        this.duckyService.updateDucky(updateDuckyRequest.id(), updateDuckyRequest.quantity(), updateDuckyRequest.price());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        this.duckyService.deleteDucky(id);
    }
}
