package com.trudmin.api.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Servicio;

public interface IServicioDaoPage extends JpaRepository<Servicio, Long> {

    public Page<Servicio> findAll(Pageable pageable);

    public Page<Servicio> findByEmpleadoAndAnioOrderByPeriodo(Empleado empleado, int anio, Pageable pageable);

    public List<Servicio> findByEmpleadoAndAnioOrderByPeriodo(Empleado empleado, int anio);

    public Servicio findByEmpleadoAndPeriodo(Empleado empleado, String periodo);

}
