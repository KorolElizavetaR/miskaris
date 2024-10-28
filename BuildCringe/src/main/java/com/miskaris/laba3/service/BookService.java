package com.miskaris.laba3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miskaris.laba3.model.Book;
import com.miskaris.laba3.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service ("bookService")
@RequiredArgsConstructor
public class BookService {
	@Autowired
	private final BookRepository bookRepository;
	
	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public List<Book> getAllBooksSortedByTitle()
	{
		return bookRepository.findAllByOrderByTitleAsc();
	}
	
	public List<Book> getBooksByAuthor(String author)
	{
		return bookRepository.findByAuthor(author);
	}
	
	public Book getBookById(Integer id)
	{
		return bookRepository.findById(id).orElseThrow(()->new NullPointerException());
	}
	
	public Book addBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	public Book editBook(Integer id, Book book)
	{
		book.setId(id);
		return addBook(book);
	}
	
	public void deleteBook(Integer id)
	{
		bookRepository.deleteById(null);
	}
}
