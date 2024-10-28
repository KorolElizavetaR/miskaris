package com.miskaris.laba3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miskaris.laba3.model.Book;

@Repository //("bookRepository")
public interface BookRepository extends JpaRepository<Book, Integer>{
	List<Book>findByAuthor(String author);
	List<Book> findAllByOrderByTitleAsc();
}
