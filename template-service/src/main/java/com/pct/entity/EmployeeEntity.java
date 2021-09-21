package com.pct.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name= "employee")
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class EmployeeEntity {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String designation;
	private String contactNo;
	private boolean deleted = Boolean.FALSE; 
}
