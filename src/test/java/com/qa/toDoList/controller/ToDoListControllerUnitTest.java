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

import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.dto.ToDoListDTO;
import com.qa.toDoList.mappers.ToDoListMapper;
import com.qa.toDoList.service.ToDoListService;

@SpringBootTest
public class ToDoListControllerUnitTest {
	
	@Autowired
	private ToDoListController tdlController;
	
	@MockBean
	private ToDoListService tdlService;
	
	@MockBean
	private ToDoListMapper tdlMapper;
	
	private List<ToDoList> validLists;
	private List<ToDoListDTO> validListDTOs;
	
	private ToDoList validList;
	private ToDoListDTO validListDTO;
	
	@BeforeEach
	public void init() { 
		validList = new ToDoList(1, "Listy");
		validListDTO = new ToDoListDTO(1, "ListMe");
		
		validLists = new ArrayList<ToDoList>();
		validListDTOs = new ArrayList<ToDoListDTO>();
		validLists.add(validList);
		validListDTOs.add(validListDTO);
		
	}
	
	@Test
	public void getAllListsTest() {
		
		when(tdlService.listAllLists()).thenReturn(validListDTOs);
		
		ResponseEntity<List<ToDoListDTO>> response =
				new ResponseEntity<List<ToDoListDTO>>(validListDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(tdlController.getAllLists());
		
		verify(tdlService, times(1)).listAllLists();
	}
	
	@Test
	public void createListTest() {
		
		when(tdlService.createList(Mockito.any(ToDoList.class)))
		.thenReturn(validListDTO);
	
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", String.valueOf(validListDTO.getToDoID()));
	
		ResponseEntity<ToDoListDTO> response = 
			new ResponseEntity<ToDoListDTO>(validListDTO, httpHeaders, HttpStatus.CREATED);
	
		assertThat(response).isEqualTo(tdlController.createList(validList));
	
		verify(tdlService, times(1)).createList(Mockito.any(ToDoList.class));
}	
	
	@Test
	public void updateListTest() {
		when(tdlService.updateList(validList.getToDoID(), validList))
				.thenReturn(validListDTO);
		
		ResponseEntity<ToDoListDTO> response = 
				new ResponseEntity<ToDoListDTO>(validListDTO, HttpStatus.OK);
		
		assertThat(response).isEqualTo(tdlController
				.updateList(validList.getToDoID(), validList));
		
		verify(tdlService, times(1)).updateList(validList.getToDoID(), validList);
	}
	
	@Test
	public void deleteListTest() {
		when(tdlService.deleteList(validList.getToDoID())).thenReturn(true);
		
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		
		assertThat(response).isEqualTo(tdlController
				.deleteList(validList.getToDoID()));
		
		
		verify(tdlService, times(1)).deleteList(validList.getToDoID());
	}

}
