package com.qa.toDoList.exceptions;

import javax.persistence.EntityNotFoundException;

public class CustomerNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7654636836827359633L;
	
	public CustomerNotFoundException() {
		super();
	}
	
	public CustomerNotFoundException(String message) {
		super(message);
	}

}
