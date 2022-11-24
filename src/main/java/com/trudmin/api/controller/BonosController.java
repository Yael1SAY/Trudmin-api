package com.trudmin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.dto.BonoDTO;
import com.trudmin.api.exceptions.GenericResponse;
import com.trudmin.api.service.BonoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("bonos")
public class BonosController {

    @Autowired
    BonoService bonosServices;

    @Secured("ROLE_ADMIN")
    @GetMapping("/obtenerBonosPorEmpleadoYAnio/{empleadoId}/{anio}")
    ResponseEntity<?> obtenerBonosPorEmpleadoYAnio(@PathVariable long empleadoId, @PathVariable int anio) {
        List<BonoDTO> bonosDTO;
        bonosDTO = bonosServices.bonosPorEmpleadoYAnio(empleadoId, anio);
        
		GenericResponse<List<BonoDTO>> response = new GenericResponse<>();
        response.setData(bonosDTO);
        response.setMessage("Se obtuvieron correctamente los datos");
        response.setStatus(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
