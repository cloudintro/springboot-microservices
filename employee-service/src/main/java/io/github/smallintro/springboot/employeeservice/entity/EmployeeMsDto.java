package io.github.smallintro.springboot.employeeservice.entity;

public class EmployeeMsDto {

	private String empId;
	private String empName;
	private String emailAddress;
	private String roleName;

	public EmployeeMsDto() {
		super();
	}

	public EmployeeMsDto(String empId, String empName, String emailAddress, String roleName) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.emailAddress = emailAddress;
		this.roleName = roleName;
	}

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
