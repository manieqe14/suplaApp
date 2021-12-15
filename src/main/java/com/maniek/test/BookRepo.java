package com.maniek.test;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long> {
	
	@Query("FROM Book WHERE title=?1")
	List<Book> searchByTitle(String title);
	

}
