package com.qa.toDoList.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TaskID")
	private int id;
	
	@Column(name = "name", unique = true)
	@NotNull
	private String name;
	
	private String description;
	
	@ManyToOne(targetEntity = ToDoList.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_taskID")
	private ToDoList toDoList;
	
	public Tasks() {
		super();
	}
	
	public Tasks(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Tasks(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ToDoList getToDoList() {
		return toDoList;
	}

	public void setToDoList(ToDoList toDoList) {
		this.toDoList = toDoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((toDoList == null) ? 0 : toDoList.hashCode());
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
		Tasks other = (Tasks) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (toDoList == null) {
			if (other.toDoList != null)
				return false;
		} else if (!toDoList.equals(other.toDoList))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Task [ID: " + id + ", Name: " + name + ", Description: " + description + "]";
	}

}
