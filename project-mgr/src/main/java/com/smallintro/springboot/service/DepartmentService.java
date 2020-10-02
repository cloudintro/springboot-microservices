package com.smallintro.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallintro.springboot.entity.Department;
import com.smallintro.springboot.entity.Project;
import com.smallintro.springboot.repository.DepartmentRepo;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepo deptRepo;
	
	public Department getDepartmentById(Long deptId) {

		return deptRepo.findByDeptId(deptId);
	}

	public Department getDepartmentByName(String deptName) {

		return deptRepo.findByDeptName(deptName);
	}

	public List<Department> getAllDepartments() {

		return deptRepo.findAll();
	}

	public Department createDepartment(Department dept) {

		return deptRepo.save(dept);
	}

	public void deleteDepartmentById(Long deptId) {

		deptRepo.deleteById(deptId);
	}

	public List<Project> getProjects(String deptName) {
		Department dept = deptRepo.findByDeptName(deptName);
		return dept.getProjects();
	}
}