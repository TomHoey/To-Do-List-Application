package com.qa.toDoList.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.dto.CustomerDTO;

@Component
public class CustomerMapper {
	
	private ModelMapper modelMapper;
	
	@Autowired
	public CustomerMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public CustomerDTO mapToDTO(Customer customer) {
		return this.modelMapper.map(customer, CustomerDTO.class);
	}
	
	public Customer mapToCustomer(CustomerDTO customerDTO) {
		return this.modelMapper.map(customerDTO, Customer.class);
	}
	
	

}
