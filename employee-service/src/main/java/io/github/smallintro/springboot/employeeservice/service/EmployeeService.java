package io.github.smallintro.springboot.employeeservice.service;

import java.util.List;

import javax.validation.constraints.Min;

import io.github.smallintro.springboot.employeeservice.entity.Employee;
import io.github.smallintro.springboot.employeeservice.entity.EmployeeMmDto;
import io.github.smallintro.springboot.employeeservice.entity.EmployeeMsDto;
import io.github.smallintro.springboot.employeeservice.exception.RecordAlreadyExistsException;
import io.github.smallintro.springboot.employeeservice.utils.EmployeeMapper;
import io.github.smallintro.springboot.employeeservice.utils.ProjectConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.smallintro.springboot.employeeservice.exception.RecordNotFoundException;
import io.github.smallintro.springboot.employeeservice.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;

	@Autowired
	private ModelMapper modelMapper;

	// TODO Failed to create bean
	// @Autowired
	private EmployeeMapper empMapper;

	public List<Employee> getEmployees() {
		return empRepo.findAll();
	}

	public Employee getEmpInfoById(String empId) throws RecordNotFoundException {
		Employee emp = empRepo.findByEmpId(empId);
		if (null == emp) {
			throw new RecordNotFoundException("Employee with empId " + empId + " not found");
		}
		return emp;
	}

	public Employee addEmployee(Employee emp) throws RecordAlreadyExistsException {
		if (existsByEmpId(emp.getEmpId())) {
			throw new RecordAlreadyExistsException("Employee with empId " + emp.getEmpId() + " already exists");
		}
		return empRepo.save(emp);

	}

	public String updateEmployee(String empId, Employee emp) {
		if (existsByEmpId(empId)) {
			empRepo.save(emp);
			return ProjectConstants.OPERATION_SUCCESS;
		}
		return ProjectConstants.OPERATION_FAILED;
	}

	public String delEmployee(@Min(1) String empId) {
		if (existsByEmpId(empId)) {
			empRepo.delete(empRepo.findByEmpId(empId));
			return ProjectConstants.OPERATION_SUCCESS;
		}
		return ProjectConstants.OPERATION_FAILED;
	}

	public boolean existsByEmpId(String empId) {
		return null != empRepo.findByEmpId(empId);
	}

	// Model Mapper DTO
	public EmployeeMmDto getEmpInfoModelMapper(String empId) throws RecordNotFoundException {
		Employee emp = empRepo.findByEmpId(empId);
		if (null == emp) {
			throw new RecordNotFoundException("Employee with empId " + empId + " not found");
		}
		EmployeeMmDto empMmDto = modelMapper.map(emp, EmployeeMmDto.class);
		return empMmDto;
	}

	// Map Struct DTO
	public EmployeeMsDto getEmpInfoMapStruct(String empId) throws RecordNotFoundException {
		Employee emp = empRepo.findByEmpId(empId);
		if (null == emp) {
			throw new RecordNotFoundException("Employee with empId " + empId + " not found");
		}
		EmployeeMsDto empMsDto = empMapper.employeeToEmployeeMsDto(emp);
		return empMsDto;
	}


}
