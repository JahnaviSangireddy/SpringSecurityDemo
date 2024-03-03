package com.springsecuritydemo.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {
	
	@GetMapping("/saybye")
	public String sayBye() {
		return "Bye bye !!";
	}

}
