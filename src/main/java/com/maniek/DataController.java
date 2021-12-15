package com.maniek;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.maniek.data.SuplaScene;
import com.maniek.data.SuplaSceneRepo;
import com.maniek.test.Book;
import com.maniek.test.BookRepo;
import com.maniek.test.Bookstore;
import com.maniek.test.BookstoreRepo;

@Controller
public class DataController {
	
	@Autowired
	SuplaSceneRepo suplaSceneRepo;
	
	@Autowired
	BookRepo bookRepo;
	
	@Autowired
	BookstoreRepo bookStoreRepo;
	
	@RequestMapping("/")
	public ModelAndView home(){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("/scenes")
	@ResponseBody
	public List<SuplaScene> scenes() {
		
		return suplaSceneRepo.findAll();
	}
	
	@PutMapping("/bookstores/{store_id}/addBooks")
	@ResponseBody
	public String addBookToBookStore(@PathVariable("store_id") long store_id, @RequestBody Book book_id) {
		Book book = bookRepo.findById(book_id.getBook_id()).orElse(new Book());
		Bookstore bookStore = bookStoreRepo.findById(store_id).orElse(new Bookstore());
		
		List<Book> books = bookStore.getBooks();
		books.add(book);
		bookStore.setBooks(books);
		bookStoreRepo.save(bookStore);
		
		return "ok";
	}
	
	@RequestMapping("/getBookstores")
	@ResponseBody
	public List<Bookstore> getBookstores() {
		List<Bookstore> bookstores = bookStoreRepo.getBookStores();
		return bookstores;
	}
	
	@RequestMapping("/searchBook")
	public ModelAndView searchBook(@RequestParam String word) {
		
		ModelAndView mv = new ModelAndView();
		
		List<Book> booksFounded = bookRepo.searchByTitle(word);
		System.out.println(booksFounded.toString());
		
		mv.addObject("booksFounded", booksFounded);
		
		mv.setViewName("search");
		return mv;
	}
	
}