package com.trudmin.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable{
	
	@Id
	@Column(name = "id_servicio")
	@JsonIgnore
	private long idServicio;

	@NotNull(message = "El mes no puede ser nulo")
	private String mes;

	@NotNull(message = "El anio no puede ser nulo")
	private int anio;
	
	@NotNull(message = "El periodo no puede ser nulo")
	private String periodo;
	
	@NotNull(message = "El total de Solicitudes de pedido no puede ser nulo")
	@Column(name = "total_Sol_Ped")
	private int totalSolPed;
	
	@NotNull(message = "El total de orden de compra no puede ser nulo")
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
