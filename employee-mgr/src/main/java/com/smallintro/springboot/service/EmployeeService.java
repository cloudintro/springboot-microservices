package com.smallintro.springboot.service;

import java.util.List;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallintro.springboot.entity.Employee;
import com.smallintro.springboot.entity.EmployeeMmDto;
import com.smallintro.springboot.entity.EmployeeMsDto;
import com.smallintro.springboot.exception.RecordAlreadyExistsException;
import com.smallintro.springboot.exception.RecordNotFoundException;
import com.smallintro.springboot.repository.EmployeeRepo;
import com.smallintro.springboot.utils.EmployeeMapper;
import com.smallintro.springboot.utils.ProjectConstants;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;

	@Autowired
	private ModelMapper modelMapper;

	//@Autowired
	private EmployeeMapper empMapper;

	public List<Employee> getEmployees() {
		return empRepo.findAll();
	}

	public Employee getEmpInfoById(String empId) {
		return empRepo.findByEmpId(empId);
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
