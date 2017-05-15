package com.package1;

public class HeapException extends Exception {

	private static final long serialVersionUID = 1L;
	//constant block
	String message;
	
	public HeapException(String message)
	{
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return message;
	}
	//constant

}

