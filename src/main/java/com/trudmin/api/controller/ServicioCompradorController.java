package com.trudmin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.model.Servicio;
import com.trudmin.api.service.ServicioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("servicio")
public class ServicioCompradorController {
	
	@Autowired
    ServicioService servicioService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{periodo}", method = RequestMethod.GET)
    List<Servicio> obtenerServicioPorPeriodo(@PathVariable String periodo){
        List<Servicio> servicio = servicioService.obtenerServicioPorPeriodo(periodo);
        return servicio;
    }

}
