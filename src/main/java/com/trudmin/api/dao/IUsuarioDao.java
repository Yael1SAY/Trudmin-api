package com.trudmin.api.dao;

import java.util.List;

import com.trudmin.api.model.Usuario;

public interface IUsuarioDao {

	List<Usuario> obtenerUsuario();

	Usuario obtenerUsuarioId(long id);
	
	Usuario findByUserName(String nombreUsuario);

	Usuario registrarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Usuario usuario);

    void eliminarUsuario(long id);
}
