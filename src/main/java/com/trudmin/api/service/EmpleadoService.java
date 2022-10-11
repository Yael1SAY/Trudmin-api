package com.trudmin.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.trudmin.api.dao.IEmpleadoDao;
import com.trudmin.api.dao.IEmpleadoDaoPage;
import com.trudmin.api.dto.EmpleadoDTO;
import com.trudmin.api.model.Empleado;

@Service
public class EmpleadoService {

	@Autowired
	IEmpleadoDao empleadoDao;

	@Autowired
	IEmpleadoDaoPage empleadoDaoPage;

	ModelMapper modelMapper = new ModelMapper();

	public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    } 

	public List<Empleado> obtenerEmpleados() {
		return empleadoDao.obtenerEmpleados();
	}

	public Page<EmpleadoDTO> obtenerEmpleadosPage(Pageable pageable) {
		Page<Empleado> empleadoEntity = empleadoDaoPage.findAll(pageable);
        Page<EmpleadoDTO> empleadoDTO = mapEntityPageIntoDtoPage(empleadoEntity, EmpleadoDTO.class);
        return empleadoDTO;
	}

}
