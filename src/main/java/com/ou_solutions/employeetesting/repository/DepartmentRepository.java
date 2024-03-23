package com.ou_solutions.employeetesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ou_solutions.employeetesting.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	public Optional<Department> findByNameAndLocation(String name, String location);

}
