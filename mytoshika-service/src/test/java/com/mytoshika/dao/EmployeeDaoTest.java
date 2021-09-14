//
//package com.mytoshika.dao;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.mytoshika.JPAConfiguration;
//import com.mytoshika.entity.EmployeeEntity;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {JPAConfiguration.class})
//public class EmployeeDaoTest {
//
//	@Autowired
//	private EmployeeDao employeeDao;
//
//	@BeforeEach
//	void checkDaoNotNull(){
//		assertNotNull(employeeDao);
//	}
//
//	@Test
//	public void findByIdTest()  {
//		EmployeeEntity empEntity = getEmpEntity();
//		Optional<EmployeeEntity> coreEntity = employeeDao.findById("1");
//		assertObjects(empEntity,coreEntity);
//	}
//
//	@Test
//	public void saveTest()  {
//		EmployeeEntity employeeEntity = new EmployeeEntity();
//		employeeEntity.setId("1");
//		employeeEntity.setFirstName("John");
//		employeeEntity.setLastName("Martin");
//		employeeEntity.setEmail("abc@gmail.com");
//		employeeEntity.setDesignation("SprigBoot");
//		employeeEntity.setContactNo("9856472632");
//
//		employeeDao.save(employeeEntity);
//
//		EmployeeEntity employeeEntity1 = new EmployeeEntity();
//		employeeEntity1.setId("2");
//		employeeEntity1.setFirstName("Ram");
//		employeeEntity1.setLastName("Mishra");
//		employeeEntity1.setEmail("abc@gmail.com");
//		employeeEntity1.setDesignation("SprigBoot");
//		employeeEntity1.setContactNo("9856465453");
//
//		employeeDao.save(employeeEntity1);
//
//		List<EmployeeEntity> empEntity = employeeDao.findAll();
//		Assert.assertEquals(employeeEntity1.getId(), empEntity.get(1).getId());
//		Assert.assertEquals(employeeEntity1.getFirstName(), empEntity.get(1).getFirstName());
//		Assert.assertEquals(employeeEntity.getLastName(), empEntity.get(0).getLastName());
//		Assert.assertEquals(employeeEntity.getEmail(), empEntity.get(0).getEmail());
//		Assert.assertEquals(employeeEntity.getDesignation(), empEntity.get(0).getDesignation());
//		Assert.assertEquals(employeeEntity.getContactNo(), empEntity.get(0).getContactNo());
//	}
//
//	@Test
//	public void findAllTest() {
//		EmployeeEntity employeeEntity = new EmployeeEntity();
//		employeeEntity.setId("1");
//		employeeEntity.setFirstName("John");
//		employeeEntity.setLastName("Martin");
//		employeeEntity.setEmail("abc@gmail.com");
//		employeeEntity.setDesignation("SprigBoot");
//		employeeEntity.setContactNo("9856472632");
//
//		employeeDao.save(employeeEntity);
//
//		EmployeeEntity employeeEntity1 = new EmployeeEntity();
//		employeeEntity1.setId("2");
//		employeeEntity1.setFirstName("Ram");
//		employeeEntity1.setLastName("Mishra");
//		employeeEntity1.setEmail("abcd@gmail.com");
//		employeeEntity1.setDesignation("SprigBoot");
//		employeeEntity1.setContactNo("9856465453");
//
//		employeeDao.save(employeeEntity1);
//
//		List<EmployeeEntity> employees = employeeDao.findAll();
//
//		Assert.assertEquals(2, employees.size());
//
//		Assert.assertEquals(employeeEntity.getEmail(), employees.get(0).getEmail());
//		Assert.assertEquals(employeeEntity1.getEmail(), employees.get(1).getEmail());
//	}
//
//	@Test
//	public void deleteTest() {
//		EmployeeEntity employeeEntity = new EmployeeEntity();
//		employeeEntity.setId("1");
//		employeeEntity.setFirstName("John");
//		employeeEntity.setLastName("Martin");
//		employeeEntity.setEmail("abc@gmail.com");
//		employeeEntity.setDesignation("SprigBoot");
//		employeeEntity.setContactNo("9856472632");
//
//		employeeDao.save(employeeEntity);
//
//		EmployeeEntity employeeEntity1 = new EmployeeEntity();
//		employeeEntity1.setId("2");
//		employeeEntity1.setFirstName("Ram");
//		employeeEntity1.setLastName("Mishra");
//		employeeEntity1.setEmail("abcd@gmail.com");
//		employeeEntity1.setDesignation("SprigBoot");
//		employeeEntity1.setContactNo("9856465453");
//
//		employeeDao.save(employeeEntity1);
//
//		assertEquals(2, employeeDao.findAll().size());
//
//		employeeDao.delete(employeeDao.findById("2").get());
//
//		assertEquals(1, employeeDao.findAll().size());
//
//		assertFalse(employeeDao.findById("2").isPresent());
//	}
//
//	private EmployeeEntity createEmployeeEntity(){
//		EmployeeEntity empEntity = new EmployeeEntity();
//		empEntity.setId("1");
//		empEntity.setFirstName("John");
//		empEntity.setLastName("Martin");
//		empEntity.setEmail("abc@gmail.com");
//		empEntity.setDesignation("Java Dev");
//		empEntity.setContactNo("987542373");
//		return empEntity;
//	}
//
//	private void assertObjects(EmployeeEntity empEntity, Optional<EmployeeEntity> coreEntity){
//		Objects.equals(empEntity, coreEntity);
//	}
//
//	private EmployeeEntity getEmpEntity(){
//		EmployeeEntity empEntity = createEmployeeEntity();
//		return employeeDao.save(empEntity);
//	}
//
//}