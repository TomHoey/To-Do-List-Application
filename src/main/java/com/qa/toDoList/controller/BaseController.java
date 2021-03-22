package com.qa.toDoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qa.toDoList.dto.CustomerDTO;

@Controller
public class BaseController {
	
	@GetMapping("/test")
	@ResponseBody
	public CustomerDTO returnAnything() {
		return new CustomerDTO(1, "JeffUser");
	}

}
