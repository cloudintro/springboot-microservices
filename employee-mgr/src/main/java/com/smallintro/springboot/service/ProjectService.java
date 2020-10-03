package com.smallintro.springboot.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smallintro.springboot.entity.Project;

@Service
//@FeignClient(name="project-service",url="http://localhost:8082")
@FeignClient(name="project-service")
@RibbonClient(name="project-service")
public interface ProjectService {
	
	// TODO not able to discover project-service
	@RequestMapping("/project/{id}/external")
	ResponseEntity<Project> getProjectByProjectCode(@PathVariable ("id") Integer projectCode);

}
