package com.qa.toDoList.data;

import org.junit.jupiter.api.Test;

import com.qa.toDoList.dto.TaskDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskDTOUnitTest {
	
	@Test
	void testEquals() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	
	@Test
	void testingConstructors() {
		TaskDTO task = new TaskDTO(1, "Exercise", "Running");
		task.toString();
		
	}

}
