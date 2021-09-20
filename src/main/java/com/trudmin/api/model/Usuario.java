package com.trudmin.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
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
	
	
	public Usuario() {
		
	}

	public Usuario(long id, String nombreUsuario, String password, String nombre, String apellidoMaterno,
			String apellidoPaterno, String email, Date fechaCreacion, boolean estatus, List<Rol> roles) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.nombre = nombre;
		this.apellidoMaterno = apellidoMaterno;
		this.apellidoPaterno = apellidoPaterno;
		this.email = email;
		this.fechaCreacion = fechaCreacion;
		this.estatus = estatus;
		this.roles = roles;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNombreUsuario() {
		return nombreUsuario;
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidoMaterno() {
		return apellidoMaterno;
	}



	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}



	public String getApellidoPaterno() {
		return apellidoPaterno;
	}



	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public boolean isEstatus() {
		return estatus;
	}



	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}



	public List<Rol> getRoles() {
		return roles;
	}



	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	private static final long serialVersionUID = 1L;

}
