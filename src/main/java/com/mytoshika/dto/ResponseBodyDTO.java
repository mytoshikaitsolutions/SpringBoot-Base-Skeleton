package com.mytoshika.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@Data
@JsonIgnoreType
public class ResponseBodyDTO<T> extends GenericResponse {

	T data;
}
