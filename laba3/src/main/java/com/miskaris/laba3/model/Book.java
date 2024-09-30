package com.miskaris.laba3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table (name = "book", uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "author"})})
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Book {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private Integer id;

	 @Column(name = "title", nullable = false, length = 50)
	 private String title;

	 @Column(name = "author", nullable = false, length = 50)
	 private String author;
	 
	 @Column(name = "pages")
	 private Integer pages;
	 
	 private Book(BookBuilder builder) {
	        this.title = builder.title;
	        this.author = builder.author;
	        this.pages = builder.pages;
	 }
	 
	 @Accessors (chain = true)
	 public static class BookBuilder {
		 private String title;
		 private String author;
		 @Setter
		 private Integer pages;
	     
		 public Book build() {
			 return new Book(this);
		 }

		 public BookBuilder(String title, String author) {
			 this.title = title;
			 this.author = author;
		 }
	 }
	 
	 public static BookBuilder builder(String title, String author) {
		 return new BookBuilder(title, author);
	 }
}
