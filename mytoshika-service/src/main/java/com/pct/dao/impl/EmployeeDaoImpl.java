package com.mytoshika.daoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytoshika.dao.EmployeeDao;
import com.mytoshika.entity.EmployeeEntity;
import com.mytoshika.repository.EmployeeRepository;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeEntity save(EmployeeEntity employeeEntity) {
		return employeeRepository.save(employeeEntity);
	}

	@Override
	public Optional<EmployeeEntity> findById(String id) {
		return employeeRepository.findById(id);
	}

	@Override
	public List<EmployeeEntity> findAll() {
		return employeeRepository.findAll();
	}

//	@Override
//	public EmployeeEntity getOne(String id) {
//		return employeeRepository.getOne(id);
//	}

	@Override
	public void deleteById(String id) {
		employeeRepository.deleteById(id);
	}
}
