package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.exception.BookAlreadyExistsException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.service.BookService;


@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/book")
public class BookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<?> saveBook(@RequestBody Book book) {
		LOGGER.info("Started saving book");
		LOGGER.debug("book: {}", book);
		ResponseEntity<?> responseEntity;
		try {
			boolean result = bookService.saveBook(book);
			responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.CREATED);
		} catch (BookAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		LOGGER.debug("responseEntity: {}", responseEntity);
		return responseEntity;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getAllBooksById(@PathVariable("id") final String id) {
		LOGGER.info("Started getting all books");
		LOGGER.debug("userId: {}", id);
		ResponseEntity<?> responseEntity;
		List<Book> books = null;
		try {
			books = bookService.getAllBooks(id);
			responseEntity = new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} catch (BookNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		LOGGER.debug("responseEntity: {}", responseEntity);
		return responseEntity;
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable("id") final int id) {
		LOGGER.info("Started deleting favourite book");
		LOGGER.debug("bookId: {}", id);
		ResponseEntity<?> responseEntity;
		try {
			bookService.deleteBook(id);
			responseEntity = new ResponseEntity<String>("book deleted", HttpStatus.OK);
		} catch (BookNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		LOGGER.debug("responseEntity: {}", responseEntity);
		return responseEntity;
	}
}
