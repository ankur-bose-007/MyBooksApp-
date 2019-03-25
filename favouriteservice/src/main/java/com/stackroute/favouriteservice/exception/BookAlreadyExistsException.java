package com.stackroute.favouriteservice.exception;

@SuppressWarnings("serial")
public class BookAlreadyExistsException extends Exception {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "BookAlreadyExistsException [message=" + message + "]";
	}

}
