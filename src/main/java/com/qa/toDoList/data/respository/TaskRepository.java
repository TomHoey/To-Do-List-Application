package com.qa.toDoList.data.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.toDoList.data.models.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {

}
