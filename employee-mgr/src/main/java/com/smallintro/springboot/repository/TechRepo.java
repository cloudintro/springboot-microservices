package com.smallintro.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smallintro.springboot.entity.Technology;

@Repository
public interface TechRepo extends JpaRepository<Technology, Integer> {

	@Query(value = "from Technology where upper(techName) = upper(:techName)")
	Technology findByTechName(@Param("techName") String techName);
}
