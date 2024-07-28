package com.example.floflo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.floflo.dto.TacheDto;
import com.example.floflo.model.Action;
import com.example.floflo.service.ActionService;
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
    private final ActionService actionService;


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

        Action action = Action.builder()
            .ressourceId(tacheDto.getId())
            .actionType(0)
            .build();

        actionService.saveAction(action);

        return tacheDto;
    }


    @DeleteMapping("/{id}")
    public void deleTaches(@PathVariable Long id){


        Action action = Action.builder()
            .ressourceId(id)
            .actionType(1)
            .build();

        actionService.saveAction(action);

        tacheService.deleteTache(id);
    }
    
    
    
}
