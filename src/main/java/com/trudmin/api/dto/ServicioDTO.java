package com.trudmin.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServicioDTO {
	
	private long idServicio;
	private byte ahorro;
	private int anio;
	private byte capturaTiempo;
	private int criterio;
	private int diasOC;
	private int diasSP;
	private int discrecional;
	private String mes;
	private String periodo;
	private int total;
	private int totalOC;
	private int totalSolPed;
	private int empleadoId;
	private EmpleadoResum empleado;

}
