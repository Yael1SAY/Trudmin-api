package com.trudmin.api.controller;

import java.util.Map;

import javax.annotation.Resource;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import com.trudmin.api.service.ReporteProductividadService;

@RestController
@RequestMapping("reporte")
public class ReporteProductividadContoller {
	
	// @Autowired
	// private ReporteProductividadService reporteService;
	
	@GetMapping("/report")
	public ResponseEntity<Resource> download(@RequestParam Map<String, Object> paramas) {
		return null;
	}

}
