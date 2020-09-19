package com.smallintro.springboot.service;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;

import com.smallintro.springboot.entity.Employee;
import com.smallintro.springboot.repository.EmployeeRepo;
import com.smallintro.springboot.utils.ProjectConstants;

public class EmployeeService {

	@Autowired
	EmployeeRepo empRepo;

	public List<Employee> getEmployees() {
		return empRepo.findAll();
	}

	public Employee getEmpInfo(String empId) {
		return empRepo.findByEmpId(empId);
	}

	public String addEmployee(Employee emp) {
		if (null != getEmpInfo(emp.getEmpId())) {
			empRepo.save(emp);
			return ProjectConstants.OPERATION_SUCCESS;
		}
		return ProjectConstants.OPERATION_FAILED;
	}

	public String updateEmployee(Integer empId, Employee emp) {
		if (empRepo.existsById(empId)) {
			empRepo.save(emp);
			return ProjectConstants.OPERATION_SUCCESS;
		}
		return ProjectConstants.OPERATION_FAILED;
	}

	public String delEmployee(@Min(1) Integer empId) {
		if (empRepo.existsById(empId)) {
			empRepo.deleteById(empId);
			return ProjectConstants.OPERATION_SUCCESS;
		}
		return ProjectConstants.OPERATION_FAILED;
	}
}
