package com.qa.toDoList.data.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.qa.toDoList.data.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	
		public Customer findByName(String name);
		
		
		@Query(value = "SELECT * FROM duck", nativeQuery = true)
		public List<Customer> getAllDucksSQL();
		
		
		@Query("SELECT d FROM Duck d")
		public List<Customer> getAllDucksJPQL();
		
		
		@Query("SELECT d FROM Duck d WHERE d.name = ?1")
		public Customer getDuckByNameJPQL(String username);

}
