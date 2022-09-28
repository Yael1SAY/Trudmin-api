package com.trudmin.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ServicioCreateDTO {

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
    
}
