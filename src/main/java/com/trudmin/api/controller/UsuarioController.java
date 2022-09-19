package com.trudmin.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.dto.UsuarioDTO;
import com.trudmin.api.model.Usuario;
import com.trudmin.api.service.UsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private static final Logger LOG = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    UsuarioService usuarioService;

    // @Secured("ROLE_ADMIN")
    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // UsuarioDTO login(@RequestBody UsuarioLoginDTO usuarioLogin){
    // List<Usuario> usuarios = usuarioService.obtenerUsuarios();
    // return usuarios;
    // }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/obtenerUsuarios", method = RequestMethod.GET)
    List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return usuarios;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/obtenerUsuario{id}", method = RequestMethod.GET)
    Usuario obtenerUsuarioId(@PathVariable long id) {
        LOG.info("Id de usuario a obtener: " + id);
        Usuario usuario = new Usuario();
        usuario = usuarioService.obtenerUsuarioId(id);
        return usuario;
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/crearUsuario", method = RequestMethod.POST)
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        UsuarioDTO usuarioDto = null;
        Map<String, Object> response = new HashMap<>(); 
        try {
            usuarioDto = usuarioService.registrarUsuario(usuario);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar el alta del usuario " + usuario.getNombre());
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            response.put("status", 404);
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("message", "Error en el servidor, contactar al administrador ");
            response.put("error", e.getMessage());
            response.put("ststus", 404);
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("data", usuarioDto);
        response.put("message", "El usuario ".concat(usuarioDto.getNombreUsuario()).concat(" se registro correctamnete"));
        response.put("status", 200);
        return new ResponseEntity< Map<String, Object>>(response, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/actualizarUsuario{id}", method = RequestMethod.PUT)
    Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(usuario);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/eliminarUsuatio/{id}", method = RequestMethod.PUT)
    void eliminarUsuario(@PathVariable long id) {
        usuarioService.eliminarUsuario(id);
    }

}
