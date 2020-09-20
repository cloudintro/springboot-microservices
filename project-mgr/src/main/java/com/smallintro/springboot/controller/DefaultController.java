package com.smallintro.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smallintro.springboot.utils.ApplicationUtils;

@RestController()
@RequestMapping("hello")
public class DefaultController {

	@Autowired
	ApplicationUtils appUtils;
	@GetMapping
	public String getHealthStatus(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return appUtils.getI18nMessage("message.hello.world", locale);
	}

}
