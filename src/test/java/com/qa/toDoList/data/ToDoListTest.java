package com.qa.toDoList.data;

import org.junit.jupiter.api.Test;

import com.qa.toDoList.data.models.ToDoList;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ToDoListTest {
	
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(ToDoList.class)
		.withPrefabValues(ToDoList.class, new ToDoList(1, "Exercise"), 
				new ToDoList(2, "Running")).verify();
	}
	
	@Test
	public void ConstructorTest() {
		ToDoList newList = new ToDoList();
		newList.toString();
	}

}
