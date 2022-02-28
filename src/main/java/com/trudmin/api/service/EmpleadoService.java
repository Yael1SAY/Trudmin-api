package com.trudmin.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IEmpleadoDao;
import com.trudmin.api.model.Empleado;

@Service
public class EmpleadoService {
	
	@Autowired
	IEmpleadoDao empleadoDao;
	
	public List<Empleado> obtenerEmpleados() {
		return empleadoDao.obtenerEmpleados();
	}

}
