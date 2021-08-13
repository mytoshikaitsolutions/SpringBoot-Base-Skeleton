package com.mytoshika.service;

import java.util.List;
import java.util.UUID;

import com.mytoshika.dto.EmployeeDto;


public interface EmployeeService {

	public EmployeeDto addEmployee(EmployeeDto employeeDto);

	public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto);

	public EmployeeDto getEmployeeById(UUID id);

	public List<EmployeeDto> getEmployeeList();

	public EmployeeDto deleteEmployeeDetails(UUID id);
}
