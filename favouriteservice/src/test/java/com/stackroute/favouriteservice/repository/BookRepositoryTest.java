package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.stackroute.favouriteservice.model.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	Book book;
	
	@Before
	public void  setUp() throws Exception {
		book = new Book(1,"Harry Potter","J.K. Rowling","9781408855652","www.amazon.com","12345","ankur007");
	}
	
	@After
	public void tearDown() {
		bookRepository.deleteAll();
	}

	@Test
	public void testSaveBook() throws Exception {
		Book bookEntity=bookRepository.save(book);
		assertThat(bookRepository.existsById(bookEntity.getId())).isEqualTo(true);
		bookRepository.delete(bookEntity);
	}

	@Test
	public void testGetAllFavourites() throws Exception {
		Book bookEntity=bookRepository.save(book);
		List<Book> books = bookRepository.findFavouritesByUserId("ankur007");
		assertEquals(false, books.isEmpty());
		bookRepository.delete(bookEntity);
	}

	@Test
	public void deleteFavourites() throws Exception {
		Book bookEntity=bookRepository.save(new Book(90,"Harry Potter","J.K. Rowling","9781408855652","www.amazon.com","12345","ankur007"));
		bookRepository.deleteById(bookEntity.getId());
		assertEquals(false, bookRepository.existsById(bookEntity.getId()));
	}
}
