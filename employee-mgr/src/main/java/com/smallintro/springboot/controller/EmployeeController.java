package com.smallintro.springboot.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smallintro.springboot.entity.Employee;
import com.smallintro.springboot.service.EmployeeService;

import io.swagger.annotations.Api;

@Api(tags="Employee management APIs",value="Employee Controller")
@RestController
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
		return empService.getEmployees();
	}

	@GetMapping(value = "/{empId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmpInfo(@PathVariable String empId) {
		return empService.getEmpInfo(empId);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String addEmployee(@RequestBody Employee emp) {
	
			return empService.addEmployee(emp);

	}

	@PutMapping(value="/{empId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateEmployee(@PathVariable Integer empId,@RequestBody Employee emp) {
			return empService.updateEmployee(empId, emp);
	}

	@DeleteMapping(value="/{empId}")
	public String delEmployee(@Min(1) @PathVariable Integer empId) {
			return empService.delEmployee(empId);
	}

}
