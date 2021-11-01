package io.github.smallintro.springboot.employeeservice.controller;

import java.util.List;

import javax.validation.constraints.Min;

import io.github.smallintro.springboot.employeeservice.entity.EmpProject;
import io.github.smallintro.springboot.employeeservice.entity.Employee;
import io.github.smallintro.springboot.employeeservice.entity.EmployeeMmDto;
import io.github.smallintro.springboot.employeeservice.entity.EmployeeMsDto;
import io.github.smallintro.springboot.employeeservice.entity.Project;
import io.github.smallintro.springboot.employeeservice.exception.RecordAlreadyExistsException;
import io.github.smallintro.springboot.employeeservice.exception.RecordNotFoundException;
import io.github.smallintro.springboot.employeeservice.service.EmployeeService;
import io.github.smallintro.springboot.employeeservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;

@Api(tags = "Employee management APIs", value = "Employee Controller")
@RestController
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	//@Autowired
	//private RestTemplate restTemplate;

	@Autowired
	private ProjectService projectService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
		return empService.getEmployees();
	}

	@GetMapping(value = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmpInfoById(@PathVariable String empId) {
		try {
			return empService.getEmpInfoById(empId);
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee addEmployee(@RequestBody Employee emp) {

		try {
			return empService.addEmployee(emp);
		} catch (RecordAlreadyExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@PutMapping(value = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateEmployee(@PathVariable String empId, @RequestBody Employee emp) {
		return empService.updateEmployee(empId, emp);
	}

	@DeleteMapping(value = "/{empId}")
	public String delEmployee(@Min(1) @PathVariable String empId) {
		return empService.delEmployee(empId);
	}

	@GetMapping(value = "/{empId}/modelmapper", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeMmDto getEmpInfoModelMapper(@PathVariable String empId) {
		try {
			return empService.getEmpInfoModelMapper(empId);
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(value = "/{empId}/mapstruct", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeMsDto getEmpInfoMapStruct(@PathVariable String empId) {
		try {
			return empService.getEmpInfoMapStruct(empId);
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(value = "/{empId}/project", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmpProject getEmpProject(@PathVariable String empId) {
		try {

			Employee emp = empService.getEmpInfoById(empId);

			/*
			 * ResponseEntity<Project> project = restTemplate.exchange(
			 * "http://localhost:8082/project/" + emp.getProjectCode() + "/external",
			 * HttpMethod.GET, null, new ParameterizedTypeReference<Project>() { });
			 */

			ResponseEntity<Project> project = projectService.getProjectByProjectCode(emp.getProjectCode());

			return new EmpProject(emp, project.getBody());
		} catch (RecordNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
