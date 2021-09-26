package com.trudmin.api.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IServicioDao;
import com.trudmin.api.model.Servicio;

@Service
public class ServicioService {
	
	private static final Logger LOG = Logger.getLogger(ServicioService.class.getName());

    @Autowired
    IServicioDao ServicioDao;
    
    public List<Servicio> obtenerServicioPorPeriodo(String periodo){
        List<Servicio> servicios = ServicioDao.obtenerServicioPorPeriodo(periodo);
        LOG.info("Servicios: " + servicios.size());
        return servicios;
    }

}
