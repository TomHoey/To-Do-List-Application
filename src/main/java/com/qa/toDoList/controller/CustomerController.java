package com.qa.toDoList.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.toDoList.data.models.Customer;
import com.qa.toDoList.dto.CustomerDTO;
import com.qa.toDoList.service.CustomerService;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "1442");
		
		List<CustomerDTO> data = customerService.readAllCustomer();
		
		return new ResponseEntity<List<CustomerDTO>>(data, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/{cid}")
	public ResponseEntity<CustomerDTO> getCustomerbyID(@PathVariable("cid") int cid) {
		
		CustomerDTO cust = customerService.readByCID(cid);
		
		return new ResponseEntity<CustomerDTO>(cust, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody Customer customer) {
		
		CustomerDTO newCust = customerService.createCustomer(customer);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Location", String.valueOf(newCust.getCid()));
		
		return new ResponseEntity<CustomerDTO>(newCust, header, HttpStatus.CREATED);
	}
	
	@PutMapping("/{cid}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("cid") int cid,
													  @RequestBody Customer customer) {
		
		CustomerDTO updatedCust = customerService.updateCustomer(cid, customer);
		
		return new ResponseEntity<CustomerDTO>(updatedCust, HttpStatus.OK);
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<Boolean> deleteCustomer (@PathVariable("cid") int cid) {
		return new ResponseEntity<Boolean>(customerService.deleteCustomer(cid), HttpStatus.OK);
	}

}
