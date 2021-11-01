package io.github.smallintro.springboot.employeeservice.repository;

import io.github.smallintro.springboot.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query(value = "from Employee where upper(empName) = upper(:empName)")
	Employee findByEmpName(@Param("empName") String empName);

	Employee findByEmpId(String empId);

}
