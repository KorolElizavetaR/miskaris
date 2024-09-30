package com.miskaris.laba3.observer;

import java.util.List;

import com.miskaris.laba3.model.Book;
import com.miskaris.laba3.service.BookService;

import lombok.RequiredArgsConstructor;

/*
 * 		В идеале данный класс должен имплементироваться от интерфейса,
 * 		но я ленивый гусь с:
 */

@RequiredArgsConstructor
public class BookServiceManager {
	private final BookService bookService;
	/*
	 * 		в эту переменную обычно заносят List наблюдателей, но
	 * 		в моей программе наблюдатель лишь один и его задача -
	 * 		заносить события в базу данных
	 */
	Observer observer; 
	
	private void notifyObserver()
	{
		
	}
	
	public List<Book> getAllBooks()
	{
		List<Book> books = bookService.getAllBooks();
	}
	
	public List<Book> getAllBooksSortedByTitle()
	{
		List<Book> books = bookService.getAllBooksSortedByTitle();
	}
	
	public List<Book> getBooksByAuthor(String author)
	{
		List<Book> books = bookService.getBooksByAuthor(String author);
	}
	
	public Book getBookById(Integer id)
	{
		Book book = bookService.getBookById(id);
	}
	
	public Book addBook(Book book)
	{
		Book book = bookService.addBook(book);
	}
	
	public Book editBook(Integer id, Book book)
	{
		Book book = bookService.editBook(id, book);
	}
	
	public void deleteBook(Integer id)
	{
		Book book = bookService.deleteBook(id);
	}
}
