package com.trudmin.api.dto;

import java.io.ByteArrayInputStream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileReportDTO {
	
	private String fileName;
	private ByteArrayInputStream stream;
	private int length ;

}