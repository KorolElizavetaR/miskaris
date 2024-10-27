package com.miskaris.laba3.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.BDDMockito.given;
import com.miskaris.laba3.model.Book;
import com.miskaris.laba3.observer.BookServiceManager;

@SpringBootTest (webEnvironment=WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	private static Book book;

	@MockBean
	private BookServiceManager service;

	@BeforeAll
	public static void init() {
		book = Book.builder("1984", "Джордж Оруэлл").build();
	}

	
	@Test
	@Order (0)
	void testWrongAdress() {
		ResponseEntity<Book> response = restTemplate.getForEntity("/awdawd", Book.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	@Order (10)
	void testGetBookById() {
		given(service.getBookById(anyInt())).willReturn(book);
		
		ResponseEntity<Book> response = restTemplate.getForEntity("/books/1", Book.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().equals(book));
	}
	
	@Test
	@Order (11)
	void testGetBookById_NotFound() {
		given(service.getBookById(anyInt())).willThrow(new NullPointerException());
		
		ResponseEntity<Book> response = restTemplate.getForEntity("/books/1", Book.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
	}

	@Test
	@Order (20)
	void testAddProgrammer_Succesful() {
		given(service.addBook(Mockito.any(Book.class))).willReturn(book);
		
		ResponseEntity<Book> response = restTemplate.postForEntity("/books/add", book, Book.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().equals(book));
	}
}
