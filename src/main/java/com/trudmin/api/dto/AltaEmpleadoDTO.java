package com.trudmin.api.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AltaEmpleadoDTO {
    private long empleadoId;
	private String clave;
	private Date fechaInicio;
	private Date fechaFin;
	private long usuario;
	private int subArea;	
	private int puesto;
	private boolean estatus;
	private String telefono;
}
