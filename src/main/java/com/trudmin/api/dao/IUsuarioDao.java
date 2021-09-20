package com.trudmin.api.dao;

import java.util.List;

import com.trudmin.api.model.Usuario;

public interface IUsuarioDao {

	List<Usuario> obtenerUsuario();

	Usuario obtenerUsuarioId(long id);

	Usuario registrarUsuario(Usuario comprador);

    Usuario actualizarUsuario(Usuario comprador);

    void eliminarUsuario(long id);
}
