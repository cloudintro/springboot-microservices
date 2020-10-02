package com.smallintro.springboot.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("This model is for employee information")
@Entity
@Table(name = "tb_employee")
public class Employee extends RepresentationModel<Employee> {

	@ApiModelProperty(notes = "Employee ID should have first name initial+ Gender initial + YYYYMMDDHHMM", required = true, example = "SM201104180925", position = 1)
	@Id
	private String empId;
	private String empName;
	private Integer projectCode;
	private String emailId;
	private String role;
	private Date joiningDate;

	@ManyToMany(mappedBy = "employees")
	private List<Technology> technologies = new ArrayList<Technology>();

	private String mgrId;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(Integer projectCode) {
		this.projectCode = projectCode;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getMgrId() {
		return mgrId;
	}

	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public List<Technology> getTechnologies() {

		List<Technology> techList = new ArrayList<Technology>();

		for (Technology tech : technologies) {

			techList.add(new Technology(tech.getTechId(), tech.getTechName()));

		}

		return techList;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

}
