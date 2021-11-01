package io.github.smallintro.springboot.projectservice.controller;

import java.util.List;

import io.github.smallintro.springboot.projectservice.entity.Department;
import io.github.smallintro.springboot.projectservice.entity.Project;
import io.github.smallintro.springboot.projectservice.entity.Views;
import io.github.smallintro.springboot.projectservice.exception.RecordNotFoundException;
import io.github.smallintro.springboot.projectservice.service.DepartmentService;
import io.github.smallintro.springboot.projectservice.service.ProjectService;
import io.github.smallintro.springboot.projectservice.utils.ProjectConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;

@Api(tags = "Project management APIs", value = "Project Controller")
@RestController
@RequestMapping("project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private DepartmentService deptService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getProjects() {
		return projectService.findAllProjects();
	}

	@JsonView(Views.Internal.class)
	@GetMapping(value = "/{projectCode}/internal",produces = MediaType.APPLICATION_JSON_VALUE)
	public Project getProjectInfoForInternal(@PathVariable Integer projectCode) {
		try {
			return projectService.findByProjectCodeForInternal(projectCode);
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@JsonView(Views.External.class)
	@GetMapping(value = "/{projectCode}/external",produces = MediaType.APPLICATION_JSON_VALUE)
	public Project getProjectInfoForExrernal(@PathVariable Integer projectCode) {
		try {
			return projectService.findByProjectCodeForExternal(projectCode);
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(value = "/{projectName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Project getProjectInfo(@PathVariable String projectName) {
		try {
			return projectService.findByProjectName(projectName);
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping(value = "/{deptId}/project",produces = MediaType.APPLICATION_JSON_VALUE)
	public Project addProject(@PathVariable Long deptId, @RequestBody Project proj) {
		Department dept = deptService.getDepartmentById(deptId);
		if (null == dept) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No department found with department Id: " + deptId);
		}
		if (projectService.isProjectExistsByName(proj.getProjectName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Project with name " + proj.getProjectName() + " already exists");
		}
		proj.setDepartment(dept);
		return projectService.saveProject(proj);

	}

	@PutMapping(value = "/{projectCode}",produces = MediaType.APPLICATION_JSON_VALUE)
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
