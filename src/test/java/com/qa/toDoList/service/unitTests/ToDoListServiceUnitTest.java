package com.qa.toDoList.service.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.data.respository.ToDoListRepository;
import com.qa.toDoList.dto.ToDoListDTO;
import com.qa.toDoList.mappers.ToDoListMapper;
import com.qa.toDoList.service.ToDoListService;

@SpringBootTest
public class ToDoListServiceUnitTest {
	
	@Autowired
	private ToDoListService tdlService;
	
	@MockBean
	private ToDoListRepository tdlRepo;
	
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
	public void listAllListsTest() {
		
		when(tdlRepo.findAll()).thenReturn(validLists);
		when(tdlMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validListDTO);
		
		assertThat(validListDTOs).isEqualTo(tdlService.listAllLists());
		
		verify(tdlRepo, times(1)).findAll();
		verify(tdlMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}
	
	@Test
	public void createListTest() {
		
		when(tdlRepo.save(Mockito.any(ToDoList.class))).thenReturn(validList);
		when(tdlMapper.mapToDTO(Mockito.any(ToDoList.class))).thenReturn(validListDTO);
		
		assertThat(validListDTO).isEqualTo(tdlService.createList(validList));
		
		verify(tdlRepo, times(1)).save(Mockito.any(ToDoList.class));
		verify(tdlMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));

	}
	
	@Test
	public void updateListTest() {
		
		ToDoList updateList = new ToDoList(1, "Exercise");
		ToDoListDTO updateListDTO = new ToDoListDTO(1, "Exercise");
		
		when(tdlRepo.findById(Mockito.any(Integer.class)))
			.thenReturn(Optional.of(validList));
		
		when(tdlRepo.save(Mockito.any(ToDoList.class)))
			.thenReturn(updateList);
		
		when(tdlMapper.mapToDTO(Mockito.any(ToDoList.class)))
			.thenReturn(updateListDTO);
		
		ToDoListDTO testDTO = tdlService.updateList(validList.getToDoID()
				, updateList);
		
		assertThat(updateListDTO).isEqualTo(testDTO);
		
		verify(tdlRepo, times(1)).findById(Mockito.any(Integer.class));
		verify(tdlRepo, times(1)).save(Mockito.any(ToDoList.class));
		verify(tdlMapper, times(1)).mapToDTO(Mockito.any(ToDoList.class));
	}
	
	@Test
	public void deleteListTest() {
		
		when(tdlRepo.existsById(Mockito.any(Integer.class)))
			.thenReturn(true, false);
		
		assertThat(true).isEqualTo(tdlService.deleteList(validList.getToDoID()));
		
		verify(tdlRepo, times(2)).existsById(Mockito.any(Integer.class));
	}

}
