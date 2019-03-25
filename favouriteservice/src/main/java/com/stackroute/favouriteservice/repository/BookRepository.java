package com.stackroute.favouriteservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stackroute.favouriteservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	Book findByKey(String key);

	@Query(value="from Book b where b.userId=?1")
	List<Book> findFavouritesByUserId(String id);

}
