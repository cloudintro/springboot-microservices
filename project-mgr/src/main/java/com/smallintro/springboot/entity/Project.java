package com.smallintro.springboot.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

@ApiModel("This model is for project information")
@Entity
@Table(name = "tb_project")
public class Project extends RepresentationModel<Project>{

	@Id
	@GeneratedValue
	private Integer projectCode;
	private String projectName;
	private Date startDate;
	private String projectStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Department department;

	
	
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
