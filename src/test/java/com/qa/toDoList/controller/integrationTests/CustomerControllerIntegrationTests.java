package com.qa.toDoList.controller.integrationTests;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.toDoList.controller.CustomerController;
import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.dto.CustomerDTO;
import com.qa.toDoList.service.CustomerService;

@SpringBootTest
public class CustomerControllerIntegrationTests {
	
	@Autowired
	private CustomerController custController;
	
	@MockBean
	private CustomerService custService;
	
	private List<Customer> aliveCusts;
	private List<CustomerDTO> aliveCustDTOs;
	
	private Customer aliveCust;
	private CustomerDTO aliveCustDTO;
	
	@BeforeEach
	void init() {
		
		aliveCust = new Customer("Inter", "InterPass", "Inter@test.com");
		aliveCustDTO = new CustomerDTO("Inter");
		
		aliveCusts = new ArrayList<Customer>();
		aliveCustDTOs = new ArrayList<CustomerDTO>();
		
		aliveCusts.add(aliveCust);
		aliveCustDTOs.add(aliveCustDTO);
	}
	
	@Test
	void getAllCustomersIntTest() {
		
		when(custService.readAllCustomer()).thenReturn(aliveCustDTOs);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Location", "1442");
		
		ResponseEntity<List<CustomerDTO>> response = 
				new ResponseEntity<List<CustomerDTO>>(aliveCustDTOs, header, HttpStatus.OK);
		
		assertThat(response).isEqualTo(custController.getAllCustomers());
		
		verify(custService, times(1)).readAllCustomer();
	}
	
	@Test
	void createCustomerIntTest() {
		
		when(custService.createCustomer(Mockito.any(Customer.class))).thenReturn(aliveCustDTO);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Location", String.valueOf(aliveCustDTO.getCid()));
		
		ResponseEntity<CustomerDTO> response =
				new ResponseEntity<CustomerDTO>(aliveCustDTO, header, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(custController.createCustomer(aliveCust));
		
		verify(custService, times(1)).createCustomer(Mockito.any(Customer.class));
	}
	
	@Test
	void updateCustomerIntTest() {
		when(custService.updateCustomer(Mockito.any(Integer.class),
				Mockito.any(Customer.class))).thenReturn(aliveCustDTO);
		
		ResponseEntity<CustomerDTO> response = 
				new ResponseEntity<CustomerDTO>(aliveCustDTO,HttpStatus.OK);
		
		assertThat(response).isEqualTo(custController.updateCustomer(aliveCust.getCid(),aliveCust));
		
		verify(custService, times(1)).updateCustomer(Mockito.any(Integer.class),Mockito.any(Customer.class));

	}
	
	@Test
	void deleteCustomerIntTest() {
		when(custService.deleteCustomer(Mockito.any(Integer.class))).thenReturn(true);
		
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
		
		assertThat(response).isEqualTo(custController.deleteCustomer(aliveCust.getCid()));
		
		verify(custService, times(1)).deleteCustomer(Mockito.any(Integer.class));
	}
}
