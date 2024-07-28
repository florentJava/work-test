package com.example.floflo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.floflo.dto.TacheDto;

import com.example.floflo.service.TacheService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/taches")
@RequiredArgsConstructor

public class TacheController {

    private final TacheService tacheService;


    @GetMapping("")
    public ResponseEntity<List<TacheDto>> getMethodName() {
        return ResponseEntity.ok( tacheService.getAllTaches());
    }


    @GetMapping("/{id}")
    public TacheDto getById(@PathVariable Long id) {
        return tacheService.getTache(id);
    }


    @PostMapping("")
    public TacheDto saveTache(@RequestBody TacheDto tache) {
        //TODO: process POST request

        TacheDto tacheDto = tacheService.saveTache(tache);

        return tacheDto;
    }


    @DeleteMapping("/{id}")
    public void deleTaches(@PathVariable Long id){
        tacheService.deleteTache(id);
    }
    
    
    
}
