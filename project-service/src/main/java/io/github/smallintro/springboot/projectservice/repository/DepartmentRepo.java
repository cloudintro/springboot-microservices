package io.github.smallintro.springboot.projectservice.repository;

import io.github.smallintro.springboot.projectservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
	
	public Department findByDeptName(String deptName);

	public Department findByDeptId(Long deptId);

}
