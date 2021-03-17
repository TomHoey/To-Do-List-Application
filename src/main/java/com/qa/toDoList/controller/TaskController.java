package com.qa.toDoList.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.qa.toDoList.data.models.Status;
import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.service.TaskService;

import hateoas.assemblers.TaskDTOModelAssembler;

@RestController
@RequestMapping(path="/task")
@CrossOrigin
public class TaskController {
	
	private TaskService taskService;
	
	private TaskDTOModelAssembler taskDTOModelAssembler;
	
	@Autowired 
	public TaskController(TaskService taskService, TaskDTOModelAssembler taskDTOModelAssembler) {
		this.taskService = taskService;
		this.taskDTOModelAssembler = taskDTOModelAssembler;
		
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<TaskDTO>>> getAllTasks() {
		List<TaskDTO> data = taskService.readAllTasks();
		
		List<EntityModel<TaskDTO>> entityModels = data.stream().map(task ->
				taskDTOModelAssembler.toModel(task)).collect(Collectors.toList());
		
		CollectionModel<EntityModel<TaskDTO>> collectionModel = CollectionModel.of
				(entityModels, linkTo(methodOn(TaskController.class).getAllTasks()).withSelfRel()
						);
		
		return new ResponseEntity<CollectionModel<EntityModel<TaskDTO>>>
			(collectionModel, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<TaskDTO>> getTaskByID(@PathVariable("id") Integer id) {
		
		TaskDTO task = taskService.readByID(id);
		
		EntityModel<TaskDTO> taskEntityModel = EntityModel.of(task,
				linkTo(methodOn(TaskController.class).getTaskByID(id)).withSelfRel(),
				linkTo(methodOn(TaskController.class).getAllTasks()).withRel("tasks"));
		
		return new ResponseEntity<EntityModel<TaskDTO>>(taskEntityModel, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createTask(@Valid @RequestBody Tasks task) {
		TaskDTO newTask = taskService.createTask(task);
		
		EntityModel<TaskDTO> taskmodel = taskDTOModelAssembler.toModel(newTask);
		
		ResponseEntity<?> response = ResponseEntity.created(
				taskmodel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(taskmodel);
		return response;
	}
	
	@PutMapping
	public ResponseEntity<?> updateTask(@PathVariable("id") Integer id, @RequestBody Tasks task) {
		
		if (!task.getStatus().equals(Status.COMPLETED)) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
					.body(Problem.create()
							.withTitle("METHOD NOT ALLOWED")
							.withDetail("You cannot update a task with the " + task.getStatus() 
								+ "status"));
		}
		
		TaskDTO updatedTask = taskService.updateTask(id, task);
		EntityModel<TaskDTO> model = taskDTOModelAssembler.toModel(updatedTask);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Location", model.getRequiredLink(IanaLinkRelations.SELF).toUri().toString());
		return new ResponseEntity<EntityModel<TaskDTO>>(model, header, HttpStatus.OK);
	}

}
