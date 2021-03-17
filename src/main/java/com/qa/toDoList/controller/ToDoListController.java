package com.qa.toDoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.toDoList.dto.ToDoListDTO;
import com.qa.toDoList.service.ToDoListService;

@RestController
@RequestMapping("/list")
public class ToDoListController {
	
	private ToDoListService tdlService;
	
	@Autowired
	public ToDoListController(ToDoListService tdlService) throws Exception {
		this.tdlService = tdlService;
	}
	
	@GetMapping
	public ResponseEntity<List<ToDoListDTO>> getAllLists() {
		List<ToDoListDTO> data = tdlService.readAllLists();
		
		return new ResponseEntity<List<ToDoListDTO>>(data, HttpStatus.OK);
	}

}
