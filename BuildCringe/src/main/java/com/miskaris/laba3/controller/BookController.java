package com.miskaris.laba3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miskaris.laba3.model.Book;
import com.miskaris.laba3.observer.BookObserver;
import com.miskaris.laba3.observer.BookServiceManager;
import com.miskaris.laba3.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
//@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {
	private final BookServiceManager service;
	@Autowired
	private final ApplicationContext context;
	
	public BookController(BookServiceManager service, ApplicationContext context)
	{
		this.service = service;
		this.context = context;
		service.addObserver(context.getBean(BookObserver.class));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addBook(book));
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getBooks()
	{
		return ResponseEntity.ok(service.getAllBooks());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable ("id") Integer id)
	{
		return ResponseEntity.ok(service.getBookById(id));
	}
	
	@PatchMapping ("/{id}")
	public ResponseEntity<Book> patchProgrammer(@PathVariable ("id") Integer id, @RequestBody Book book)
	{
		return ResponseEntity.ok(service.editBook(id, book));
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<String> deleteProgrammer(@PathVariable ("id") Integer id)
	{
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book is succesfully deleted");
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleConstraintViolation(NullPointerException ex) {
		return ResponseEntity.notFound().build();
	}
}
