package com.smallintro.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smallintro.springboot.entity.Department;
import com.smallintro.springboot.entity.Project;
import com.smallintro.springboot.service.DepartmentService;

import io.swagger.annotations.Api;

@Api(tags = "Department management APIs", value = "Department Controller")
@RestController
@RequestMapping("department")
public class DepartmentController {

	@Autowired
	DepartmentService deptService;

	@GetMapping(value = "/{deptName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EntityModel<Department> getDepartmentByName(@PathVariable String deptName) {

		Department dept = deptService.getDepartmentByName(deptName);

		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(dept.getDeptName()).withSelfRel();
		dept.add(selfLink);
		EntityModel<Department> deptModel = EntityModel.of(dept);
		return deptModel;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<Department> getAllDepartments() {

		List<Department> departments = deptService.getAllDepartments();

		for (Department dept : departments) {

			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(dept.getDeptName()).withSelfRel();
			dept.add(selfLink);

			CollectionModel<Project> projects = WebMvcLinkBuilder.methodOn(this.getClass())
					.getProjects(dept.getDeptName());

			Link projLink = WebMvcLinkBuilder.linkTo(projects).withRel("all-projects");

			dept.add(projLink);

		}
		Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();

		CollectionModel<Department> deptModels = CollectionModel.of(departments, selfLink);
		return deptModels;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Department createDepartment(@RequestBody Department dept) {

		return deptService.createDepartment(dept);
	}

	@DeleteMapping("/{deptId}")
	public void deleteDepartmentById(@PathVariable Long deptId) {

		deptService.deleteDepartmentById(deptId);
	}

	@GetMapping("/{deptName}/projects")
	public CollectionModel<Project> getProjects(@PathVariable String deptName) {
		List<Project> projects = deptService.getProjects(deptName);

		CollectionModel<Project> projectsModel = CollectionModel.of(projects);
		return projectsModel;
	}

}
