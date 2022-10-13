package com.trudmin.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trudmin.api.model.Puesto;

@Repository
public interface IPuestoDao extends JpaRepository<Puesto, Long>{

    // public Puesto findAllById(int puestoId);
    
}
