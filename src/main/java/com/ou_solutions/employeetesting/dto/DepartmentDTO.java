package com.ou_solutions.employeetesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
	
	private Long departmentId;
	private String name;
	private String location;

}