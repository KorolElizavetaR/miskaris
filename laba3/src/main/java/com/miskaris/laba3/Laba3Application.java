package com.miskaris.laba3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.miskaris.laba3.model.Book;
import com.miskaris.laba3.observer.BookObserver;
import com.miskaris.laba3.observer.BookServiceManager;

@SpringBootApplication
public class Laba3Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Laba3Application.class, args);
		BookServiceManager service = context.getBean(BookServiceManager.class);
		service.addObserver(context.getBean(BookObserver.class));
		service.getAllBooks().stream().forEach(System.out::println);
		service.addBook(Book.builder("Жизнь насекомых", "Пелевин").build());
	}

}
