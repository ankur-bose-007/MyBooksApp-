package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.exception.BookAlreadyExistsException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.repository.BookRepository;

public class BookServiceTest {

	@Mock
	private BookRepository bookRepo;

	private Book book;
	private Optional<Book> optionalBook;
	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		book = new Book(1,"Harry Potter","J.K. Rowling","9781408855652","www.amazon.com","12345","ankur007");
		optionalBook=Optional.of(book);
	}

	@Test
	public void testSaveBookSuccess() throws BookAlreadyExistsException {
		when(bookRepo.findByKey(book.getKey())).thenReturn(null);
		when(bookRepo.save(book)).thenReturn(book);
		boolean status = bookServiceImpl.saveBook(book);
		assertEquals(true, status);
		verify(bookRepo, times(1)).save(book);
		verify(bookRepo, times(1)).findByKey(book.getKey());
	}

	@Test(expected = BookAlreadyExistsException.class)
	public void testSaveBookFailure() throws BookAlreadyExistsException {
		when(bookRepo.findByKey(book.getKey())).thenReturn(book);
		bookServiceImpl.saveBook(book);
		verify(bookRepo, times(1)).findByKey(book.getKey());
	}

	@Test
	public void testForGettingUserFavourites() throws BookNotFoundException {
		List<Book> favoriteBooks = new ArrayList<Book>();
		book.setId(2);
		favoriteBooks.add(book);
		book.setId(3);
		favoriteBooks.add(book);
		when(bookRepo.findFavouritesByUserId(book.getUserId())).thenReturn(favoriteBooks);
		List<Book> bookList = bookServiceImpl.getAllBooks(book.getUserId());
		assertEquals(true, bookList.get(0).getId() == 3);
		verify(bookRepo, times(1)).findFavouritesByUserId(book.getUserId());
	}

	@Test(expected = BookNotFoundException.class)
	public void testForGettingUserFavouritesWhenEmpty() throws BookNotFoundException {
		List<Book> favoriteBooks = new ArrayList<Book>();
		when(bookRepo.findFavouritesByUserId(book.getUserId())).thenReturn(favoriteBooks);
		bookServiceImpl.getAllBooks(book.getUserId());
		verify(bookRepo, times(1)).findFavouritesByUserId(book.getUserId());
	}
	
	@Test
	public void testForDeletingUserFavourites() throws BookNotFoundException {
		when(bookRepo.findById(book.getId())).thenReturn(optionalBook);
		boolean status=bookServiceImpl.deleteBook(1);
		assertEquals(true, status);
		verify(bookRepo, times(1)).findById(book.getId());
	}

	@Test(expected = BookNotFoundException.class)
	public void testForDeletingUserFavouritesWhenEmpty() throws BookNotFoundException {
		when(bookRepo.findById(book.getId())).thenReturn(Optional.empty());
		bookServiceImpl.deleteBook(1);
		verify(bookRepo, times(1)).findById(book.getId());
	}
}
