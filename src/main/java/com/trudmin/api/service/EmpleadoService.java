package com.trudmin.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.trudmin.api.dao.IEmpleadoDao;
import com.trudmin.api.dao.IEmpleadoDaoPage;
import com.trudmin.api.dto.AltaEmpleadoDTO;
import com.trudmin.api.dto.EmpleadoDTO;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Usuario;

@Service
public class EmpleadoService {

	@Autowired
	IEmpleadoDao empleadoDao;

	@Autowired
	IEmpleadoDaoPage empleadoDaoPage;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	SubAreaService subareaService;

	@Autowired
	PuestoService puestoService;

	ModelMapper modelMapper = new ModelMapper();

	/**
	 * Metodo que permite realizar el mapeo de una entidad tipo Page a un DTO tipo Page 
	 * @param <D>
	 * @param <T>
	 * @param entities
	 * @param dtoClass
	 * @return
	 */
	public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    } 

	public List<Empleado> obtenerEmpleados() {
		return empleadoDao.obtenerEmpleados();
	}

	/**
	 * Metodo que obtiene una lista de empleados por pagina
	 * @param pageable
	 * @return
	 */
	public Page<EmpleadoDTO> obtenerEmpleadosPage(Pageable pageable) {
		Page<Empleado> empleadoEntity = empleadoDaoPage.findAll(pageable);
        Page<EmpleadoDTO> empleadoDTO = mapEntityPageIntoDtoPage(empleadoEntity, EmpleadoDTO.class);
        return empleadoDTO;
	}

	/**
	 * Metodo que permite dar de alta a un empleado
	 * @param empleado
	 * @return
	 */
	public EmpleadoDTO altaEmpleado(AltaEmpleadoDTO empleado) {
		Empleado empleadoEntity = new Empleado();
		modelMapper.map(empleado, empleadoEntity);
		empleadoEntity.setUsuario(usuarioService.obtenerUsuarioId(empleado.getUsuario()));
		empleadoEntity.setSubArea(subareaService.findById(empleado.getSubArea()));
		empleadoEntity.setPuesto(puestoService.findAllById(empleado.getPuesto()));
		empleadoEntity.setEmpleadoId(UUID.randomUUID().hashCode());

		Empleado empleadoNew = empleadoDaoPage.save(empleadoEntity);
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		modelMapper.map(empleadoNew, empleadoDTO);
		return empleadoDTO;
	}

	public EmpleadoDTO obtenerEmpleadoPorUsuarioId(long usuarioId) {
		Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);
		Empleado empleadoEntity = empleadoDaoPage.findByUsuario(usuario);
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		modelMapper.map(empleadoEntity, empleadoDTO);
		return empleadoDTO;
	}

}
