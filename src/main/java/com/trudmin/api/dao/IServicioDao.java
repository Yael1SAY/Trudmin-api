package com.trudmin.api.dao;

import java.util.List;

import com.trudmin.api.model.Servicio;

public interface IServicioDao {
	
	List<Servicio> obtenerServicioPorPeriodo(String periodo);

}
