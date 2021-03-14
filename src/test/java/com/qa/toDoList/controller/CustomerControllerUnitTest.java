package com.qa.toDoList.controller;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.dto.CustomerDTO;
import com.qa.toDoList.service.CustomerService;

@SpringBootTest
public class CustomerControllerUnitTest {
	
	@Autowired
	private CustomerController customerController;
	
	@MockBean
	private CustomerService customerService;
	
	private List<Customer> aliveCustomers;
	private List<CustomerDTO> aliveCustomerDTOs;
	
	private Customer aliveCustomer;
	private CustomerDTO aliveCustomerDTO;
	
	@BeforeEach
	public void init() {
		aliveCustomer = new Customer(1, "Jeff", "JeffPass", "Jeff@test.com");
		aliveCustomerDTO = new CustomerDTO (1, "Jeff");
		
		aliveCustomers = new ArrayList<Customer>();
		aliveCustomerDTOs = new ArrayList <CustomerDTO>();
		
		aliveCustomers.add(aliveCustomer);
		aliveCustomerDTOs.add(aliveCustomerDTO);
		
	}
	
	@Test
	public void readAllCustomerTest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("location", "1442");
		
		when(customerService.readAllCustomer()).thenReturn(aliveCustomerDTOs);
		
		ResponseEntity<List<CustomerDTO>> response =
				new ResponseEntity<List<CustomerDTO>>(aliveCustomerDTOs, httpHeaders, HttpStatus.OK);
		
		assertThat(response).isEqualTo(customerController.readAllCustomers());
		
		verify(customerService, times(1)).readAllCustomer();
	}


}
