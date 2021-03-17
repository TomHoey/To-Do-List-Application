package com.qa.toDoList.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.data.respository.ToDoListRepository;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.dto.ToDoListDTO;
import com.qa.toDoList.mappers.ToDoListMapper;

@ExtendWith(MockitoExtension.class)
public class ToDoListServiceUnitTest {
	
	@InjectMocks
	private ToDoListService tdlService;
	
	@Mock
	private ToDoListRepository tdlRepo;
	
	@Mock
	private ToDoListMapper tdlMapper;
	
	private List<ToDoList> toDos;
	private List<ToDoListDTO> toDoDTOs;
	
	private ToDoList validToDoList;
	private ToDoListDTO validToDoListDTO;
	
	private Tasks validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	private void init() {
		validToDoList = new ToDoList(1, "Exercise");
		validTask = new Tasks(1, "Run", "Achieve 5k");
		validTaskDTO = new TaskDTO(1, "Run");
		validToDoListDTO = new ToDoListDTO (1, "Exercise", List.of(validTaskDTO));
		
		toDos = List.of(validToDoList);
		toDoDTOs = List.of(validToDoListDTO);
	}
	

	@Test
	public void ReadAllToDoListsTest() {
		
	}

}
