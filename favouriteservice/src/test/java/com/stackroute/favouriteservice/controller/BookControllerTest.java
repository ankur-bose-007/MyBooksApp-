package com.stackroute.favouriteservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.core.StringContains.containsString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.service.BookService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookControllerTest.class);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@MockBean
	private BookService bookService;

	Book book;
	private MockMvc mockMvc;
	ObjectMapper objectMapper;

	@Before
	public void setup() throws Exception {
		objectMapper=new ObjectMapper();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		book=new Book(1,"Harry Potter","J.K. Rowling","9781408855652","www.amazon.com","12345","ankur007");
	}

	@Test
	public void saveBookSuccess() throws Exception {
		LOGGER.info("Started saveBookSuccess test case");
		when(bookService.saveBook(book)).thenReturn(true);
		mockMvc.perform(post("/api/v1/book").content(objectMapper.writeValueAsString(book)).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isCreated());
		LOGGER.info("Ended saveBookSuccess test case");
	}
	
	@Test
	public void gettingFavouriteListWhenUserIdNotFound() throws Exception {
		LOGGER.info("Started gettingFavouriteList test case");
		when(bookService.getAllBooks("bose.ankur")).thenThrow(BookNotFoundException.class);
		MvcResult result = mockMvc.perform(get("/api/v1/book/bose.ankur")).andExpect(status().isNotFound()).andReturn();
		LOGGER.debug("result: {}", result);
		LOGGER.info("Ended gettingFavouriteList test case");
	}
	
	@Test
	public void deleteBookWhenIdFound() throws Exception {
		LOGGER.info("Started deleteBookWhenIdFound test case");
		when(bookService.deleteBook(1)).thenReturn(true);
		mockMvc.perform(delete("/api/v1/book/1")).andExpect(status().isOk()).andExpect(content().string(containsString("book deleted")));
		LOGGER.info("Ended deleteBookWhenIdFound test case");
	}
}
