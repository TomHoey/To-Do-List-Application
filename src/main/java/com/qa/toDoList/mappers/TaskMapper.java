package com.qa.toDoList.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.dto.TaskDTO;

	
@Component
public class TaskMapper {
		
		private ModelMapper modelMapper;
		
		@Autowired
		public TaskMapper(ModelMapper modelMapper) {
			this.modelMapper = modelMapper;
		}
		
		public TaskDTO mapToDTO(Tasks task) {
			return this.modelMapper.map(task, TaskDTO.class);
		}
		
		public Tasks mapToTasks(TaskDTO taskDTO) {
			return this.modelMapper.map(taskDTO, Tasks.class);
		}

}
