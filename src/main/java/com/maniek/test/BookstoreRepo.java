package com.maniek.test;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookstoreRepo extends CrudRepository<Bookstore, Long> {
	
	@Query("SELECT u FROM Bookstore u")
	List<Bookstore> getBookStores();

}
