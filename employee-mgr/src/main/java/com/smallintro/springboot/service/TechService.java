package com.smallintro.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.smallintro.springboot.entity.Technology;
import com.smallintro.springboot.repository.TechRepo;
import com.smallintro.springboot.utils.ProjectConstants;

public class TechService {

	@Autowired
	TechRepo techRepo;

	public List<Technology> getTechnologys() {
		return techRepo.findAll();
	}

	public Technology getTechnologyInfo(String techName) {
		return techRepo.findByTechName(techName);
	}

	public String addTech(@RequestBody List<Technology> techList) {
		for (Technology tech : techList) {
			if (techRepo.existsById(tech.getTechId())) {

				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Technology with Id " + tech.getTechId() + " already exists");
			}

		}
		for (Technology tech : techList) {
			techRepo.save(tech);

		}
		return ProjectConstants.OPERATION_SUCCESS;
	}

	public Technology updateTechnology(Technology tech) {
			return techRepo.save(tech);
	}

	public void delTechnology(Integer techId) {
			techRepo.deleteById(techId);
	}

	public boolean isTechExistsById(Integer techId) {
		return techRepo.existsById(techId);
	}

}
