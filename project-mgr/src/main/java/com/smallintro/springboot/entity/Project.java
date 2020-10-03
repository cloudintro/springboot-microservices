package com.smallintro.springboot.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;

@ApiModel("This model is for project information")
@Entity
@Table(name = "tb_project")
public class Project extends RepresentationModel<Project> {

	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Integer projectCode;

	@JsonView(Views.External.class)
	private String projectName;

	@JsonView(Views.Internal.class)
	private Date startDate;

	@JsonView(Views.Internal.class)
	private String projectStatus;

	@JsonView(Views.Internal.class)
	private Long budget;

	@JsonView(Views.External.class)
	private String description;

	@ManyToOne
	@JsonView(Views.External.class)
	private Department department;

	public Project(Integer projectCode, String projectName) {
		super();
		this.projectCode = projectCode;
		this.projectName = projectName;
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
