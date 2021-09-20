package com.trudmin.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "rol")
public class Rol implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
    private long id;

    @Column(unique = true, length = 20)
    private String nombreRol;
    
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    public Rol() {
    	
    }
    
	public Rol(long id, String nombreRol, List<Usuario> usuarios) {
		super();
		this.id = id;
		this.nombreRol = nombreRol;
		this.usuarios = usuarios;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
    
	private static final long serialVersionUID = 1L;

}
