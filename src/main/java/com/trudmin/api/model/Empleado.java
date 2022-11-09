package com.trudmin.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

	@Id
	@Column(name = "empleado_id")
	private long empleadoId;
	
	@Column(name = "clave", unique = true)
	private String clave;
			
	@Column(name = "fecha_inicio")
	private Date fechaInicio;
	
	@Column(name = "fecha_fin")
	private Date fechaFin;
	
	@Column(name = "telefono", unique = true)
	private String telefono;
	
	@Column(name = "status", nullable = false)
	private boolean estatus;
	
	@JoinColumn(name = "user_id")
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Usuario usuario;
	
	@JoinColumn(name = "subarea_id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SubArea subArea;
	
	@JoinColumn(name = "puesto_id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Puesto puesto;

	@JoinColumn(name="jefe_id")
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	private Jefe jefe;

	private static final long serialVersionUID = 1L;

}
