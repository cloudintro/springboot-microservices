package com.smallintro.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smallintro.springboot.entity.Project;
import com.smallintro.springboot.exception.RecordNotFoundException;
import com.smallintro.springboot.service.ProjectService;
import com.smallintro.springboot.utils.ProjectConstants;

import io.swagger.annotations.Api;

@Api(tags = "Project management APIs", value = "Project Controller")
@RestController
@RequestMapping("project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping
	public List<Project> getProjects() {
		return projectService.findAllProjects();
	}

	@GetMapping(value = "/{projectName}")
	public Project getProjectInfo(@PathVariable String projectName) {
		try {
			return projectService.findByProjectName(projectName);
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping
	public String addProject(@RequestBody Project proj) {
		if (projectService.isProjectExistsByName(proj.getProjectName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Project with name " + proj.getProjectName() + " already exists");
		}
		projectService.saveProject(proj);
		return ProjectConstants.OPERATION_SUCCESS;
	}

	@PutMapping(value = "/{projectCode}")
	public String updateProject(@PathVariable Integer projectCode, @RequestBody Project proj) {
		if (!projectService.isProjectExistsById(projectCode)) {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "No Project found with id: " + projectCode);
		}
		projectService.saveProject(proj);
		return ProjectConstants.OPERATION_SUCCESS;
	}

	@DeleteMapping(value = "/{projectCode}")
	public String delProject(@PathVariable Integer projectCode) {
		try {
			projectService.deleteById(projectCode);
			return ProjectConstants.OPERATION_SUCCESS;

		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
