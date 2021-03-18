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

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.respository.TaskRepository;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.mappers.TaskMapper;
import com.qa.toDoList.service.TaskService;

@SpringBootTest
public class TaskServiceUnitTest {
	
	@Autowired
	private TaskService taskService;
	
	@MockBean
	private TaskRepository taskRepo;
	
	@MockBean 
	private TaskMapper taskMapper;
	
	private List<Tasks> validTasks;
	private List<TaskDTO> validTaskDTOs;
	
	private Tasks validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() { 
		validTask = new Tasks(1, "Tasky", "TaskDescription");
		validTaskDTO = new TaskDTO(1, "Tasky", "TaskDescription");
		
		validTasks = new ArrayList<Tasks>();
		validTaskDTOs = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDTOs.add(validTaskDTO);
		
	} 
	
	@Test
	public void readTaskByIDTest() { 
		
		when(taskRepo.findForToDoList(1)).thenReturn(validTasks);
		when(taskMapper.mapToDTO(Mockito.any(Tasks.class))).thenReturn(validTaskDTO);
		
		assertThat(validTaskDTOs).isEqualTo(taskService.readByID(1));
		
		verify(taskRepo, times(1)).findForToDoList(1);
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Tasks.class));
	}
	
	@Test
	public void createTaskTest() { 
		
		when(taskRepo.save(Mockito.any(Tasks.class))).thenReturn(validTask);
		when(taskMapper.mapToDTO(Mockito.any(Tasks.class))).thenReturn(validTaskDTO);
		
		assertThat(validTaskDTO).isEqualTo(taskService.createTask(validTask));
		
		verify(taskRepo, times(1)).save(Mockito.any(Tasks.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Tasks.class));
	}
	
	@Test
	public void updateTaskTest() { 
		Tasks newTask = new Tasks(1, "NewOnTheBlock", "End the Suffering");
		TaskDTO newTaskDTO = new TaskDTO(1, "NewOnTheBlock", "End the Suffering");
		
		when(taskRepo.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTask));
		when(taskRepo.save(Mockito.any(Tasks.class))).thenReturn(newTask);
		when(taskMapper.mapToDTO(Mockito.any(Tasks.class))).thenReturn(newTaskDTO);
		
		TaskDTO testingDTO = taskService.updateTask(validTask.getId(), newTask);
		
		assertThat(newTaskDTO).isEqualTo(testingDTO);
		
		verify(taskRepo, times(1)).findById(Mockito.any(Integer.class));
		verify(taskRepo, times(1)).save(Mockito.any(Tasks.class));
		verify(taskMapper, times(1)).mapToDTO(Mockito.any(Tasks.class));
	}
	
	@Test
	public void deleteTaskTest() {
		
		when(taskRepo.existsById(Mockito.any(Integer.class)))
			.thenReturn(true, false);
		
		assertThat(true).isEqualTo(taskService.deleteTask(validTask.getId()));
		
		verify(taskRepo, times(2)).existsById(Mockito.any(Integer.class));
	}
	

}
