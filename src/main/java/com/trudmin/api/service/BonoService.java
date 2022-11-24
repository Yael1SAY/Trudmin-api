package com.trudmin.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IEmpleadoDaoPage;
import com.trudmin.api.dao.IServicioDaoPage;
import com.trudmin.api.dto.BonoDTO;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Servicio;

@Service
public class BonoService {

    @Autowired
    IServicioDaoPage servicioDao;

    @Autowired
    IEmpleadoDaoPage empleadoDao;

    ModelMapper modelMapper = new ModelMapper();

    public List<BonoDTO> bonosPorEmpleadoYAnio(long empleadoId, int anio) {
        List<BonoDTO> bonos = new ArrayList<>();
        Empleado empleado;
        List<Servicio> servicios;

        empleado = empleadoDao.getById(empleadoId);
        
        servicios = servicioDao.findByEmpleadoAndAnioOrderByPeriodo(empleado, anio);

        if (servicios.isEmpty()) {
            throw new NoSuchElementException("No se encontraon datos");
        }

        for (Servicio servicio : servicios) {
            BonoDTO bonoDTO = new BonoDTO();
            modelMapper.map(servicio, bonoDTO);
			bonos.add(bonoDTO);
        }
        return bonos;
    }
    
}
