package com.smallintro.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smallintro.springboot.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
	
	public Department findByDeptName(String deptName);

}
