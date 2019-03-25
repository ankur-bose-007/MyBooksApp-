package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.exception.BookAlreadyExistsException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	@Autowired
	private BookRepository bookRepository;


	public boolean saveBook(Book book) throws BookAlreadyExistsException {
		LOGGER.info("Inside service saveBook");
		Book bookEntity = bookRepository.findByKey(book.getKey());
		if (bookEntity != null) {
			throw new BookAlreadyExistsException("Could not save. Book already exists");
		}
		bookRepository.save(book);
		return true;
	}

	public List<Book> getAllBooks(final String id) throws BookNotFoundException {
		LOGGER.info("Inside service getAllBooks");
		List<Book> books = bookRepository.findFavouritesByUserId(id);
		if (books.isEmpty()) {
			throw new BookNotFoundException("Favourites not found!");
		}
		return books;
	}
	
	public boolean deleteBook(int id) throws BookNotFoundException {
		LOGGER.info("Inside service deleteBook");
		Optional<Book> bookEntity=bookRepository.findById(id);
		if (!bookEntity.isPresent()) {
			throw new BookNotFoundException("Book doesn't exist");
		}
		bookRepository.delete(bookEntity.get());
		LOGGER.info("deleted book");
		LOGGER.debug("bookId: {}", id);
		return true;
	}
}
