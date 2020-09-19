package com.smallintro.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smallintro.springboot.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query(value = "from Employee where upper(empName) = upper(:empName)")
	Employee findByEmpName(@Param("empName") String empName);

	Employee findByEmpId(String empId);

}
