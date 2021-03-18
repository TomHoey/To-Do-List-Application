package com.qa.toDoList.data;

import org.junit.jupiter.api.Test;

import com.qa.toDoList.dto.CustomerDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerDTOUnitTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(CustomerDTO.class).verify();
	}
	
	@Test
	public void ConstructorTest() {
		CustomerDTO newCustomerDTO = new CustomerDTO();
		newCustomerDTO.toString();
	}
	

}
