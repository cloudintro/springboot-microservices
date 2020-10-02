package com.smallintro.springboot.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.smallintro.springboot.entity.Technology;
import com.smallintro.springboot.repository.TechRepo;

@Service
public class TechService {

	@Autowired
	private TechRepo techRepo;

	public List<Technology> getTechnologies() {
		return techRepo.findAll();
	}

	// @RequestParam filter
	public MappingJacksonValue getTechnologyByName(String techName, Set<String> fields) {
		Technology tech = techRepo.findByTechName(techName);
		if (null != tech) {
			FilterProvider filters = new SimpleFilterProvider().addFilter("techFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(tech);

			mapper.setFilters(filters);
			return mapper;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No technology found with the name " + techName);
	}

	// MappingJacksonValue Filter
	public MappingJacksonValue getTechnologyById(Integer techId) {
		Optional<Technology> Optionaltech = techRepo.findById(techId);
		if (null != Optionaltech) {
			Technology tech = Optionaltech.get();
			Set<String> fields = new HashSet<String>();
			fields.add("techName");
			fields.add("catagory");
			fields.add("description");
			FilterProvider filters = new SimpleFilterProvider().addFilter("techFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(tech);

			mapper.setFilters(filters);
			return mapper;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No technology found with the techId " + techId);
	}

	public List<Technology> addTech(@RequestBody List<Technology> techList) {
		for (Technology tech : techList) {
			if (techRepo.existsById(tech.getTechId())) {

				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Technology with Id " + tech.getTechId() + " already exists");
			}

		}
		List<Technology> newTechList = new ArrayList<Technology>();
		for (Technology tech : techList) {
			newTechList.add(techRepo.save(tech));

		}
		return newTechList;
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
