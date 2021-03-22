package com.qa.toDoList.service.unitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.h2.util.Task;
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
import com.qa.toDoList.service.CustomerService;

@SpringBootTest
public class CustomerServiceUnitTest {
	
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
	void init() {
		aliveCust = new Customer(1, "Jeff", "JeffPass", "Jeff@Test.com");
		aliveCustDTO = new CustomerDTO(1, "Jeff");
		
		custs = new ArrayList<Customer>();
		custDTOs = new ArrayList<CustomerDTO>();
		
		custs.add(aliveCust);
		custDTOs.add(aliveCustDTO);
	}
	
	@Test
	void readAllCustomerTest() {
		
		when(customerRepository.findAll()).thenReturn(custs);
		when(customerMapper.mapToDTO(Mockito.any(Customer.class))).thenReturn(aliveCustDTO);
		
		assertThat(custDTOs).isEqualTo(customerService.readAllCustomer());
		
		verify(customerRepository, times(1)).findAll();
		verify(customerMapper, times(1)).mapToDTO(Mockito.any(Customer.class));
	}
	
	@Test
	void readByCIDCustomerTest() {
		
		when(customerRepository.findById(Mockito.any(Integer.class)))
			.thenReturn(Optional.of(aliveCust));
		when(customerMapper.mapToDTO(Mockito.any(Customer.class))).thenReturn(aliveCustDTO);
		
		assertThat(aliveCustDTO).isEqualTo(customerService.readByCID(1));
		
		verify(customerRepository, times(1)).findById(Mockito.any(Integer.class));
		verify(customerMapper, times(1)).mapToDTO(Mockito.any(Customer.class));
		
	}
	
	@Test
	void createCustomerTest() {
		
		when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(aliveCust);
		when(customerMapper.mapToDTO(Mockito.any(Customer.class))).thenReturn(aliveCustDTO);
		
		assertThat(aliveCustDTO).isEqualTo(customerService.createCustomer(aliveCust));
		
		verify(customerRepository, times(1)).save(Mockito.any(Customer.class));
		verify(customerMapper, times(1)).mapToDTO(Mockito.any(Customer.class));
	}
	
	@Test
	void updateCustomerTest() {
		
		Customer updateCust = new Customer(1, "Ted", "TedPass", "Ted@Test.com");
		CustomerDTO updateCustDTO = new CustomerDTO(1, "Ted");
		
		when(customerRepository.findById(Mockito.any(Integer.class)))
			.thenReturn(Optional.of(aliveCust));
		
		when(customerRepository.save(Mockito.any(Customer.class)))
			.thenReturn(updateCust);
		
		when(customerMapper.mapToDTO(Mockito.any(Customer.class)))
			.thenReturn(updateCustDTO);
		
		CustomerDTO testingDTO = customerService.updateCustomer(aliveCust.getCid(), updateCust);
		
		assertThat(updateCustDTO).isEqualTo(testingDTO);
	}
	
	@Test
	void deleteCustomerTest() {
		
		when(customerRepository.existsById(Mockito.any(Integer.class))).thenReturn(true, false);
		
		assertThat(true).isEqualTo(customerService.deleteCustomer(aliveCust.getCid()));
		
		verify(customerRepository, times(2)).existsById(Mockito.any(Integer.class));
		
	}

}
