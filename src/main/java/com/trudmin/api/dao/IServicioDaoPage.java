package com.trudmin.api.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trudmin.api.model.Servicio;

public interface IServicioDaoPage extends JpaRepository<Servicio, Long> {

    public Page<Servicio> findAll(Pageable pageable);
    
}
