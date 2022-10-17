package com.trudmin.api.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.dto.UsuarioDTO;
import com.trudmin.api.exceptions.GenericResponse;
import com.trudmin.api.exceptions.InvalidDataException;
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
    @GetMapping("/obtenerUsuarios")
    List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return usuarios;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/obtenerUsuarios/page/{page}/{size}")
    Page<UsuarioDTO> obtenerUsuariosPage(@PathVariable Integer page, @PathVariable Integer size) {
        Page<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosPage(PageRequest.of(page, size));
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
    @PostMapping("/crearUsuario")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        UsuarioDTO usuarioDto = usuarioService.registrarUsuario(usuario);
        GenericResponse<UsuarioDTO> response = new GenericResponse<UsuarioDTO>();
        response.setData(usuarioDto);
        response.setMessage("El usuario ".concat(usuarioDto.getNombreUsuario()).concat(" se registro correctamnete"));
        response.setStatus(200);
        return new ResponseEntity<GenericResponse<UsuarioDTO>>(response, HttpStatus.OK);
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
