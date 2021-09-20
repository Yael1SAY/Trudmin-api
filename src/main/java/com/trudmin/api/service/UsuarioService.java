package com.trudmin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IUsuarioDao;
import com.trudmin.api.model.Usuario;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UsuarioService {
	
	private static final Logger LOG = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    IUsuarioDao usuarioDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> obtenerUsuarios(){
        List<Usuario> list = usuarioDao.obtenerUsuario();
        return list;
    }

    public Usuario obtenerUsuarioId(long id){
    	Usuario comprador = usuarioDao.obtenerUsuarioId(id);
        if (comprador.isEstatus()){
            return null;
        }
        return comprador;
    }

    public Usuario registrarUsuario(Usuario comprador){
        comprador.setPassword(passwordEncoder.encode(comprador.getPassword()));
        Usuario compradorNew = usuarioDao.registrarUsuario(comprador);
        return compradorNew;
    }

    public Usuario actualizarUsuario(Usuario comprador){
    	Usuario compradorNew = usuarioDao.actualizarUsuario(comprador);
        return compradorNew;
    }

    public void eliminarUsuario(long id){
    	usuarioDao.eliminarUsuario(id);
    }

}
