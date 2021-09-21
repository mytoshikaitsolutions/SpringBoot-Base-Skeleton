package com.pct.dao;

import java.util.List;
import java.util.Optional;

import com.pct.entity.EmployeeEntity;

public interface EmployeeDao {

	public EmployeeEntity save(EmployeeEntity employeeEntity);

	public Optional<EmployeeEntity> findById(String id);

	public List<EmployeeEntity> findAll();

	public void deleteById(String id);
}
