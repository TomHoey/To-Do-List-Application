package com.qa.toDoList.dto;

public class TaskDTO {
	
	private int id;
	
	private String taskName;
	
	private String description;
	
	
	public TaskDTO() {
		super();
	}
	
	public TaskDTO(int id, String taskName, String description) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.description = description;
		
	}
	
	public TaskDTO(String taskName, String description) {
		super();
		this.taskName = taskName;
		this.description = description;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
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
		TaskDTO other = (TaskDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}

	@Override
	public String toString () { 
		return "TaskDTO [ID: " + id + ", Name: " + taskName + ", Description: " + description + "]";
	}

}
