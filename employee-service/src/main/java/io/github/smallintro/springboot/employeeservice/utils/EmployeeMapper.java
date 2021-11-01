package io.github.smallintro.springboot.employeeservice.utils;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.github.smallintro.springboot.employeeservice.entity.Employee;
import io.github.smallintro.springboot.employeeservice.entity.EmployeeMsDto;

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
