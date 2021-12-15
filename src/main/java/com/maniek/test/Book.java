package com.maniek.test;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private long book_id;
	
	private String title;
	
	@ManyToMany(mappedBy="books")
	@JsonIgnore
	private List<Bookstore> bookstores;

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Bookstore> getBookstores() {
		return bookstores;
	}

	public void setBookstores(List<Bookstore> bookstores) {
		this.bookstores = bookstores;
	}

	
	  @Override public String toString() { return "[book_id=" + book_id +
	  ", title=" + title + ", bookstores=" + bookstores + "]"; }
	 
	
	

}
