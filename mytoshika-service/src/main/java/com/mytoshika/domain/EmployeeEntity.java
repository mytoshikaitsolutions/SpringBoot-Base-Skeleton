package com.mytoshika.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name= "employee")
public class EmployeeEntity {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String designation;
	private String contactNo;
}
