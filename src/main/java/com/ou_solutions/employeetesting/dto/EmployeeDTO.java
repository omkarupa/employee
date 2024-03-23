package com.ou_solutions.employeetesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
	private Long employeeId;
	
	private String fullName;
	
	private String email;
	
	private String password;
	
	private DepartmentDTO department;
	
	private double salary;
	

}
