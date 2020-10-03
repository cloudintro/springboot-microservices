package com.smallintro.springboot.entity;

import org.springframework.hateoas.RepresentationModel;

public class Project extends RepresentationModel<Project> {

	private Integer projectCode;

	private String projectName;

	private String description;

	public Project() {
		super();
	}

	public Integer getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(Integer projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
