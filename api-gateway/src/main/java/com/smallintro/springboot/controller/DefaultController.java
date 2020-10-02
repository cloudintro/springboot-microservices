package com.smallintro.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("hello")
public class DefaultController {

	// TODO check for the reading list using @Value
	//@Value("${spring.application.name}")
	private String configServer = "localhost:9081";

	@Autowired
	private Environment env;

	@GetMapping
	public String getHealthStatus(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return "Hello!! \n Welcome to "
				+ env.getProperty(("spring.application.name"))
				+ ". Configuration for this service is managed by config-server running at " + configServer;
	}

}
