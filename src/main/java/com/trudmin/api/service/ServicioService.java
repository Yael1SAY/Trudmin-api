package com.trudmin.api.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IEmpleadoDao;
import com.trudmin.api.dao.IServicioDao;
import com.trudmin.api.dto.ServicioDTO;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Servicio;

@Service
public class ServicioService {

	private static final Logger LOG = Logger.getLogger(ServicioService.class.getName());

	@Autowired
	IServicioDao servicioDao;
	
	@Autowired
	IEmpleadoDao empleadoDao;

	public List<Servicio> obtenerServicioPorPeriodo(String periodo) {
		List<Servicio> servicios = servicioDao.obtenerServicioPorPeriodo(periodo);
		LOG.info("Servicios: " + servicios.size());
		return servicios;
	}

	public Servicio crearServicioComprador(ServicioDTO serviciodto) {
		Servicio servicio = new Servicio();
		try {
			Empleado empleado = empleadoDao.obtenerEmpleadoPorId(serviciodto.getEmpleadoId());
			servicio.setAhorro(serviciodto.getAhorro());
			servicio.setAnio(serviciodto.getAnio());
			servicio.setCapturaTiempo(serviciodto.getCapturaTiempo());
			servicio.setCriterio(serviciodto.getCriterio());
			servicio.setDiasOC(serviciodto.getDiasOC());
			servicio.setDiasSP(serviciodto.getDiasSP());
			servicio.setDiscrecional(serviciodto.getDiscrecional());
			servicio.setEmpleado(empleado);
			servicio.setMes(serviciodto.getMes());
			servicio.setPeriodo(serviciodto.getPeriodo());
			servicio.setTotal(serviciodto.getTotal());
			servicio.setTotalOC(serviciodto.getTotalOC());
			servicio.setTotalSolPed(serviciodto.getTotalSolPed());
			Servicio servicoComp = servicioDao.crearServicioComprador(servicio);
			return servicoComp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Servicio updateServicio (ServicioDTO servicioDto) {
		Servicio servicio = new Servicio();
		try {
			Empleado empleado = empleadoDao.obtenerEmpleadoPorId(servicioDto.getEmpleadoId());
			servicio.setIdServicio(servicioDto.getIdServicio());
			servicio.setAhorro(servicioDto.getAhorro());
			servicio.setAnio(servicioDto.getAnio());
			servicio.setCapturaTiempo(servicioDto.getCapturaTiempo());
			servicio.setCriterio(servicioDto.getCriterio());
			servicio.setDiasOC(servicioDto.getDiasOC());
			servicio.setDiasSP(servicioDto.getDiasSP());
			servicio.setDiscrecional(servicioDto.getDiscrecional());
			servicio.setEmpleado(empleado);
			servicio.setMes(servicioDto.getMes());
			servicio.setPeriodo(servicioDto.getPeriodo());
			servicio.setTotal(servicioDto.getTotal());
			servicio.setTotalOC(servicioDto.getTotalOC());
			servicio.setTotalSolPed(servicioDto.getTotalSolPed());
			Servicio servicoComp = servicioDao.crearServicioComprador(servicio);
			return servicoComp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public long eliminarServicio(long idServicio) {
		long idServicioElimainado = servicioDao.elimiarServicio(idServicio);
		return idServicioElimainado;
	}

}
