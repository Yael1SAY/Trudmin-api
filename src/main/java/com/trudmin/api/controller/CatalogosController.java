package com.trudmin.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trudmin.api.dto.AreaDto;
import com.trudmin.api.dto.CatalogClaveEmpleadoDTO;
import com.trudmin.api.dto.CatalogUsuariosDto;
import com.trudmin.api.dto.PuestoDTO;
import com.trudmin.api.dto.SubAreaDto;
import com.trudmin.api.service.CatalogoService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("catalogos")
public class CatalogosController {
	
	@Autowired
	CatalogoService catalogoService;
	
	@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/areas", method = RequestMethod.GET)
    List<AreaDto> obtenerAreas(){
        List<AreaDto> areas = catalogoService.obtenerAreas();
        return areas;
    }
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/subAreas", method = RequestMethod.GET)
	List<SubAreaDto> obtenerSubAreas() {
		List<SubAreaDto> subAreas = catalogoService.obtenerSubAreas();
		return subAreas;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/puestos", method = RequestMethod.GET)
	List<PuestoDTO> obtenerPuestos() {
		List<PuestoDTO> puestos = catalogoService.obtenerPuestos();
		return puestos;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/clavesEmpleado", method = RequestMethod.GET)
	List<CatalogClaveEmpleadoDTO> obtenerCatalogoClavesEmpl() {
		List<CatalogClaveEmpleadoDTO> clavesEmpleado = catalogoService.obtenerClavesEmpleados();
		return clavesEmpleado;
	}


	@RequestMapping(value = "/pruebaMaven", method = RequestMethod.GET)
	String pruebaMaven() {
		// List<CatalogClaveEmpleadoDTO> clavesEmpleado = catalogoService.obtenerClavesEmpleados();
		return "Hola maven";
	}

	@GetMapping("/catalogoUsuarios")
	List<CatalogUsuariosDto>obtenerCatalogUsuario() {
		List<CatalogUsuariosDto> catalogoUsuarios = catalogoService.obtenerUsuarios();
		return catalogoUsuarios;
	}

}
