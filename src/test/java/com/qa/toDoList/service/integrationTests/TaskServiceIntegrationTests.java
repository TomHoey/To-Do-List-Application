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
import com.qa.toDoList.data.respository.TaskRepository;
import com.qa.toDoList.data.respository.ToDoListRepository;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.mappers.TaskMapper;
import com.qa.toDoList.service.TaskService;

@SpringBootTest
class TaskServiceIntegrationTests {
	
	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private ToDoListRepository tdlRepo;

	private List<Tasks> validTasks;
	private List<TaskDTO> validTaskDTOs;

	private Tasks validTask;
	private TaskDTO validTaskDTO;
	private static ToDoList validToDoList;
	
	@BeforeEach
	 public void init() {
		validToDoList = new ToDoList(1);
		tdlRepo.deleteAll();
		validToDoList = tdlRepo.save(validToDoList);
		validTask = new Tasks("Running", "Sprinting");
		validTask.setToDoList(validToDoList);
		validTaskDTO = new TaskDTO("Running", "Sprinting");
		taskRepo.deleteAll();
		validTask = taskRepo.save(validTask);
		validTaskDTO = taskMapper.mapToDTO(validTask);
		validTasks = new ArrayList<Tasks>();
		validTaskDTOs = new ArrayList<TaskDTO>();
		validTasks.add(validTask);
		validTaskDTOs.add(validTaskDTO);
		
	}
	
	@Test
	public void readByIDTest() {
	List<TaskDTO> tasksInDB = taskService.readByID(validToDoList.getToDoID());
	assertThat(validTaskDTOs).isEqualTo(tasksInDB); 
	
	}
	
	@Test
	public void createTaskTest() {
		Tasks anotherTask = new Tasks(4, "Sleeping", "very Sleepy");
		TaskDTO anotherTaskDTO = taskMapper.mapToDTO(anotherTask);
		
		assertThat(anotherTaskDTO).isEqualTo(taskService.createTask(anotherTask));
	}
	
	@Test
	public void updateTaskTest() { 
		Tasks updateTask = new Tasks(validTask.getId(), "Crying", "Why Testing");
		updateTask.setToDoList(validTask.getToDoList());
		
		TaskDTO updateTaskDTO = taskMapper.mapToDTO(updateTask);
		TaskDTO taskDTO = taskService.updateTask(validTask.getId(), updateTask);
		
		assertThat(updateTaskDTO).isEqualTo(taskDTO);
		
	}
	
	@Test
	public void deleteTaskTest() {
		assertThat (taskService.deleteTask(validTask.getId())).isTrue();
	}
	
}

