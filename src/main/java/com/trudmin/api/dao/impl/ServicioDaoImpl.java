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
	public List<Servicio> obtenerServicioPorPeriodo(String periodo) {
		final String LISTAR_SERVICIOS = "Select s From Servicio s where s.periodo = :periodo";
        return entityManager.createQuery(LISTAR_SERVICIOS, Servicio.class).setParameter("periodo", periodo).getResultList();
	}

}
