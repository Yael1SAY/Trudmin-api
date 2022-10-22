package com.trudmin.api.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Servicio;

public interface IServicioDaoPage extends JpaRepository<Servicio, Long> {

    public Page<Servicio> findAll(Pageable pageable);

    public List<Servicio> findByEmpleadoAndAnio(Empleado empleado, int anio);

}
