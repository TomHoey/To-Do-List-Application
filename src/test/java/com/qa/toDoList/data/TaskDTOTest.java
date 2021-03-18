package com.qa.toDoList.data;

import org.junit.jupiter.api.Test;

import com.qa.toDoList.dto.TaskDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskDTOTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	
	@Test
	public void testingConstructors() {
		TaskDTO task = new TaskDTO(1, "Exercise", "Running");
		task.toString();
		
	}

}
