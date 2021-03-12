package com.qa.toDoList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.data.respository.CustomerRepository;
import com.qa.toDoList.dto.CustomerDTO;
import com.qa.toDoList.exceptions.CustomerNotFoundException;
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
	
	public CustomerDTO readByCID(Integer cid) {
		Optional<Customer> cust = customerRepository.findById(cid);
		
		if (cust.isPresent()) {
			return customerMapper.mapToDTO(cust.get());
		} else {
			throw new CustomerNotFoundException ("No user matches that ID");
		}
	}
	
	
	public List<CustomerDTO> readAllCustomer() {
		List<Customer> cust = customerRepository.findAll();
		List<CustomerDTO> custDTOs = new ArrayList<CustomerDTO>();
		
		cust.forEach(customer -> custDTOs.add(customerMapper.mapToDTO(customer)));

		return custDTOs;
		
	}
	
	
	public CustomerDTO createCustomer(Customer customer) {
		
		Customer newCust = customerRepository.save(customer);
		
		return customerMapper.mapToDTO(newCust);
	}
	
	public CustomerDTO updateCustomer (Integer cid, Customer customer) throws EntityNotFoundException {
		Optional<Customer> customerInDbOpt = customerRepository.findById(cid);
		Customer customerInDb;
		
		if (customerInDbOpt.isPresent()) {
			customerInDb = customerInDbOpt.get();
		} else {
			throw new CustomerNotFoundException("No customer by that ID exists.");
		}
		
		customerInDb.setUsername(customer.getUsername());
		customerInDb.setPassword(customer.getPassword());
		customerInDb.setEmail(customer.getEmail());
		
		Customer updatedCust = customerRepository.save(customerInDb);
		
		return customerMapper.mapToDTO(updatedCust);
	}

}
