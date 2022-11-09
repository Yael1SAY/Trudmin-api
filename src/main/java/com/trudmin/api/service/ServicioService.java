package com.trudmin.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trudmin.api.dao.IEmpleadoDao;
import com.trudmin.api.dao.IEmpleadoDaoPage;
import com.trudmin.api.dao.IServicioDao;
import com.trudmin.api.dao.IServicioDaoPage;
import com.trudmin.api.dto.ServicioCreateDTO;
import com.trudmin.api.dto.ServicioDTO;
import com.trudmin.api.dto.ServicioProductividadDTO;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Servicio;
import org.modelmapper.ModelMapper;

@Service
public class ServicioService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	IServicioDao servicioDao;

	@Autowired
	IServicioDaoPage servicioDaoPage;

	@Autowired
	IEmpleadoDao empleadoDao;

	@Autowired
	IEmpleadoDaoPage empleadoDaoPage;

	public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
		return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
	}

	public Page<ServicioDTO> obtenerServiciosPage(Pageable pageable) {
		Page<Servicio> entity = servicioDaoPage.findAll(pageable);
		Page<ServicioDTO> usuarioDto = null;
		usuarioDto = mapEntityPageIntoDtoPage(entity, ServicioDTO.class);
		return usuarioDto;
	}

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
		List<Servicio> servicios = null;
		servicios = servicioDao.obtenerServicioPorPeriodo(periodo);
		return servicios;
	}

	@Transactional
	public ServicioCreateDTO crearServicioComprador(ServicioCreateDTO servicioDTO) {
		Servicio servicio = new Servicio();
		Empleado empleado = empleadoDao.obtenerEmpleadoPorId(servicioDTO.getEmpleadoId());
		modelMapper.map(servicioDTO, servicio);
		servicio.setEmpleado(empleado);
		servicio.setIdServicio(UUID.randomUUID().hashCode());

		Servicio servicoComp = servicioDao.crearServicioComprador(servicio);

		ServicioCreateDTO servicioRespDTO = new ServicioCreateDTO();

		modelMapper.map(servicoComp, servicioRespDTO);

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
			Servicio servicoComp;
			servicoComp = servicioDao.crearServicioComprador(servicio);
			return servicoComp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public long eliminarServicio(long idServicio) {
		long idServicioElimainado;
		idServicioElimainado = servicioDao.elimiarServicio(idServicio);
		return idServicioElimainado;
	}

	public List<ServicioDTO> obtenerServiciosCompradorPeriodo(long empleadoId, int anio) {
		List<ServicioDTO> listServiciosDto = new ArrayList<>();
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

	public List<ServicioProductividadDTO> obtenerServicioProductividad(long empleadoId, int anio) {
		List<ServicioProductividadDTO> servicioProductividad = new ArrayList<>();
		Optional<Empleado> empleadoOP = empleadoDaoPage.findById(empleadoId);
		if (empleadoOP.isPresent()) {
			List<Servicio> servicios = servicioDaoPage.findByEmpleadoAndAnioOrderByPeriodo(empleadoOP.get(), anio);
			for (Servicio servicio : servicios) {
				ServicioProductividadDTO servicioDto = new ServicioProductividadDTO();
				modelMapper.map(servicio, servicioDto);
				servicioProductividad.add(servicioDto);
			}
		} else {
			throw new NoSuchElementException("No existe el empleado con id " + empleadoId);
		}

		return servicioProductividad;
	}

}
