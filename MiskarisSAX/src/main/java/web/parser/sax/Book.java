package web.parser.sax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors (chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
	 private String id;
	 private String author;
	 private String title;
	 private String genre;
	 private Double price;
	 private String currency;
}
