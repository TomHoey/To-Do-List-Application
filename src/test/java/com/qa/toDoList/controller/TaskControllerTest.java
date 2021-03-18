package com.qa.toDoList.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.mappers.TaskMapper;
import com.qa.toDoList.service.TaskService;

@SpringBootTest
public class TaskControllerTest {
	
	
	@Autowired
	private TaskController taskController;
	
	@MockBean
	private TaskService taskService;
	
	@MockBean
	private TaskMapper taskMapper;
	
	private List<Tasks> validTasks;
	private List<TaskDTO> validTaskDTOs;
	
	private Tasks validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() { 
		validTask = new Tasks(1, "Task", "TaskyTask");
		validTaskDTO = new TaskDTO(1, "Task", "TaskyTask");
		
		validTasks = new ArrayList<Tasks>();
		validTaskDTOs = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDTOs.add(validTaskDTO);
		
	}
	
	@Test
	public void getAllTasksTest() {
		
		when(taskService.readByID(1)).thenReturn(validTaskDTOs);
		
		ResponseEntity<List<TaskDTO>> response =
				new ResponseEntity<List<TaskDTO>>(validTaskDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.getTasksByID(1));
		
		verify(taskService, times(1)).readByID(1);
	}
	
	@Test
	public void createTasksTest() {
		
		when(taskService.createTask(Mockito.any(Tasks.class)))
		.thenReturn(validTaskDTO);
	
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", String.valueOf(validTaskDTO.getId()));
	
		ResponseEntity<TaskDTO> response = 
			new ResponseEntity<TaskDTO>(validTaskDTO, httpHeaders, HttpStatus.CREATED);
	
		assertThat(response).isEqualTo(taskController.createTask(1, validTask));
	
		verify(taskService, times(1)).createTask(Mockito.any(Tasks.class));
	}	
	
	@Test
	public void updateTaskTest() {
		when(taskService.updateTask(validTask.getId(), validTask))
				.thenReturn(validTaskDTO);
		
		ResponseEntity<TaskDTO> response = 
				new ResponseEntity<TaskDTO>(validTaskDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController
				.updateTask(validTask.getId(), validTask));
		
		verify(taskService, times(1)).updateTask(validTask.getId(), validTask);
	}
	
	@Test
	public void deleteTaskTest() {
		when(taskService.deleteTask(validTask.getId())).thenReturn(true);
		
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		
		assertThat(response).isEqualTo(taskController
				.deleteTask(validTask.getId()));
		
		
		verify(taskService, times(1)).deleteTask(validTask.getId());
	}
		
}
