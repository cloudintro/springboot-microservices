package com.smallintro.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smallintro.springboot.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
	
	@Query(value = "from Project where upper(projectName) = upper(:projectName)")
	Project findByProjectName(@Param("projectName") String projectName);

	boolean existsByProjectName(String projectName);
}
