package com.qa.toDoList.data.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.toDoList.data.models.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {

}
