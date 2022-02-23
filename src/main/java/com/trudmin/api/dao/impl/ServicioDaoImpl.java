package com.trudmin.api.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trudmin.api.dao.IServicioDao;
import com.trudmin.api.model.Servicio;

@Transactional
@Repository
public class ServicioDaoImpl implements IServicioDao{
	
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
	public List<Servicio> obtenerServicios() {
		final String LISTAR_SERVICIOS = "Select s From Servicio s ";
        return entityManager.createQuery(LISTAR_SERVICIOS, Servicio.class).getResultList();
	}

	@Override
	public List<Servicio> obtenerServicioPorPeriodo(String periodo) {
		final String LISTAR_SERVICIOS = "Select s From Servicio s where s.periodo = :periodo";
        return entityManager.createQuery(LISTAR_SERVICIOS, Servicio.class).setParameter("periodo", periodo).getResultList();
	}
	
	@Override
	public Servicio crearServicioComprador(Servicio servicio) {
		Servicio servicioResponse = new Servicio();
		try {
			servicioResponse = entityManager.merge(servicio);
			entityManager.getTransaction().commit();
			return servicioResponse;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			return servicioResponse;
		}
	}
	
	public Servicio actualizarServicio(Servicio servicio) {
		return servicio;
	}

	@Override
	public long elimiarServicio(long idServicio) {
		Servicio servicio = entityManager.find(Servicio.class, idServicio);
		entityManager.remove(servicio);
		return idServicio;
	}

	@Override
	public List<Servicio> obtenerServiciosCompradorPeriodo(long empleadoid, int anio) {
		final String LISTAR_SERVICIOS = "Select s From Servicio s where s.empleado.empleadoId = :empleado_id AND s.anio = :anio";
        return entityManager.createQuery(LISTAR_SERVICIOS, Servicio.class)
        		.setParameter("empleado_id", empleadoid)
        		.setParameter("anio", anio)
        		.getResultList();
	}

}
