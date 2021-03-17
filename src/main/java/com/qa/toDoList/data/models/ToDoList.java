package com.qa.toDoList.data.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "ToDoList")
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "toDoID")
	private int toDoID;
	
	@Column(name = "name", unique = true)
	private String toDoListName;
	
	@OneToMany(mappedBy = "ToDoList", fetch = FetchType.LAZY, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Tasks> tasks;
	
	public ToDoList() {
		super();
	}
	
	public ToDoList(String toDoListName) {
		super();
		this.toDoListName = toDoListName;
	}
	
	public ToDoList(int toDoID, String toDoListName) {
		super();
		this.toDoID = toDoID;
		this.toDoListName = toDoListName;
	}

	public int getToDoID() {
		return toDoID;
	}

	public void setToDoID(int toDoID) {
		this.toDoID = toDoID;
	}

	public String getToDoListName() {
		return toDoListName;
	}
	
	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public void setToDoListName(String toDoListName) {
		this.toDoListName = toDoListName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + toDoID;
		result = prime * result + ((toDoListName == null) ? 0 : toDoListName.hashCode());
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
		ToDoList other = (ToDoList) obj;
		if (toDoID != other.toDoID)
			return false;
		if (toDoListName == null) {
			if (other.toDoListName != null)
				return false;
		} else if (!toDoListName.equals(other.toDoListName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		return "List [ListID:" + toDoID + ", name:" + toDoListName + "]";
	}

	

}
