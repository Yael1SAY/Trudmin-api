package com.trudmin.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import java.util.logging.Logger;

import com.trudmin.api.dto.ServicioCreateDTO;
import com.trudmin.api.dto.ServicioDTO;
import com.trudmin.api.model.Servicio;
import com.trudmin.api.service.ServicioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("servicio")
public class ServicioCompradorController {

	// private static final Logger LOG = Logger.getLogger(ServicioService.class.getName());

	@Autowired
	ServicioService servicioService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/obtenerServicios", method = RequestMethod.GET)
	ResponseEntity<?> obtenerServicios() {
		List<ServicioDTO> servicio = new ArrayList<>();
		Map<String, Object> response = new HashMap<>(); 
		try {
			servicio = servicioService.obtenerServicios();
		} catch (DataAccessException e) {
            response.put("message", "Error al obtener la lista de productividad ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            response.put("status", 404);
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("message", "Error en el servidor, contactar al administrador ");
            response.put("error", e.getMessage());
            response.put("ststus", 404);
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("data", servicio);
        response.put("message", "Se obtuvo correctamente la lista de productividad");
        response.put("status", 200);
		
		return new ResponseEntity< Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/obtenerServiciosPorCompradorAnio", method = RequestMethod.GET)
	List<ServicioDTO> obtenerServiciosPorCompradorAnio(@RequestParam long empleadoId, @RequestParam int anio) {
		List<ServicioDTO> servicio = servicioService.obtenerServiciosCompradorPeriodo(empleadoId, anio);
		return servicio;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/serviciosPeriodo/{periodo}", method = RequestMethod.GET)
	List<Servicio> obtenerServicioPorPeriodo(@PathVariable String periodo) {
		List<Servicio> servicio = servicioService.obtenerServicioPorPeriodo(periodo);
		return servicio;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/crearServicio", method = RequestMethod.POST)
	ResponseEntity<?> crearServicioComprador(@RequestBody ServicioCreateDTO servicio) {
		ServicioCreateDTO servicioComp = new ServicioCreateDTO();
		Map<String, Object> response = new HashMap<>(); 
		try {
			servicioComp = servicioService.crearServicioComprador(servicio);
		} catch (DataAccessException e) {
            response.put("message", "Error al crear nueva productividad");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            response.put("status", 404);
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("message", "Error en el servidor, contactar al administrador ");
            response.put("error", e.getMessage());
            response.put("ststus", 404);
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
		response.put("data", servicioComp);
        response.put("message", "Se creo correctamente nueva productividad");
        response.put("status", 200);
		
		return new ResponseEntity< Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/actualizarServicio", method = RequestMethod.PUT)
	Servicio actualizarServicio(@RequestBody ServicioDTO servicioDto) {
		Servicio servicioComp = servicioService.updateServicio(servicioDto);
		return servicioComp;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/bajaServicio/{id}", method = RequestMethod.DELETE)
	long elimianrServicio(@PathVariable long id) {
		// long servicioIdLong = servicioId;
		long servicioIdEliminado = servicioService.eliminarServicio(id);
		return servicioIdEliminado;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/obtenerServicio/{id}", method = RequestMethod.GET)
	ServicioDTO obtenerServicioPorId(@PathVariable long id) {
		ServicioDTO servicioDTO = servicioService.obtenerServicioPorId(id);
		return servicioDTO;
	}

}
