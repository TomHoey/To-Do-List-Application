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
import com.qa.toDoList.controller.ToDoListController;
import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.data.respository.ToDoListRepository;
import com.qa.toDoList.dto.ToDoListDTO;
import com.qa.toDoList.mappers.ToDoListMapper;
import com.qa.toDoList.service.ToDoListService;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql" }, 
	 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ToDoListControllerIntegrationTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ToDoListController tdlController;
	
	@Autowired
	private ToDoListService tdlService;
	
	@Autowired
	private ToDoListRepository tdlRepo;
	
	@Autowired
	private ToDoListMapper tdlMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private ToDoList validList = new ToDoList(1, "Exercise");
	private ToDoListDTO validListDTO = new ToDoListDTO(1, "Exercise");

	private List<ToDoList> validLists = List.of(validList);
	private List<ToDoListDTO> validListDTOs = List.of(validListDTO);

	@Test
	public void createListTest() throws Exception {
		
		ToDoList newList = new ToDoList(2, "Exercise");
		ToDoListDTO newListDTO = new ToDoListDTO(2, "Exercise");
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/list");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON); 
		mockRequest.content(objectMapper.writeValueAsString(newList));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(newListDTO));
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
		
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher)
		   .andExpect(headerMatcher);
	}
	
	
	@Test
	public void readAllLists() throws Exception {
		
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/list");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(validListDTOs));
		
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	 void updateListTest() throws Exception {
		
		 ToDoList newList = new ToDoList(1, "Exercise");
		 ToDoListDTO newListDTO = new ToDoListDTO(1, "Exercise"); 
		
		
		 MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/list/1");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newList));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(newListDTO));
		
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
	
	@Test
	public void deleteLists() throws Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/list/1");
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isNoContent();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
		
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher);
	}
}