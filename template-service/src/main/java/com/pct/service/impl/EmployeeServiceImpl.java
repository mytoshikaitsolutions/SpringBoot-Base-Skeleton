package com.pct.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pct.dao.EmployeeDao;
import com.pct.dto.EmployeeDto;
import com.pct.entity.EmployeeEntity;
import com.pct.logging.Loggable;
import com.pct.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final ModelMapper modelMapper;

	@Autowired
	private EmployeeDao employeeDao;

	@Loggable
	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		log.info("Add employee method in EmployeeService");
		EmployeeDto response = null;
		if(Objects.nonNull(employeeDto)) {
			EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
			employeeEntity.setId(UUID.randomUUID().toString());
			employeeEntity = employeeDao.save(employeeEntity);
			response = modelMapper.map(employeeEntity, EmployeeDto.class);
			return response;
		}
		return response;
	}

	@Loggable
	@Override
	public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto) {
		EmployeeDto response = null;
		if(Objects.nonNull(employeeDto.getId())) {
			Optional<EmployeeEntity> optionalEmployeeEntity = employeeDao.findById(employeeDto.getId());
			if(optionalEmployeeEntity.isPresent()) {
				EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
				employeeEntity.setId(optionalEmployeeEntity.get().getId());
				employeeEntity = employeeDao.save(employeeEntity);
				response = modelMapper.map(employeeEntity, EmployeeDto.class);
				return response;
			}
		}
		return response;
	}

	@Loggable
	@Override
	public EmployeeDto getEmployeeById(String id) {
		EmployeeDto response = null;
		Optional<EmployeeEntity> optionalEmployeeEntity = employeeDao.findById(id);
		if(optionalEmployeeEntity.isPresent()) {
			response = modelMapper.map(optionalEmployeeEntity.get(), EmployeeDto.class);
			return response;
		}
		return response;
	}

	@Loggable
	@Override
	public List<EmployeeDto> getEmployeeList() {
		List<EmployeeDto> responseList = new ArrayList<>();
		List<EmployeeEntity> employeeEntityList = employeeDao.findAll();
		if(Objects.nonNull(employeeEntityList) && !employeeEntityList.isEmpty()) {
			employeeEntityList.stream().forEach(employeeEntity->
			responseList.add(modelMapper.map(employeeEntity, EmployeeDto.class)));
			return responseList;
		}
		return responseList;
	}

	@Loggable
	@Override
	public Boolean deleteEmployeeDetails(String id) {
		Optional<EmployeeEntity> employeeEntity = employeeDao.findById(id);
		if(!employeeEntity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record does'nt exist to delete for this id.");
		}
		employeeDao.deleteById(id);
		return true;
	}

}