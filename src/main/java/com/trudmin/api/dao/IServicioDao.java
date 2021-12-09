package com.trudmin.api.dao;

import java.util.List;

import com.trudmin.api.model.Servicio;

public interface IServicioDao {
	
	List<Servicio>obtenerServicios();
	
	List<Servicio> obtenerServicioPorPeriodo(String periodo);
	
	Servicio crearServicioComprador(Servicio servicio);
	
	long elimiarServicio(long idServicio);

}
