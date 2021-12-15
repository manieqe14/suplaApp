package com.maniek.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bookstore {
	
	@Id
	@GeneratedValue
	private long bookstore_id;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "bookstore_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "bookstore_id"))
	private List<Book> books;

	public long getBookstore_id() {
		return bookstore_id;
	}

	public void setBookstore_id(long bookstore_id) {
		this.bookstore_id = bookstore_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		StringBuffer booksBuffer = new StringBuffer();
		books.forEach(v -> booksBuffer.append(v.getTitle() + ", "));
		return "[bookstore_id=" + bookstore_id + ", name=" + name + ", BOOKS: " + booksBuffer.toString() + "]";
	}
	
	

}
