package com.smallintro.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("hello")
public class DefaultController {
	
	@GetMapping
	public String getHealthStatus() {
		return "Hello World!!, I am Spring Boot Controller";
	}

}
