package com.qa.toDoList.data;

import org.junit.jupiter.api.Test;

import com.qa.toDoList.data.models.Customer;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	
	Customer aliveCustomer = new Customer(1, "Fred", "FredTest", "Fred@test.com");
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	

}
