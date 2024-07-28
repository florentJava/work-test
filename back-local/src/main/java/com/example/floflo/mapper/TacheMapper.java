package com.example.floflo.mapper;

import org.mapstruct.Mapper;

import com.example.floflo.dto.TacheDto;
import com.example.floflo.model.Tache;


@Mapper(componentModel = "spring")
public interface TacheMapper {
    
    TacheDto toDto(Tache tache);
    Tache toEntity(TacheDto tacheDto);


}
