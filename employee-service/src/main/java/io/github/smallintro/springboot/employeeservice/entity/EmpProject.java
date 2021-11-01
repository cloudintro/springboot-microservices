package io.github.smallintro.springboot.employeeservice.entity;

public class EmpProject {

	private Employee employee;
	private Project project;

	public EmpProject(Employee employee, Project project) {
		super();
		this.employee = employee;
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
