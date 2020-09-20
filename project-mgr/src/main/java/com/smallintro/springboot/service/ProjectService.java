package com.smallintro.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallintro.springboot.entity.Project;
import com.smallintro.springboot.exception.RecordNotFoundException;
import com.smallintro.springboot.repository.ProjectRepo;

@Service
public class ProjectService {
	@Autowired
	ProjectRepo projRepo;

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

}
