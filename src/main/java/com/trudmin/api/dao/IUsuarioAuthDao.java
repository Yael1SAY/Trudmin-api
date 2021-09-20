package com.trudmin.api.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trudmin.api.model.Usuario;

@Repository
public interface IUsuarioAuthDao extends CrudRepository<Usuario, Long>{

	@Query("select c from Usuario c where c.nombreUsuario = ?1")
    public Usuario findByUsername2(String nombreUsuario);

}
