package com.trudmin.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServicioProductividadDTO {
	private int anio;
	private int totalOC;
	private int totalSolPed;
	private int empleadoId;
	private String mes;
}
