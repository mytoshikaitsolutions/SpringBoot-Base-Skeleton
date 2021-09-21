package com.pct.service;

import java.util.List;

import com.pct.dto.EmployeeDto;


public interface EmployeeService {

	public EmployeeDto addEmployee(EmployeeDto employeeDto);

	public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto);

	public EmployeeDto getEmployeeById(String id);

	public List<EmployeeDto> getEmployeeList();
	
	public Boolean deleteEmployeeDetails(String id);
}
