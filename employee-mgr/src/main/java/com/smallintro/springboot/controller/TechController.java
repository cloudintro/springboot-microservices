package com.smallintro.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smallintro.springboot.entity.Technology;
import com.smallintro.springboot.service.TechService;
import com.smallintro.springboot.utils.ProjectConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(tags = "Technology management APIs", value = "Technology Controller")
@RestController
@RequestMapping("technology")
public class TechController {

	@Autowired
	TechService techService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Technology> getTechnologys() {
		return techService.getTechnologys();
	}

	@GetMapping(value = "/{techName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Technology getTechnologyInfo(@ApiParam(value = "Technology name") @PathVariable String techName) {
		return techService.getTechnologyInfo(techName);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public String addTech(@RequestBody List<Technology> techList) {
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