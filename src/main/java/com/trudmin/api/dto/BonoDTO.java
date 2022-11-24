package com.trudmin.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BonoDTO {
    private String mes;
    private String periodo;
    private int anio;
    private int total;
    private EmpleadoDTO empleado;
}