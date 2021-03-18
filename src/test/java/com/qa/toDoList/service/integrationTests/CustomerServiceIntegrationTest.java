package com.qa.toDoList.service.integrationTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.data.respository.CustomerRepository;
import com.qa.toDoList.dto.CustomerDTO;
import com.qa.toDoList.mappers.CustomerMapper;
import com.qa.toDoList.service.CustomerService;

@SpringBootTest
public class CustomerServiceIntegrationTest {
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private CustomerMapper custMapper;
	
	private List<Customer> aliveCusts;
	private List<CustomerDTO> aliveCustDTOs;
	
	private Customer aliveCust;
	private CustomerDTO aliveCustDTO;
	
	@BeforeEach
	public void init() {
		
		aliveCust = new Customer("Inter", "InterPass", "Inter@test.com");
		
		aliveCusts = new ArrayList<Customer>();
		aliveCustDTOs = new ArrayList<CustomerDTO>();
		
		custRepo.deleteAll();
		
		aliveCust = custRepo.save(aliveCust);
		
		aliveCustDTO = custMapper.mapToDTO(aliveCust);
		
		aliveCusts.add(aliveCust);
		aliveCustDTOs.add(aliveCustDTO);
	}
	
	@Test
	public void readAllCustomerIntTest() {
		List<CustomerDTO> CustInDB = custService.readAllCustomer();
		
		assertThat(aliveCustDTOs).isEqualTo(CustInDB);
	}
	
	@Test
	public void createCustomerIntTest() {
		Customer newCust = new Customer(aliveCust.getCid(), "Inter", "InterPass", "Inter@test.co.uk");
		CustomerDTO newCustDTO = custMapper.mapToDTO(newCust);
		assertThat(newCustDTO).isEqualTo(custService.createCustomer(newCust));
	}
	
	@Test
	public void updateCustomerIntTest() {
		Customer newCust = new Customer(aliveCust.getCid(), "Inter", "InterPass", "Inter@test.com");
		CustomerDTO newCustDTO = custMapper.mapToDTO(newCust);
		CustomerDTO toCustDTO = custService.updateCustomer(aliveCust.getCid(),newCust);
		assertThat(newCustDTO).isEqualTo(toCustDTO);
	}
	
	@Test
	public void deleteCustomerIntTest() { 
		assertThat(true).isEqualTo(custService.deleteCustomer(aliveCust.getCid()));
	}

}
