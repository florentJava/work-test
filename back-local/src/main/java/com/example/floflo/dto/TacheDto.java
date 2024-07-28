package com.example.floflo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TacheDto {

    private Long id;

    private String nom;

    private String description;

    private String date;
}
