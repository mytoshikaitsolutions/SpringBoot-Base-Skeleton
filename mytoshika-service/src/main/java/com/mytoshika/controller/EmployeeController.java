package com.mytoshika.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Timed
	@PostMapping("/add")
	private ResponseEntity<ResponseBodyDTO<EmployeeDto, Long>> addEmployee(@RequestBody EmployeeDto employeeDto) throws Exception{
		ResponseBodyDTO<EmployeeDto, Long> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.addEmployee(employeeDto));
		responseBodyDTO.setMessage("Employee added successfully");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	private ResponseEntity<ResponseBodyDTO<EmployeeDto, Long>> updateEmployeeDetails(@RequestBody EmployeeDto employeeDto){
		ResponseBodyDTO<EmployeeDto, Long> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.updateEmployeeDetails(employeeDto));
		responseBodyDTO.setMessage("Employee updated successfully");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<ResponseBodyDTO<EmployeeDto, Long>> getEmployeeById(@PathVariable("id") String id){
		ResponseBodyDTO<EmployeeDto, Long> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.getEmployeeById(id));
		responseBodyDTO.setMessage("Employee details");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@GetMapping()
	private ResponseEntity<ResponseBodyDTO<List<EmployeeDto>, Long>> getEmployeeList(){
		ResponseBodyDTO<List<EmployeeDto>, Long> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.getEmployeeList());
		responseBodyDTO.setMessage("Employee details");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "API to delete employee details by Id")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseBodyDTO<Boolean, Long>> deleteEmployeeDetails(@PathVariable("id") String id) {
		ResponseBodyDTO<Boolean, Long> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setData(employeeService.deleteEmployeeDetails(id));
		responseBodyDTO.setMessage("Employee details");
		responseBodyDTO.setStatus(Status.SUCCESS);
		return new ResponseEntity<>(responseBodyDTO, HttpStatus.OK);
	}
}
