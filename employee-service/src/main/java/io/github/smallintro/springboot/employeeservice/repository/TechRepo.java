package io.github.smallintro.springboot.employeeservice.repository;

import io.github.smallintro.springboot.employeeservice.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TechRepo extends JpaRepository<Technology, Integer> {

	@Query(value = "from Technology where upper(techName) = upper(:techName)")
	Technology findByTechName(@Param("techName") String techName);
}
