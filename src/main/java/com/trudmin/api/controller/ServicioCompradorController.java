package com.trudmin.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import java.util.logging.Logger;

import com.trudmin.api.dto.ServicioCreateDTO;
import com.trudmin.api.dto.ServicioDTO;
import com.trudmin.api.dto.ServicioProductividadDTO;
import com.trudmin.api.exceptions.GenericResponse;
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
    @GetMapping("/obtenerServicios/page/{page}/{size}")
    Page<ServicioDTO> obtenerUsuariosPage(@PathVariable Integer page, @PathVariable Integer size) {
        Page<ServicioDTO> servicio = servicioService.obtenerServiciosPage(PageRequest.of(page, size));
        return servicio;
    }

	@Secured("ROLE_ADMIN")
    @GetMapping("/obtenerServiciosProductividad/{empleadoId}/{anio}")
    ResponseEntity<?> obtenerServicioProductividad(@PathVariable Integer empleadoId, @PathVariable Integer anio) {
		GenericResponse<List<ServicioProductividadDTO>> response = new GenericResponse<List<ServicioProductividadDTO>>();
        List<ServicioProductividadDTO> servicio = servicioService.obtenerServicioProductividad(empleadoId, anio);
		response.setData(servicio);
		response.setMessage("Se obtuvieron correctamnete los datos");
		response.setStatus(200);
        return new ResponseEntity<GenericResponse<List<ServicioProductividadDTO>>>(response, HttpStatus.OK);
    }

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/obtenerServicios", method = RequestMethod.GET)
	ResponseEntity<?> obtenerServicios() {
		List<ServicioDTO> servicio = new ArrayList<>();
		Map<String, Object> response = new HashMap<>(); 
		try {
			servicio = servicioService.obtenerServicios();
		} catch (DataAccessException e) {
            response.put("message", "Error al obtener la lista de productividad ");
            response.put("error", e.getMostSpecificCause().getMessage());
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
            response.put("error", e.getMostSpecificCause().getMessage());
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
