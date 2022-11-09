package com.trudmin.api.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trudmin.api.dao.IEmpleadoDao;
import com.trudmin.api.model.Empleado;

@Transactional
@Repository
public class EmpleadoDaoImpl implements IEmpleadoDao{

	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public Empleado obtenerEmpleadoPorId(long empleadoId) {
		Empleado empleado;
		empleado = entityManager.find(Empleado.class, empleadoId);
		return empleado;
	}

	@Override
	public List<Empleado> obtenerEmpleados() {
		final String LISTAR_EMLEADOS = "Select u From Empleado u";
		List<Empleado> empleados = entityManager.createQuery(LISTAR_EMLEADOS, Empleado.class).getResultList();
        return empleados;
	}

}
