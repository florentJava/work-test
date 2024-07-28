package com.example.floflo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.floflo.model.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {

    
} 
