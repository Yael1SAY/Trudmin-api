package com.trudmin.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empleado_id")
	private long empleadoId;
	
	private String clave;
	
	private String jefe;
	
	private String puesto;
		
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@JoinColumn(name = "subarea_id")
    @OneToOne(fetch = FetchType.LAZY)
    private SubArea subArea;

}
