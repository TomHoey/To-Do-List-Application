package com.qa.toDoList.data.models;

import java.util.ArrayList;
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
	@Column(name = "to_DoID")
	private int toDoID;
	
	@Column(name = "name", unique = true)
	private String listName;
	
	@OneToMany(mappedBy = "toDoList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Tasks> tasks;
	
	public ToDoList(int temporary) {
		toDoID = temporary;
		this.tasks = new ArrayList<Tasks>();
	}
	
	public ToDoList() {
		this.tasks = new ArrayList<Tasks>();
	}
	
	public ToDoList(int toDoID, String listName) {
		super();
		this.toDoID = toDoID;
		this.listName = listName;
		this.tasks = new ArrayList<Tasks>();
	}
	
	public ToDoList(String listName, List<Tasks> tasks) {
		super();
		this.listName = listName;
		this.tasks = tasks;
	}
	
	public ToDoList(int toDoID, String listName, List<Tasks> tasks) {
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
	
	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		result = prime * result + ((listName == null) ? 0 : listName.hashCode());
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
		if (getTasks() == null) {
			if (other.getTasks() != null)
				return false;
		} else if (!getTasks().equals(other.getTasks()))
			return false;
		if (getListName() == null) {
			if (other.getListName() != null)
				return false;
		} else if (!getListName().equals(other.getListName()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return "List [ListID:" + toDoID + ", name:" + listName + "]";
	}

	

}
