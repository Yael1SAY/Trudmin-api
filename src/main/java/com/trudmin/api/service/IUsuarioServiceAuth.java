package com.trudmin.api.service;

import org.springframework.data.jpa.repository.Query;

import com.trudmin.api.model.Usuario;

public interface IUsuarioServiceAuth {
	
	@Query("select c from Usuario c where c.clave = ?1")
    public Usuario findByUsername2(String nombreUsuario);

}
