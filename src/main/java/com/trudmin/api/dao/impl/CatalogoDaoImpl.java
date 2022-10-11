package com.trudmin.api.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trudmin.api.dao.ICatalogoDao;
import com.trudmin.api.model.Area;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Puesto;
import com.trudmin.api.model.SubArea;
import com.trudmin.api.model.Usuario;

@Transactional
@Repository
public class CatalogoDaoImpl implements ICatalogoDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Area> obtenerAreas() {
		final String LISTAR_AREAS = "Select a From Area a";
		List<Area> areas = entityManager.createQuery(LISTAR_AREAS, Area.class).getResultList();
		return areas;
	}

	@Override
	public List<SubArea> obtenerSubareas() {
		final String LISTAR_SUBAREAS = "Select s From SubArea s";
		List<SubArea> subareas = entityManager.createQuery(LISTAR_SUBAREAS, SubArea.class).getResultList();
		return subareas;
	}

	@Override
	public List<Puesto> obtenerPuestos() {
		final String LISTAR_PUESTOS = "Select p From Puesto p";
		List<Puesto> puestos = entityManager.createQuery(LISTAR_PUESTOS, Puesto.class).getResultList();
		return puestos;
	}

	@Override
	public List<Empleado> obtenerEmpleados() {
		final String LISTAR_EMPLEADOS = "Select e From Empleado e";
		List<Empleado> empleados = entityManager.createQuery(LISTAR_EMPLEADOS, Empleado.class).getResultList();
		return empleados;
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		final String LISTAR_USUARIOS = "Select u FROM Usuario u";
		List<Usuario> usuario = entityManager.createQuery(LISTAR_USUARIOS, Usuario.class).getResultList();
		return usuario;
	}

}
