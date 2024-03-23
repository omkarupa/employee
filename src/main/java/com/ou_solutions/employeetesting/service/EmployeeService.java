package com.ou_solutions.employeetesting.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ou_solutions.employeetesting.dto.DepartmentDTO;
import com.ou_solutions.employeetesting.dto.EmployeeDTO;
import com.ou_solutions.employeetesting.entity.Department;
import com.ou_solutions.employeetesting.entity.Employee;
import com.ou_solutions.employeetesting.repository.DepartmentRepository;
import com.ou_solutions.employeetesting.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service

@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository empRepository;
	
	private final DepartmentRepository deptRepository;
	
	private final PasswordEncoder encoder;
	
	
	/*
	 * private static List<Employee> employees;
	 * 
	 * private static List<Department> departmentList;
	 * 
	 * private static Long empid = 0L; private static Long deptId = 0L;
	 * 
	 * public EmployeeService() { this.emp = null;
	 * log.info("List created for employess and department"); employees = new
	 * ArrayList<>(); departmentList = new ArrayList<>(); }
	 */

	public List<Employee> getAllEmployees()
	{
		return empRepository.findAll();
	}
	
	public Employee getEmployee(Long empId)
	{
		if(empRepository.findById(empId).isPresent())
		{
			return empRepository.findById(empId).get();
		}
		
		return null;
		
	}
	
	
	public Employee createNew( EmployeeDTO empDTO) throws Exception 
	{
		
		Department dept = null;
		
		if(!isDepartmentExists(empDTO.getDepartment()))
		{
			 dept = new Department().builder()
					.name(empDTO.getDepartment().getName())
					.location(empDTO.getDepartment().getLocation())
					.build();
		}
		else {
			dept = getDeparment(empDTO.getDepartment());
		}
		
		
		if(empRepository.findByEmail(empDTO.getEmail()).isPresent())
		{
			throw new Exception("User Already exists with email");
		}
		
		Employee emp = new Employee().builder()
				.fullName(empDTO.getFullName())
				.email(empDTO.getEmail())
				.password(encoder.encode(empDTO.getPassword()))
				.salary(empDTO.getSalary())
				.department(dept)
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.build();
				
		
		empRepository.save(emp);
		
		return emp;
	}
	
	
	public Employee updateEmployee( Long empId,EmployeeDTO empDTO) throws Exception
	{
		
		if(isEmployeeExists(empId))
		{
			Employee emp = getEmployee(empId);
			
			if(emp != null && emp.getEmail().equals(empDTO.getEmail()))
			{
				Department dept = null;
				
				if(!isDepartmentExists(empDTO.getDepartment()))
				{
					 dept = new Department().builder()
							.name(empDTO.getDepartment().getName())
							.location(empDTO.getDepartment().getLocation())
							.build();
					
				}
				else {
					dept = getDeparment(empDTO.getDepartment());
				}
				
				emp.setFullName(empDTO.getFullName());
				 emp.setDepartment(dept);
				 emp.setSalary(empDTO.getSalary());
				 emp.setUpdatedDate(LocalDateTime.now());
				 empRepository.save(emp);

			}
			else {
				throw new Exception("User Cannot change Emaild");
			}
			
			
			return emp;
		}
		
		return null;
	}
	
	
	public Employee deleteEmployee( Long empId)
	{
		
		if(isEmployeeExists(empId))
		{
			Employee emp = getEmployee(empId);
			
			 empRepository.deleteById(empId);
			
			return emp;
		}
		
		return null;
		
	}
	
	
	private boolean isDepartmentExists(DepartmentDTO deptDto)
	{

		return deptRepository.findByNameAndLocation(deptDto.getName(),deptDto.getLocation()).isPresent();
		
	}
	
	private Department getDeparment(DepartmentDTO deptDto)
	{
		if(isDepartmentExists(deptDto))
		{
			return deptRepository.findByNameAndLocation(deptDto.getName(),deptDto.getLocation()).get();
		}
		
		return null;
	}
	
	private Boolean isEmployeeExists(Long id)
	{
		return empRepository.findById(id).isPresent();
	}

	public Employee changePassword(String email,String currentPassword, String newPassword) throws Exception {
		
		//log.info("Email : {} , CurrentPassword : {} , newPassword : {}",email,currentPassword,newPassword );
		
		if(isEmployeeExists(email,currentPassword))
		{
			Employee emp = getEmployeeByEmailAndPassword(email,currentPassword);
			
			//log.info("EMployee : {}",emp );
			
			if(emp != null)
			{
				emp.setPassword(encoder.encode(newPassword));
				
				empRepository.save(emp);
			}
			
			
			return emp;
			
		}
		else { throw new Exception("User Not found"); }
		
		/*
		 * if(isEmployeeExists(empId)) { Employee emp = getEmployee(empId);
		 * 
		 * log.info("Password : " + encoder.encode(newPassword));
		 * 
		 * emp.setPassword( encoder.encode(newPassword));
		 * 
		 * empRepository.save(emp);
		 * 
		 * return emp; } else { throw new Exception("User Not found"); }
		 */
		
		
	}

	private Employee getEmployeeByEmailAndPassword(String email, String currentPassword) {
		
		return empRepository.findByEmail(email).get();
	}

	private boolean isEmployeeExists(String email, String currentPassword) {
		
		 return empRepository.findByEmail(email).isPresent();
	
	}
	

	
	

	
	
	


	
	

}
