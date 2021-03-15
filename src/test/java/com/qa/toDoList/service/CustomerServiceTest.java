package com.qa.toDoList.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.data.respository.CustomerRepository;
import com.qa.toDoList.dto.CustomerDTO;
import com.qa.toDoList.mappers.CustomerMapper;

@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private CustomerMapper customerMapper;
	
	private List<Customer> custs;
	private List<CustomerDTO> custDTOs;
	
	private Customer aliveCust;
	private CustomerDTO aliveCustDTO;
	
	@BeforeEach
	public void init() {
		aliveCust = new Customer(1, "Jeff", "JeffPass", "Jeff@Test.com");
		aliveCustDTO = new CustomerDTO(1, "Jeff");
		
		custs = new ArrayList<Customer>();
		custDTOs = new ArrayList<CustomerDTO>();
		
		custs.add(aliveCust);
		custDTOs.add(aliveCustDTO);
	}
	
	@Test
	public void createCustomerTest() {
		
		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(aliveCust);
		when(customerMapper.mapToDTO(Mockito.any(Customer.class))).thenReturn(aliveCustDTO);
		
		assertThat(aliveCustDTO).isEqualTo(customerService.createCustomer(aliveCust));
		
		verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
		verify(customerMapper, times(1)).mapToDTO(Mockito.any(Customer.class));
	}

}
