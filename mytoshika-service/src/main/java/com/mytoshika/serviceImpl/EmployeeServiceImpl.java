package com.mytoshika.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytoshika.domain.EmployeeEntity;
import com.mytoshika.dto.EmployeeDto;
import com.mytoshika.repository.EmployeeRepository;
import com.mytoshika.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final ModelMapper modelMapper;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		log.info("Add employee method in EmployeeService");
		EmployeeDto response = null;
		if(Objects.nonNull(employeeDto)) {
			EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
			employeeEntity.setId(UUID.randomUUID().toString());
			employeeEntity = employeeRepository.save(employeeEntity);
			response = modelMapper.map(employeeEntity, EmployeeDto.class);
			return response;
		}
		return response;
	}

	@Override
	public EmployeeDto updateEmployeeDetails(EmployeeDto employeeDto) {
		EmployeeDto response = null;
		if(Objects.nonNull(employeeDto.getId())) {
			Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeDto.getId());
			if(optionalEmployeeEntity.isPresent()) {
				EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
				employeeEntity.setId(optionalEmployeeEntity.get().getId());
				employeeEntity = employeeRepository.save(employeeEntity);
				response = modelMapper.map(employeeEntity, EmployeeDto.class);
				return response;
			}
		}
		return response;
	}

	@Override
	public EmployeeDto getEmployeeById(String id) {
		EmployeeDto response = null;
		Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
		if(optionalEmployeeEntity.isPresent()) {
			response = modelMapper.map(optionalEmployeeEntity.get(), EmployeeDto.class);
			return response;
		}
		return response;
	}

	@Override
	public List<EmployeeDto> getEmployeeList() {
		List<EmployeeDto> responseList = new ArrayList<>();
		List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
		if(Objects.nonNull(employeeEntityList)) {
			employeeEntityList.stream().forEach(employeeEntity->
				responseList.add(modelMapper.map(employeeEntity, EmployeeDto.class)));
			return responseList;
		}
		return responseList;
	}

}