package com.trudmin.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private long id;
    private String nombreUsuario;
    @JsonIgnore
    private String password;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String email;
    private Date fechaCreacion;
    private boolean estatus;
}
