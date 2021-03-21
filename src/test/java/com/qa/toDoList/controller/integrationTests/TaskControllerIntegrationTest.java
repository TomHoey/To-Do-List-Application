package com.qa.toDoList.controller.integrationTests;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.toDoList.controller.TaskController;
import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.data.respository.TaskRepository;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.mappers.TaskMapper;
import com.qa.toDoList.service.TaskService;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql" }, 
	 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TaskControllerIntegrationTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TaskController taskController;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Tasks validTask = new Tasks(1, "Running", "Run 5km");
	private TaskDTO validTaskDTO = new TaskDTO(1, "Running", "Run 5Km");

	private List<Tasks> validTasks = List.of(validTask);
	private List<TaskDTO> validTaskDTOs = List.of(validTaskDTO);
	
	private ToDoList validList= new ToDoList(1,"Exercise", validTasks);

	@Test
	public void createTaskTest() throws Exception {
		Tasks taskSave = new Tasks("Running", "Run 5km");
		taskSave.setToDoList(validList);
		
		TaskDTO requiredTask = new TaskDTO(2, "Sprinting", "100m");
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/task/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON); 
		mockRequest.content(objectMapper.writeValueAsString(taskSave));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(requiredTask));
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher)
		   .andExpect(headerMatcher);
	}
	
	
	@Test
	public void readTaskByID () throws Exception {
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/task/1");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validTaskDTO));
		
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
}
