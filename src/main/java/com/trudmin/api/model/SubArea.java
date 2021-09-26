package com.trudmin.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subarea")
public class SubArea implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subarea_id")
	private long subAreaId;
	
	private String subArea;
	
	@ManyToOne()
    @JoinColumn(name = "area_id")
	private Area area;
	
	//@OneToOne(mappedBy = "sub_area", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	//private Empleado empleado;
	

	private static final long serialVersionUID = 1L;

}
