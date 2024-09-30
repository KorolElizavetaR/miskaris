package com.miskaris.laba3.observer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.miskaris.laba3.model.Book;
import com.miskaris.laba3.service.BookService;

import lombok.RequiredArgsConstructor;

/*
 * 		В идеале данный класс должен имплементироваться от интерфейса,
 * 		но я ленивый гусь с:
 */

@RequiredArgsConstructor
@Component
public class BookServiceManager {
	private final BookService bookService;
	/*
	 * 		в эту переменную обычно заносят List наблюдателей, но
	 * 		в моей программе наблюдатель лишь один и его задача -
	 * 		заносить события в базу данных
	 */
	List <Observer> observers = new ArrayList<>(); 
	
	public void addObserver(Observer observer)
	{
		observers.add(observer);
	}
	
	// должен быть removeObserver но я патат
	
	private void notifyObservers(String message)
	{
		observers.stream().forEach(observer -> observer.registerEvent(message));
	}
	
	public List<Book> getAllBooks()
	{
		List<Book> books = bookService.getAllBooks();
		notifyObservers("getAllBooks()");
		return books;
	}
	
	public List<Book> getAllBooksSortedByTitle()
	{
		List<Book> books = bookService.getAllBooksSortedByTitle();
		notifyObservers("getAllBooksSortedByTitle()");
		return books;
	}
	
	public List<Book> getBooksByAuthor(String author)
	{
		List<Book> books = bookService.getBooksByAuthor(author);
		notifyObservers("getBooksByAuthor(String author)");
		return books;
	}
	
	public Book getBookById(Integer id)
	{
		Book book = bookService.getBookById(id);
		notifyObservers("getBookById(Integer id)");
		return book;
	}
	
	public Book addBook(Book book)
	{
		Book temp = bookService.addBook(book);
		notifyObservers("addBook(Book book)");
		return temp;
	}
	
	public Book editBook(Integer id, Book book)
	{
		Book temp = bookService.editBook(id, book);
		notifyObservers("editBook(Integer id, Book book)");
		return temp;
	}
	
	public void deleteBook(Integer id)
	{
		bookService.deleteBook(id);
		notifyObservers("deleteBook(Integer id)");
	}
}
