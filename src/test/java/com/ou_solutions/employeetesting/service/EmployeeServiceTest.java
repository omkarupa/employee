package com.ou_solutions.employeetesting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ou_solutions.employeetesting.dto.EmployeeDTO;
import com.ou_solutions.employeetesting.entity.Department;
import com.ou_solutions.employeetesting.entity.Employee;


@SpringBootTest
public class EmployeeServiceTest {
	
	@InjectMocks
	EmployeeService employeeService;
	

	

	
	@Test
	public void getEmployeeTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		

		
		 Employee emp =  employeeService.getEmployee(1L);
		if(emp != null)
			assertEquals(emp.getFullName(), "Omkar Upadhyay");
		
		if(emp == null)
			assertEquals(emp, null);
	
	}
	

	 
}
