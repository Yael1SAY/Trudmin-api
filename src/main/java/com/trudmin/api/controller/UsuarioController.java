package com.trudmin.api.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.model.Usuario;
import com.trudmin.api.service.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	private static final Logger LOG = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    UsuarioService usuarioService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Usuario> obtenerUsuario(){
        List<Usuario> usuario = usuarioService.obtenerUsuarios();
        LOG.info("usuarios; " + usuario);
        return usuario;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Usuario obtenerUsuarioId(@PathVariable long id){
        return usuarioService.obtenerUsuarioId(id);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Usuario registrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.registrarUsuario(usuario);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Usuario actualizarUsuario(@RequestBody Usuario usuario){
        return usuarioService.actualizarUsuario(usuario);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.PUT)
    void eliminarUsuario(@PathVariable long id){
    	usuarioService.eliminarUsuario(id);
    }

}
