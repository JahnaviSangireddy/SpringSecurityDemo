package com.springsecuritydemo.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
	
	@GetMapping("/sayHi")
	public String sayHi() {
		return "Hi";
	}
	
	@GetMapping("/sayHello")
	public String sayHello() {
		return "Hello";
	}

}
