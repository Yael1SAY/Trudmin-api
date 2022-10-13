package com.trudmin.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IPuestoDao;
import com.trudmin.api.model.Puesto;

@Service
public class PuestoService {

    @Autowired
    IPuestoDao puestoDao;

    public Puesto findAllById(long puestoId) {
        return puestoDao.findById(puestoId).get();
    }
}
