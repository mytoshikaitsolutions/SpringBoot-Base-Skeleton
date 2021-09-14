package com.mytoshika.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;

@Data
@JsonIgnoreType
public class ResponseBodyDTO<T, ID> extends GenericResponse {

	T data;
	
	ID id;
}
