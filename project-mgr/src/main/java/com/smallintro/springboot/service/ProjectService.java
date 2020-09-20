package com.smallintro.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.smallintro.springboot.entity.Project;
import com.smallintro.springboot.exception.RecordNotFoundException;
import com.smallintro.springboot.repository.DepartmentRepo;
import com.smallintro.springboot.repository.ProjectRepo;

@Service
public class ProjectService {
	@Autowired
	ProjectRepo projRepo;
	@Autowired
	DepartmentRepo deptRepo;

	public List<Project> findAllProjects() {

		return projRepo.findAll();
	}

	public Project findByProjectName(String projectName) throws RecordNotFoundException {
		Project project = projRepo.findByProjectName(projectName);
		if (null == project) {
			throw new RecordNotFoundException("No Project found with name: " + projectName);
		}

		return project;
	}

	public Project saveProject(Project proj) {
		if (!deptRepo.existsById(proj.getDepartment().getDeptId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid department Id");
		}
		return projRepo.save(proj);
	}

	public void deleteById(Integer projectCode) throws RecordNotFoundException {
		if (!projRepo.existsById(projectCode)) {
			throw new RecordNotFoundException("No Project found with id: " + projectCode);

		}
		projRepo.deleteById(projectCode);

	}

	public boolean isProjectExistsById(Integer projectCode) {

		return projRepo.existsById(projectCode);
	}

	public boolean isProjectExistsByName(String projectName) {
		return projRepo.existsByProjectName(projectName);
	}

	public Project findByProjectCodeForInternal(Integer projectCode) throws RecordNotFoundException {
		if (!projRepo.existsById(projectCode)) {
			throw new RecordNotFoundException("No Project found with id: " + projectCode);

		}
		return projRepo.findById(projectCode).get();
	}

	public Project findByProjectCodeForExternal(Integer projectCode) throws RecordNotFoundException {
		if (!projRepo.existsById(projectCode)) {
			throw new RecordNotFoundException("No Project found with id: " + projectCode);

		}
		return projRepo.findById(projectCode).get();
	}

}
