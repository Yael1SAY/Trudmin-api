package com.trudmin.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	@JsonIgnore
	@Column(nullable = true)
    private String password;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido_materno")
	private String apellidoMaterno;
	
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "fecha_cracion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@Column(name = "status")
	private boolean estatus;
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = {@JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "rol_id")}
    )
    private List<Rol> roles;
	
}
