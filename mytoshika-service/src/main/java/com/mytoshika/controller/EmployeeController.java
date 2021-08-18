package com.mytoshika.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytoshika.dto.EmployeeDto;
import com.mytoshika.dto.ResponseBodyDTO;
import com.mytoshika.dto.Status;
import com.mytoshika.service.EmployeeService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags="Employee controller provider" , description="This is a employee contoller to provide all the employee related APIs")
@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@ApiOperation(value = "API to add employee details")
	@Timed
	@PostMapping("/add")
	private ResponseEntity<ResponseBodyDTO<EmployeeDto>> addEmployee(@RequestBody EmployeeDto employeeDto) throws Exception{
		ResponseBodyDTO<EmployeeDto> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.addEmployee(employeeDto));
		responseBodyDTO.setMessage("Employee added successfully");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "API to update employee details by Id")
	@PutMapping("/update")
	private ResponseEntity<ResponseBodyDTO<EmployeeDto>> updateEmployeeDetails(@RequestBody EmployeeDto employeeDto){
		ResponseBodyDTO<EmployeeDto> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.updateEmployeeDetails(employeeDto));
		responseBodyDTO.setMessage("Employee updated successfully");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "API to get employee details by Id")
	@GetMapping("/{id}")
	private ResponseEntity<ResponseBodyDTO<EmployeeDto>> getEmployeeById(@PathVariable("id") String id){
		ResponseBodyDTO<EmployeeDto> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.getEmployeeById(id));
		responseBodyDTO.setMessage("Employee details");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "API to get all employee details")
	@GetMapping()
	private ResponseEntity<ResponseBodyDTO<List<EmployeeDto>>> getEmployeeList(){
		ResponseBodyDTO<List<EmployeeDto>> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.getEmployeeList());
		responseBodyDTO.setMessage("Employee details");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "API to delete employee details by Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteEmployeeDetails(@PathVariable("id") String id) {
		return ResponseEntity.ok(employeeService.deleteEmployeeDetails(id));
	}
}
