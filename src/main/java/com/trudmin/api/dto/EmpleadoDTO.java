package com.trudmin.api.dto;

import java.util.Date;

import com.trudmin.api.model.Puesto;
import com.trudmin.api.model.SubArea;
import com.trudmin.api.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO {
	
	private long empleadoId;
	private String clave;
	private Date fechaInicio;
	private Date fechaFin;
	private Usuario usuario;
	private SubArea subArea;	
	private Puesto puesto;
	private boolean estatus;
	private String telefono;

}
