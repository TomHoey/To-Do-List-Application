package com.qa.toDoList.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.data.respository.CustomerRepository;
import com.qa.toDoList.dto.CustomerDTO;
import com.qa.toDoList.mappers.CustomerMapper;

@Service
public class CustomerService {
	
	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;
	
	@Autowired
	public CustomerService (CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}
	
	public CustomerDTO createCustomer(Customer customer) {
		
		Customer newCust = customerRepository.save(customer);
		
		return customerMapper.mapToDTO(newCust);
	}

}
