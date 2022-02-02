package com.trudmin.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio")
	private long idServicio;
	
	//@Column(name = "empleado_id")
	//private int empleadoId;
		
	private String mes;
	private int anio;
	
	private String periodo;
	
	@Column(name = "total_Sol_Ped")
	private int totalSolPed;
	
	@Column(name = "total_OC")
	private int totalOC;
	
	
	private int diasOC;
	private int diasSP;
	private int criterio;
	private int discrecional;

	private byte ahorro;
	
	@Column(name = "captura_tiempo")
	private byte capturaTiempo;
	private int total;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_id")
	private Empleado empleado;

	private static final long serialVersionUID = 1L;

}
