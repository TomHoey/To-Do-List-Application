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

@RestController
@RequestMapping(path="/task")
@CrossOrigin
public class TaskController {
	
	private TaskService taskService;
	
	@Autowired 
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<TaskDTO>> getTasksByID(@PathVariable("id)") int id) {
		List<TaskDTO> task = taskService.readByID(id);
		return new ResponseEntity<List<TaskDTO>>(task, HttpStatus.OK);

	}
	
	@PostMapping("/{id}")
	public ResponseEntity<TaskDTO> createTask(@PathVariable("id)") Integer id, @RequestBody Tasks task) {
		ToDoList newList = new ToDoList();
		newList.setToDoID(id);
		task.setToDoList(newList);
		TaskDTO newTask = taskService.createTask(task);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Location", String.valueOf(newTask.getId()));
		return new ResponseEntity<TaskDTO>(newTask, header, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") Integer id, @RequestBody Tasks task) {
		TaskDTO newTask = taskService.updateTask(id, task);
		
		return new ResponseEntity<TaskDTO>(newTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTask (@PathVariable("id") Integer id) {
		return new ResponseEntity<Boolean>(taskService.deleteTask(id), HttpStatus.NO_CONTENT);
	}

}
