package com.trudmin.api.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trudmin.api.model.Empleado;

public interface IEmpleadoDaoPage extends JpaRepository<Empleado, Long> {

    public Page<Empleado> findAll(Pageable pageable);

}
