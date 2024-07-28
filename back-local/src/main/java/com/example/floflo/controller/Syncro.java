package com.example.floflo.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.floflo.dto.TacheDto;
import com.example.floflo.model.Action;
import com.example.floflo.model.Tache;
import com.example.floflo.service.ActionService;
import com.example.floflo.service.TacheService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.util.List;
import org.springframework.http.HttpEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/synchro")
@RequiredArgsConstructor
public class Syncro {

    private final TacheService tacheService;
    private final RestTemplate restTemplate;
    private final ActionService actionService;
    
    

    @PostMapping("/taches")
    public TacheDto saveTache(@RequestBody TacheDto tache) {
        //TODO: process POST request


        //Enregistrer la tache
        
        TacheDto tacheDto = tacheService.saveTache(tache);

        //Faire la requette sur le serveur distant
        String url = "http://localhost:9003/taches";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<TacheDto> request = new HttpEntity<>(tacheDto, headers);
        
        restTemplate.exchange(url, HttpMethod.POST, request, TacheDto.class);
        
        //Renvoyer la taches enregistrer
        return tacheDto;
    }


    @DeleteMapping("/taches/{id}")
    public void deleTaches(@PathVariable Long id){

        //Suprimmer la tache en local
        tacheService.deleteTache(id);

        //Faire la requette sur le serveur distant
        String url = "http://localhost:9003/taches/"+id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<TacheDto> request = new HttpEntity<>(headers);

        restTemplate.exchange(url, HttpMethod.DELETE, request, Void.class);
    }


    @GetMapping("")
    public void syncro() {
        //TODO: process PUT request


        List<Action> actions = actionService.getAllActions();

        String url = "http://localhost:9003/taches";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        for (Action action : actions) {
            if (action.getActionType() == 0) {

                TacheDto tacheDto = tacheService.getTache(action.getRessourceId());

                
                //Faire la requette sur le serveur distant
                HttpEntity<TacheDto> request = new HttpEntity<>(tacheDto, headers);
                
                restTemplate.exchange(url, HttpMethod.POST, request, TacheDto.class);

                actionService.deleteAction(action.getId());
            }else{

                HttpEntity<TacheDto> request = new HttpEntity<>( headers);
                
                restTemplate.exchange(url+"/"+action.getRessourceId(), HttpMethod.DELETE, request, Void.class);

                actionService.deleteAction(action.getId());
            }
        }
        
        
    }


}
