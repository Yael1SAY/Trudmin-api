package com.trudmin.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trudmin.api.dao.IUsuarioAuthDao;
import com.trudmin.api.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceAuth implements UserDetailsService, IUsuarioServiceAuth{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceAuth.class);

    @Autowired
    private IUsuarioAuthDao usuarioDao;
    
	//@Autowired
    //private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
    	logger.info("NombreUsuario: " + nombreUsuario);
        Usuario usuario = usuarioDao.findByUsername2(nombreUsuario);
        logger.info("Usuario: " + usuario);

        if (usuario == null){
            logger.error("Error en el Login: no existe el Usuario con Nombre de usuario: " + nombreUsuario + " en el sistema");
            throw new UsernameNotFoundException("Error en el Login: no existe el Usuario con nombre de usuario : " + nombreUsuario + " en el sistema");
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombreRol()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());
        UserDetails userDetails = new User(usuario.getNombreUsuario(), usuario.getPassword(), usuario.isEstatus(), true, true, true, authorities);
        return userDetails;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername2(String nombreUsuario) {
        return usuarioDao.findByUsername2(nombreUsuario);
    }

}
