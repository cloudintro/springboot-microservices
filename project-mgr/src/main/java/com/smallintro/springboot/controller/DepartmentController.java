package com.smallintro.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smallintro.springboot.entity.Department;
import com.smallintro.springboot.service.DepartmentService;

import io.swagger.annotations.Api;


@Api(tags="Department management APIs",value="Project Controller")
@RestController
@RequestMapping("department")
public class DepartmentController {
	
	@Autowired
	DepartmentService deptService;
	
	@GetMapping(value="/{deptName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Department getDepartmentByName(@PathVariable String deptName) {
		
		return deptService.getDepartmentByName(deptName);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Department> getAllDepartments() {
		
		return deptService.getAllDepartments();
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Department createDepartment(@RequestBody Department dept) {
		
		return deptService.createDepartment(dept);
	}
	
	@DeleteMapping("/{deptId}")
	public void deleteDepartmentById(@PathVariable Long deptId) {
		
		deptService.deleteDepartmentById(deptId);
	}


}
