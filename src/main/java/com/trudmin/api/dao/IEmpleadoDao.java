package com.trudmin.api.dao;

import java.util.List;

import com.trudmin.api.model.Empleado;

public interface IEmpleadoDao {
	
	Empleado obtenerEmpleadoPorId (long empleadoId);
	
	List<Empleado> obtenerEmpleados();

}
