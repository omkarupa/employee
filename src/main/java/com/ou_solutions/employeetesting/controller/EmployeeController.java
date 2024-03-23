package com.ou_solutions.employeetesting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ou_solutions.employeetesting.dto.EmployeeDTO;
import com.ou_solutions.employeetesting.dto.PasswordChangeDTO;
import com.ou_solutions.employeetesting.entity.Employee;
import com.ou_solutions.employeetesting.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService service;
	
	
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return service.getAllEmployees();
	}
	
	@GetMapping("/employee/{empId}")
	public Employee getEmployee(@PathVariable Long empId)
	{
		return service.getEmployee(empId);
	}
	
	@PostMapping("/employee")
	public Employee createNew(@RequestBody EmployeeDTO empDTO) throws Exception
	{
		return service.createNew(empDTO);
	}
	
	@PutMapping("/employee/{empId}")
	public Employee updateEmployee(@PathVariable Long empId,@RequestBody EmployeeDTO dto) throws Exception
	{
		return service.updateEmployee(empId,dto);
	}
	
	@DeleteMapping("/employee/{empId}")
	public Employee deleteEmployee(@PathVariable Long empId)
	{
		return service.deleteEmployee(empId);
	}
	
	@PutMapping("/employee/password-change")
	public Employee changePassword(@RequestBody PasswordChangeDTO passwordDto ) throws Exception
	{
		if(!passwordDto.getCurrentPassword().equals(passwordDto.getNewPassword()))
		{
			return service.changePassword(passwordDto.getEmail(),passwordDto.getCurrentPassword(),passwordDto.getNewPassword());
		}
		else 
		{
			throw new Exception("Current password and New password cannot be same");
		}
		
		
	}
	
	
	
	

}
