package com.qa.toDoList.service.integrationTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.models.ToDoList;
import com.qa.toDoList.data.respository.ToDoListRepository;
import com.qa.toDoList.dto.ToDoListDTO;
import com.qa.toDoList.mappers.ToDoListMapper;
import com.qa.toDoList.service.ToDoListService;

	
	@SpringBootTest
	public class ToDoListServiceIntegrationTest {
		
		@Autowired
		private ToDoListService tdlService;
		
		@Autowired
		private ToDoListMapper tdlMapper;

		@Autowired
		private ToDoListRepository tdlRepo;

		private List<ToDoList> validToDoLists;
		private List<ToDoListDTO> validToDoListDTOs;

		private ToDoList validToDoList;
		private ToDoListDTO validToDoListDTO;
		
		@BeforeEach
		 public void init() {
			ArrayList<Tasks> listOfTasks =new ArrayList<Tasks>();
			validToDoList = new ToDoList(1);
			
			tdlRepo.deleteAll();
			
			validToDoList = tdlRepo.save(validToDoList);
			validToDoListDTO = tdlMapper.mapToDTO(validToDoList);
			
			validToDoLists = new ArrayList<ToDoList>();
			validToDoListDTOs = new ArrayList<ToDoListDTO>();
			
			validToDoLists.add(validToDoList);
			validToDoListDTOs.add(validToDoListDTO);
	
			
		}
		
		@Test
		public void readAllTest() {
		
			List<ToDoListDTO> listsInDB = tdlService.listAllLists();
			assertThat(validToDoListDTOs).isEqualTo(listsInDB); 
		
		}
		
		@Test
		public void createListTest() {
			
			ToDoList anotherList = new ToDoList(validToDoList.getToDoID(), "Rest", validToDoList.getTasks());
			ToDoListDTO anotherTaskDTO = tdlMapper.mapToDTO(anotherList);
			
			assertThat(anotherTaskDTO).isEqualTo(tdlService.createList(anotherList));
		}
		
		@Test
		public void updateListTest() { 
			
			ToDoList updateList = new ToDoList(validToDoList.getToDoID(), "Crying", validToDoList.getTasks());
			

			ToDoListDTO updateListDTO = tdlMapper.mapToDTO(updateList);
			ToDoListDTO listDTO = tdlService.updateList(validToDoList.getToDoID(), updateList);
			
			assertThat(updateListDTO).isEqualTo(listDTO);
			
		}
		
		@Test
		public void deleteListTest() {
			assertThat (tdlService.deleteList(validToDoList.getToDoID())).isTrue();
		}

}	

