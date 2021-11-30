package com.trudmin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.dto.ServicioDTO;
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
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    Servicio crearServicioComprador(@RequestBody ServicioDTO servicio) {
    	Servicio servicioComp = servicioService.crearServicioComprador(servicio);
    	return servicioComp;
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    Servicio actualizarServicio(@RequestBody ServicioDTO servicioDto) {
    	Servicio servicioComp = servicioService.updateServicio(servicioDto);
    	return servicioComp;
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    long elimianrServicio(@PathVariable long id) {
    	//long servicioIdLong = servicioId;
    	long servicioIdEliminado = servicioService.eliminarServicio(id);
    	return servicioIdEliminado;
    }

}
