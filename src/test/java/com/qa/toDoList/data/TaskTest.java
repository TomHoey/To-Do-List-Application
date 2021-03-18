package com.qa.toDoList.data;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.toDoList.data.models.Tasks;
import com.qa.toDoList.data.models.ToDoList;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskTest {
	ToDoList newList = new ToDoList(1, "Exercise", new ArrayList<Tasks>());
	
	@Test
	public void testEquals() { 
		EqualsVerifier.simple().forClass(Tasks.class).withPrefabValues(ToDoList.class, newList, new ToDoList()).verify();
	}
	
	@Test
	public void testingConstructors() { 
		Tasks task = new Tasks(1, "Exercise", "Running");
		task.toString();
	}

}
