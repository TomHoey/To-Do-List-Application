package com.qa.toDoList.data.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.toDoList.data.models.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
	
	@Query(value = "SELECT * FROM TASKS t WHERE t.fk_toDoID = ?1", nativeQuery = true)
	public List<Tasks> findForToDoList(int id);

}
