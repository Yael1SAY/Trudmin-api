package com.trudmin.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IEmpleadoDao;
import com.trudmin.api.dao.IServicioDao;
import com.trudmin.api.dto.ServicioCreateDTO;
import com.trudmin.api.dto.ServicioDTO;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Servicio;
import org.modelmapper.ModelMapper;

@Service
public class ServicioService {

	private static final Logger LOG = Logger.getLogger(ServicioService.class.getName());

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	IServicioDao servicioDao;

	@Autowired
	IEmpleadoDao empleadoDao;

	public List<ServicioDTO> obtenerServicios() {
		List<Servicio> servicios = servicioDao.obtenerServicios();
		List<ServicioDTO> serviciosDTO = new ArrayList<>();

		for (Servicio servicio : servicios) {
			ServicioDTO servicioDTO = new ServicioDTO();
			modelMapper.map(servicio, servicioDTO);
			serviciosDTO.add(servicioDTO);
		}
		return serviciosDTO;
	}

	public List<Servicio> obtenerServicioPorPeriodo(String periodo) {
		List<Servicio> servicios = servicioDao.obtenerServicioPorPeriodo(periodo);
		LOG.info("Servicios: " + servicios.size());
		return servicios;
	}

	public ServicioCreateDTO crearServicioComprador(ServicioCreateDTO servicioDTO) {
		LOG.info("Servicio entrada: " + servicioDTO.toString());
		Servicio servicio = new Servicio();
		Empleado empleado = empleadoDao.obtenerEmpleadoPorId(servicioDTO.getEmpleadoId());
		modelMapper.map(servicioDTO, servicio);
		servicio.setEmpleado(empleado);
		servicio.setIdServicio(UUID.randomUUID().hashCode());

		LOG.info("Servicio convertido a model: " + servicio.toString());

		Servicio servicoComp = servicioDao.crearServicioComprador(servicio);

		ServicioCreateDTO servicioRespDTO = new ServicioCreateDTO();

		modelMapper.map(servicoComp, servicioRespDTO);

		LOG.info("Servicio respuesta convertido a DTO: " + servicio.toString());

		return servicioRespDTO;
	}

	public Servicio updateServicio(ServicioDTO servicioDto) {
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

	public List<ServicioDTO> obtenerServiciosCompradorPeriodo(long empleadoId, int anio) {
		List<ServicioDTO> listServiciosDto = new ArrayList<ServicioDTO>();
		List<Servicio> serviciosResponse = servicioDao.obtenerServiciosCompradorPeriodo(empleadoId, anio);
		for (Servicio servicioResp : serviciosResponse) {
			ServicioDTO servicioDto = new ServicioDTO();
			modelMapper.map(servicioResp, servicioDto);
			listServiciosDto.add(servicioDto);
		}
		return listServiciosDto;
	}

	public ServicioDTO obtenerServicioPorId(long servicioId) {
		Servicio servicio = servicioDao.obtenerServicioPorId(servicioId);
		ServicioDTO servicioDto = new ServicioDTO();
		modelMapper.map(servicio, servicioDto);
		return servicioDto;
	}

}
