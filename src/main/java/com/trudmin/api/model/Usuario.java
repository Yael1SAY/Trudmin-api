package com.trudmin.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@Column(name = "user_id")
	@JsonIgnore
	private long id;

	@Column(name = "nombre_usuario")
	@NotNull(message = "El nombre de usuario no puede ser nulo")
	private String nombreUsuario;

	@Column(nullable = true)
	@NotNull(message = "La contraseña no puede ser nula")
	@Size(min = 8, message = "La contraseña debe contener almenos 8 caracteres")
	private String password;

	@Column(name = "nombre")
	@NotNull(message = "El nombre no puede ser nulo")
	private String nombre;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "apellido_paterno")
	@NotNull(message = "El Apellidoo paterno no puede ser nulo")
	private String apellidoPaterno;

	@Column(name = "email", unique = true)
	@NotNull(message = "El email no puede ser nulo")
	private String email;

	@JsonIgnore
	@Column(name = "fecha_cracion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@JsonIgnore
	@Column(name = "status")
	private boolean estatus;

	@JsonIgnore
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "usuarios_roles", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "rol_id") })
	private List<Rol> roles;

	private static final long serialVersionUID = 1L;

}
