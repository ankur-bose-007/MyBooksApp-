package com.stackroute.favouriteservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book")

public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;

	@Column(name = "book_title")
	private String title;

	@Column(name = "book_author")
	private String author;

	@Column(name = "book_isbn")
	private String isbn;

	@Column(name = "book_url")
	private String url;

	@NotNull(message = "key cannot be empty")
	@Column(name = "book_key")
	private String key;

	@NotNull(message = "userId cannot be empty")
	@Column(name = "book_userid")
	private String userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", url=" + url
				+ ", key=" + key + ", userId=" + userId + "]";
	}

	public Book() {
		super();
	}

	public Book(int id, String title, String author, String isbn, String url,
			@NotNull(message = "key cannot be empty") String key,
			@NotNull(message = "userId cannot be empty") String userId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.url = url;
		this.key = key;
		this.userId = userId;
	}
}
