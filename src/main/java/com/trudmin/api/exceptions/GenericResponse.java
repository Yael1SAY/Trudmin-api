package com.trudmin.api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse<T> {
    private T data;
    private String message;
    private int status;
}
