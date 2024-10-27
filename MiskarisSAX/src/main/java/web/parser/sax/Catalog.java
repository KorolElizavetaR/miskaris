package web.parser.sax;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Catalog {
	private ArrayList <Book> books = new ArrayList<>();

    public void push(Book b) {
        books.add(b);
    }

}
