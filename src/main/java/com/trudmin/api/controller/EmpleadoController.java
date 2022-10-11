package com.trudmin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.dto.EmpleadoDTO;
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

    @Secured("ROLE_ADMIN")
    @GetMapping("/obtenerEmpleados/page/{page}/{size}")
    Page<EmpleadoDTO> obtenerUsuariosPage(@PathVariable Integer page, @PathVariable Integer size){
        Page<EmpleadoDTO> empleado = empleadoService.obtenerEmpleadosPage(PageRequest.of(page, size));
        return empleado;
    }

}
