package com.qa.toDoList.controller;

import java.util.List;

public interface CRUDController<T> {
	
	List<T> readAll();
	
	T readByID();
	
	T create();
	
	T update();
	
	int delete();

}
