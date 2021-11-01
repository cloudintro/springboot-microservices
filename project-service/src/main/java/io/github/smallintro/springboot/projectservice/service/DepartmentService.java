package io.github.smallintro.springboot.projectservice.service;

import java.util.List;

import io.github.smallintro.springboot.projectservice.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.smallintro.springboot.projectservice.entity.Project;
import io.github.smallintro.springboot.projectservice.repository.DepartmentRepo;

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