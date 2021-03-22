package com.qa.toDoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.service.TaskService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="/task")
public class TaskController {
	
	private TaskService taskService;
	
	@Autowired 
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<List<TaskDTO>> getTasksByID(@PathVariable("id") int id) {
		List<TaskDTO> task = taskService.readByID(id);
		return new ResponseEntity<List<TaskDTO>>(task, HttpStatus.OK);

	}
	
	@CrossOrigin
	@PostMapping("/{id}")
	public ResponseEntity<TaskDTO> createTask(@PathVariable("id") int id, @RequestBody Tasks task) {
		ToDoList newList = new ToDoList();
		newList.setToDoID(id);
		task.setToDoList(newList);
		
		System.out.println(task.getId());
		
		TaskDTO newTask = taskService.createTask(task);
		
		System.out.println(newTask.getId());
		
		HttpHeaders header = new HttpHeaders();
		header.add("Location", String.valueOf(newTask.getId()));
		
		return new ResponseEntity<TaskDTO>(newTask, header, HttpStatus.CREATED);
		
	}
	
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") int id, @RequestBody Tasks task) {
		TaskDTO newTask = taskService.updateTask(id, task);
		
		return new ResponseEntity<TaskDTO>(newTask, HttpStatus.OK);
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTask (@PathVariable("id") int id) {
		return new ResponseEntity<Boolean>(taskService.deleteTask(id), HttpStatus.NO_CONTENT);
	}

}
