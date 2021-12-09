package com.trudmin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.model.Empleado;
import com.trudmin.api.service.EmpleadoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("empleado")
public class EmpleadoController {
	
	@Autowired
	EmpleadoService empleadoService;
	
	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/obtenerEmpleados", method = RequestMethod.GET)
    List<Empleado> obtenerUsuarios(){
        List<Empleado> empleado = empleadoService.obtenerEmpleados();
        return empleado;
    }

}
