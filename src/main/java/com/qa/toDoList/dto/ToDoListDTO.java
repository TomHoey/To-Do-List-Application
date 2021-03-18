package com.qa.toDoList.dto;

import java.util.ArrayList;
import java.util.List;

import com.qa.toDoList.data.models.Tasks;

public class ToDoListDTO {
	
	private int toDoID;
	
	private String listName;
	
	private List<TaskDTO> tasks;
	
	@Override
	public String toString() { 
		return "ToDoListDTO [ID:" + toDoID + "Name: " + listName + ", Tasks: " + tasks + "]";
	}
	
	public ToDoListDTO() {
	
	}
	
	public ToDoListDTO(int toDoID, String toDoListName) {
		super();
		this.toDoID = toDoID;
		this.listName = listName;
		this.tasks = new ArrayList<TaskDTO>();
	}
	
	public ToDoListDTO(int toDoID, String listName, List<TaskDTO> tasks) {
		super();
		this.toDoID = toDoID;
		this.listName = listName;
		this.tasks = tasks;
	}

	public int getToDoID() {
		return toDoID;
	}

	public void setToDoID(int toDoID) {
		this.toDoID = toDoID;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public List<TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listName == null) ? 0 : listName.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		result = prime * result + toDoID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoListDTO other = (ToDoListDTO) obj;
		if (listName == null) {
			if (other.listName != null)
				return false;
		} else if (!listName.equals(other.listName))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		if (toDoID != other.toDoID)
			return false;
		return true;
	}

	
}
