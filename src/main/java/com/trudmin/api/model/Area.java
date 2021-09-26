package com.trudmin.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "area")
public class Area implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_id")
	private long areaId;
	
	private String area;
	
	//@JsonIgnore
	//@OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<SubArea> subArea;
	
	private static final long serialVersionUID = 1L;

}
