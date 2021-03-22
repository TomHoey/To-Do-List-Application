package com.qa.toDoList.data;

import org.junit.jupiter.api.Test;

import com.qa.toDoList.dto.CustomerDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerDTOUnitTest {
	
	@Test
	void testEquals() {
		EqualsVerifier.simple().forClass(CustomerDTO.class).verify();
	}
	
	@Test
	void ConstructorTest() {
		CustomerDTO newCustomerDTO = new CustomerDTO();
		newCustomerDTO.toString();
	}
	

}
