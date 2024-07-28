package com.example.floflo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.floflo.dto.TacheDto;
import com.example.floflo.mapper.TacheMapper;
import com.example.floflo.model.Tache;
import com.example.floflo.repository.TacheRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;

    private final TacheMapper tacheMapper;


    public void deleteTache(Long id) {
        tacheRepository.deleteById(id);
    }


    public TacheDto getTache(Long id) {
        return tacheMapper.toDto(tacheRepository.findById(id).orElse(null));
    }

    public TacheDto saveTache(TacheDto tacheDto) {

        Tache tache = tacheMapper.toEntity(tacheDto);
        return tacheMapper.toDto(tacheRepository.save(tache));
    }

    public TacheDto updateTache(TacheDto tacheDto) {
        Tache tache = tacheMapper.toEntity(tacheDto);
        return tacheMapper.toDto(tacheRepository.save(tache));
    }

    public List<TacheDto> getAllTaches() {
        return tacheRepository.findAll().stream().map(tacheMapper::toDto).toList();
    }

    
}
