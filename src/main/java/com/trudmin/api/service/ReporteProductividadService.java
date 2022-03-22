package com.trudmin.api.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dto.FileReportDTO;
import com.trudmin.api.report.JasperReportManager;
import com.trudmin.api.report.enums.TipoReporteEnum;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;

@Service
public class ReporteProductividadService {

	@Autowired
	private JasperReportManager jasperReport;

	@Autowired
	private DataSource dataSource;

	FileReportDTO obtenerReporteProductividad(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "reporte_de_productividad";
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";

		FileReportDTO fileReport = new FileReportDTO();

		ByteArrayOutputStream stream = jasperReport.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();

		fileReport.setFileName(fileName + extension);
		fileReport.setStream(new ByteArrayInputStream(bs));
		fileReport.setLength(bs.length);

		return fileReport;
	}

}
