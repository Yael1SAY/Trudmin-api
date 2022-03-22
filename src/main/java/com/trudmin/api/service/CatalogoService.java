package com.trudmin.api.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.ICatalogoDao;
import com.trudmin.api.dto.AreaDto;
import com.trudmin.api.dto.CatalogClaveEmpleadoDTO;
import com.trudmin.api.dto.PuestoDTO;
import com.trudmin.api.dto.SubAreaDto;
import com.trudmin.api.model.Area;
import com.trudmin.api.model.Empleado;
import com.trudmin.api.model.Puesto;
import com.trudmin.api.model.SubArea;

@Service
public class CatalogoService {
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	ICatalogoDao catalogosDao;

	public List<AreaDto> obtenerAreas() {
		List<AreaDto> ListAreasDto = new ArrayList<AreaDto>();
		List<Area> areasResponse = catalogosDao.obtenerAreas();
		for(Area area : areasResponse) {
			AreaDto areaDto = new AreaDto();
			modelMapper.map(area, areaDto);
			ListAreasDto.add(areaDto);
		}
		return ListAreasDto;		
	}
	
	public List<SubAreaDto> obtenerSubAreas() {
		List<SubAreaDto> listSubAreas = new ArrayList<SubAreaDto>();
		List<SubArea> subAreaResponse = catalogosDao.obtenerSubareas();
		for(SubArea subArea : subAreaResponse) {
			SubAreaDto subAreaDto = new SubAreaDto();
			modelMapper.map(subArea, subAreaDto);
			listSubAreas.add(subAreaDto);
		}
		return listSubAreas;
	}
	
	public List<PuestoDTO> obtenerPuestos() {
		List<PuestoDTO> listPuestoDto = new ArrayList<PuestoDTO>();
		List<Puesto> puestoResponse = catalogosDao.obtenerPuestos();
		for(Puesto puesto : puestoResponse) {
			PuestoDTO puestoDto = new PuestoDTO();
			modelMapper.map(puesto, puestoDto);
			listPuestoDto.add(puestoDto);
		}
		return listPuestoDto;
	}
	
	public List<CatalogClaveEmpleadoDTO> obtenerClavesEmpleados() {
		List<CatalogClaveEmpleadoDTO> listCatalogClv = new ArrayList<CatalogClaveEmpleadoDTO>();
		List<Empleado> empleadosResponse = catalogosDao.obtenerEmpleados();
		for(Empleado empleado : empleadosResponse) {
			CatalogClaveEmpleadoDTO catalogClave = new CatalogClaveEmpleadoDTO();
			modelMapper.map(empleado, catalogClave);
			listCatalogClv.add(catalogClave);
		}
		return listCatalogClv;
	}
}
