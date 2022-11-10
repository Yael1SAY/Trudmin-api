package com.trudmin.api.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Usuario;

public interface IEmpleadoDaoPage extends JpaRepository<Empleado, Long> {

    public Page<Empleado> findAll(Pageable pageable);

    public Empleado findByUsuario(Usuario usuario);

    public Empleado findByClave(String clave);

}
