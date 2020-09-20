package com.smallintro.springboot.controller;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smallintro.springboot.entity.Technology;
import com.smallintro.springboot.service.TechService;
import com.smallintro.springboot.utils.ProjectConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(tags = "Technology management APIs", value = "Technology Controller")
@RestController
@RequestMapping("technology")
@Validated
public class TechController {

	@Autowired
	TechService techService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Technology> getTechnologies() {
		return techService.getTechnologies();
	}

	@GetMapping(value = "/{techName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MappingJacksonValue getTechnologyByName(@ApiParam(value = "Technology name") @PathVariable String techName,
			@RequestParam Set<String> fields) {
		return techService.getTechnologyByName(techName, fields);
	}

	@GetMapping(value = "/id/{techId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MappingJacksonValue getTechnologyById(@Min(1) @PathVariable Integer techId) {

		return techService.getTechnologyById(techId);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Technology> addTech(@RequestBody List<Technology> techList) {
		return techService.addTech(techList);

	}

	@PutMapping(value = "/{techId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateTechnology(@PathVariable Integer techId, @RequestBody Technology tech) {
		if (techService.isTechExistsById(techId)) {
			techService.updateTechnology(tech);
			return ProjectConstants.OPERATION_SUCCESS;
		}
		return ProjectConstants.OPERATION_FAILED;
	}

	@DeleteMapping(value = "/{techId}")
	public String delTechnology(@PathVariable Integer techId) {
		if (techService.isTechExistsById(techId)) {
			techService.delTechnology(techId);
			return ProjectConstants.OPERATION_SUCCESS;
		}
		return ProjectConstants.OPERATION_FAILED;
	}

}