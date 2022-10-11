package com.trudmin.api.dao;

import java.util.List;

import com.trudmin.api.model.Area;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Puesto;
import com.trudmin.api.model.SubArea;
import com.trudmin.api.model.Usuario;

public interface ICatalogoDao {

	List<Area> obtenerAreas();
	
	List<SubArea> obtenerSubareas();
	
	List<Puesto> obtenerPuestos();
	
	List<Empleado> obtenerEmpleados();

	List<Usuario> obtenerUsuarios();

}
