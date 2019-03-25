package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class BookNotFoundException extends Exception {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "BookNotFoundException [message=" + message + "]";
	}

}
