package com.miskaris.laba3.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

import com.miskaris.laba3.model.Book;
import com.miskaris.laba3.repository.BookRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {
	@MockBean
	private BookRepository repos;
	@Autowired
	private BookService service;
	
	@Test
	@Order (20)
	void testAddBook() {
		Book book = Book.builder("1984", "Джордж Оруэлл").build();
		when(repos.save(book)).thenReturn(book);
		service.addBook(book);
		verify(repos).save(book);
	}

	@Test
	@Order (30)
	void testUpdateBook() {
		Book book = Book.builder("1984", "Джордж Оруэлл").build();
		book.setId(1);
		
		Book newBook = Book.builder("Скотный двор", "Джордж Оруэлл").build();
		
		given(repos.findById(book.getId())).willReturn(Optional.of(newBook));
		service.editBook(book.getId(), newBook);
		verify(repos).save(Mockito.any(Book.class));
	}

	@Test
	@Order (15)
	void testFetchAllBooks() {
		List<Book> progs = new ArrayList<>(Collections.singleton(Book.builder("1984", "Джордж Оруэлл").build()));
		given(repos.findAll()).willReturn(progs);
		List<Book> expected = service.getAllBooks();
		assertEquals(expected, progs);
		verify(repos).findAll();
	}

	@Test
	@Order (10)
	void testFetchBookById() {
		Book book = Book.builder("1984", "Джордж Оруэлл").build();
		book.setId(1);
		
		when(repos.findById(book.getId())).thenReturn(Optional.of(book));
		service.getBookById(book.getId());
		verify(repos).findById(book.getId());
	}
	
	@Test
	@Order (11)
	void testFetchProgrammerById_DoesNotExists() {
		Book book = Book.builder("1984", "Джордж Оруэлл").build();
		book.setId(1);
		
		when(repos.findById(anyInt())).thenReturn(Optional.empty());

		assertThrows(NullPointerException.class, () -> service.getBookById(book.getId()));
	}
	
}
