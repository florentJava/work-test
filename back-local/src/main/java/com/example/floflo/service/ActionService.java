package com.example.floflo.service;

import java.util.List;

import com.example.floflo.model.Action;
import com.example.floflo.repository.ActionRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ActionService {
    
    private final ActionRepository actionRepository;


    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public void saveAction(Action action) {
        actionRepository.save(action);
    }

    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }

}
