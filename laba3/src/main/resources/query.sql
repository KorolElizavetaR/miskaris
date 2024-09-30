CREATE TABLE book
(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	title varchar(50) NOT NULL,
	author varchar(50) NOT NULL,
	pages int,
	UNIQUE (title, author)
);

INSERT INTO Book(title, author) VALUES
	('Мартин Иден', 'Джек Лондон'),
	('Странник по звездам', 'Джек Лондон'),
	('Дьяволида', 'Булгаков'),
	('Собачье сердце', 'Булгаков'),
	('Роковые яйца', 'Булгаков');
	
INSERT INTO Book(title, author, pages) VALUES
	('Степной волк', 'Герман Гэссе', 345),
	('Дэмиан', 'Герман Гэссе', 467);
	
CREATE TABLE log_books
(
	message text NOT NULL,
	executed_at timestamp default NOW(),
	PRIMARY KEY (message, executed_at)
);
SELECT * FROM log_books;