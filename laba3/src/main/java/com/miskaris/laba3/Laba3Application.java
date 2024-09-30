package com.miskaris.laba3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.miskaris.laba3.service.BookService;

@SpringBootApplication
public class Laba3Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Laba3Application.class, args);
		BookService service = context.getBean(BookService.class);
		service.getAllBooks().stream().forEach(System.out::println);
	}

}
