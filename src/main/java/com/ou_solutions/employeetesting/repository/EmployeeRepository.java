package com.ou_solutions.employeetesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ou_solutions.employeetesting.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Optional<Employee> findByEmail(String email);

	public Optional<Employee> findByEmailAndPassword(String email, String password);

}
