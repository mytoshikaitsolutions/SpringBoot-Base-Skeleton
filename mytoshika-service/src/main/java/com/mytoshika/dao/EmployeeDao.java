package com.mytoshika.dao;

import java.util.List;
import java.util.Optional;

import com.mytoshika.entity.EmployeeEntity;

public interface EmployeeDao {

	public EmployeeEntity save(EmployeeEntity employeeEntity);
	
	public Optional<EmployeeEntity> findById(String id);
	
	public List<EmployeeEntity> findAll();
	
	public EmployeeEntity getOne(String id);
	
	public void delete(EmployeeEntity employeeEntity);
}
