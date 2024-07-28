package com.example.floflo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.floflo.model.Tache;


@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {

    
} 
