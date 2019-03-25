package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.exception.BookAlreadyExistsException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.model.Book;

public interface BookService {
	
	boolean saveBook(Book book) throws BookAlreadyExistsException;
	
	List<Book> getAllBooks(String id) throws BookNotFoundException;
	
	public boolean deleteBook(int id) throws BookNotFoundException;
}
