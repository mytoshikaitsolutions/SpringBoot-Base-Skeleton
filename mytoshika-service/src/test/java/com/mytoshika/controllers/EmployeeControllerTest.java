package com.mytoshika.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytoshika.controller.EmployeeController;
import com.mytoshika.dto.EmployeeDto;
import com.mytoshika.dto.ResponseBodyDTO;
import com.mytoshika.dto.Status;
import com.mytoshika.serviceImpl.EmployeeServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=EmployeeController.class)
@WebAppConfiguration
@WebMvcTest
public class EmployeeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;

	@MockBean
	private EmployeeServiceImpl employeeService;

	@BeforeTestMethod
	public  void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test 
	public void addEmployeeTest() throws Exception{

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId("1");
		employeeDto.setContactNo("9999999999");
		employeeDto.setFirstName("Emp");
		employeeDto.setLastName("test");
		employeeDto.setEmail("emptest@gmail.com");
		employeeDto.setDesignation("test");

		ResponseBodyDTO<EmployeeDto> responseBodyDTO = new ResponseBodyDTO<>(); 
		responseBodyDTO.setStatus(Status.SUCCESS); 
		responseBodyDTO.setMessage("Employee added successfully");
		responseBodyDTO.setData(employeeDto);
		String json=mapper.writeValueAsString(responseBodyDTO);

		when(employeeService.addEmployee(employeeDto)).thenReturn(employeeDto);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.mockMvc.perform(post("/employee/add")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json))
		.andDo(print())
		.andExpect(status().isOk()) 
		//		.andExpect(content().string(this.mapper.writeValueAsString(responseBodyDTO)))
		.andExpect(jsonPath("$.status", is("SUCCESS")))
		.andReturn();
	}

	@Test 
	public void updateEmployeeDetailsTest() throws Exception{

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId("1");
		employeeDto.setContactNo("9999999999");
		employeeDto.setFirstName("Emp");
		employeeDto.setLastName("test");
		employeeDto.setEmail("emptest@gmail.com");
		employeeDto.setDesignation("test");

		ResponseBodyDTO<EmployeeDto> responseBodyDTO = new ResponseBodyDTO<>(); 
		responseBodyDTO.setStatus(Status.SUCCESS); 
		responseBodyDTO.setMessage("Employee updated successfully");
		responseBodyDTO.setData(employeeDto);
		String json=mapper.writeValueAsString(responseBodyDTO);

		when(employeeService.updateEmployeeDetails(employeeDto)).thenReturn(employeeDto);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.mockMvc.perform(put("/employee/update").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
		.andDo(print())
		.andExpect(status().isOk()) 
		//		.andExpect(content().string(this.mapper.writeValueAsString(responseBodyDTO)))
		.andExpect(jsonPath("$.status", is("SUCCESS")))
		.andReturn();
	}

	@Test 
	public void getEmployeeByIdTest() throws Exception{

		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId("1");
		employeeDto.setContactNo("989383838");
		employeeDto.setEmail("abc@gmail.com");
		employeeDto.setFirstName("Abc");
		employeeDto.setLastName("Def");
		employeeDto.setDesignation("CEO");

		ResponseBodyDTO<EmployeeDto> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setMessage("Employee details");
		responseBodyDTO.setStatus(Status.SUCCESS);
		responseBodyDTO.setData(employeeDto);

		when(employeeService.getEmployeeById("1")).thenReturn(employeeDto);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.mockMvc.perform(get("/employee/{id}", 1).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		//		.andExpect(content().string(this.mapper.writeValueAsString(responseBodyDTO)))
		.andExpect(jsonPath("$.status", is("SUCCESS")))
		.andExpect(status().isOk())
		.andReturn();
	}

	@Test 
	public void getEmployeeTest() throws Exception{

		EmployeeDto employeeDto1 = new EmployeeDto();
		employeeDto1.setId("1");
		employeeDto1.setContactNo("989383838");
		employeeDto1.setEmail("abc@gmail.com");
		employeeDto1.setFirstName("Abc");
		employeeDto1.setLastName("Def");
		employeeDto1.setDesignation("CEO");

		EmployeeDto employeeDto2 = new EmployeeDto();
		employeeDto2.setId("2");
		employeeDto2.setContactNo("989383837");
		employeeDto2.setEmail("abcd@gmail.com");
		employeeDto2.setFirstName("Abdc");
		employeeDto2.setLastName("Defg");
		employeeDto2.setDesignation("CEOA");

		List<EmployeeDto> allRecords = new ArrayList<>(Arrays.asList(employeeDto1, employeeDto2));

		ResponseBodyDTO<List<EmployeeDto>> responseBodyDTO = new ResponseBodyDTO<>();
		responseBodyDTO.setMessage("Employee details");
		responseBodyDTO.setStatus(Status.SUCCESS);
		responseBodyDTO.setData(allRecords);

		when(employeeService.getEmployeeList()).thenReturn(allRecords);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.mockMvc.perform(get("/employee").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		//		.andExpect(content().string(this.mapper.writeValueAsString(responseBodyDTO)))
		.andExpect(jsonPath("$.status", is("SUCCESS")))
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	public void deleteEmployeeDetailsTest() throws Exception {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId("1");
		employeeDto.setContactNo("989383838");
		employeeDto.setEmail("abc@gmail.com");
		employeeDto.setFirstName("Abc");
		employeeDto.setLastName("Def");
		employeeDto.setDesignation("CEO");

		when(employeeService.deleteEmployeeDetails("1")).thenReturn(true);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.mockMvc.perform(delete("/employee/delete/{id}", "1").contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
	}

}
