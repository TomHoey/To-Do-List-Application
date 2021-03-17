package com.qa.toDoList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.respository.TaskRepository;
import com.qa.toDoList.dto.TaskDTO;
import com.qa.toDoList.mappers.TaskMapper;

@Service
public class TaskService {
	
	private TaskRepository taskRepository;
	
	private TaskMapper taskMapper;
	
	@Autowired
	public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
		this.taskRepository = taskRepository;
		this.taskMapper = taskMapper;
	}
	
	public List<TaskDTO> readAllTasks() {
		List<Tasks> tasks = taskRepository.findAll();
		List<TaskDTO> taskDTO = new ArrayList<TaskDTO>();
		
		tasks.forEach(task -> taskDTO.add(taskMapper.mapToDTO(task)));
		
		return taskDTO;
	}
	
	public TaskDTO readByID (Integer ID) {
		Optional<Tasks> task = taskRepository.findById(ID);
		
		if (task.isPresent()) {
			return taskMapper.mapToDTO(task.get());
		} else {
			throw new EntityNotFoundException();
		}
	}
	
	public TaskDTO createTask (Tasks task) {
		Tasks newTask = taskRepository.save(task);
		
		return taskMapper.mapToDTO(newTask);
	}

}
