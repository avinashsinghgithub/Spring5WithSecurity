package com.luv2code.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
 
	@GetMapping("/")
	public String showLanding() {
		
		return "landing";
	}
}