package com.smallintro.springboot.utils;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.smallintro.springboot.entity.Employee;
import com.smallintro.springboot.entity.EmployeeMsDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

	// For single mapping @Mapping is enough, use @Mappings for multiple mappings
	@Mappings({
		@Mapping(source = "emailId", target = "emailAddress"), 
		@Mapping(source = "role", target = "roleName") 
		})
	EmployeeMsDto employeeToEmployeeMsDto(Employee emp);

	List<EmployeeMsDto> employeesToEmployeeMsDtos(List<Employee> emp);
}
