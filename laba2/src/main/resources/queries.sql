CREATE TABLE users
(
	"id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	username varchar (50) NOT NULL,
	"password" varchar NOT NULL,
	user_role varchar NOT NULL CHECK (user_role = 'ROLE_USER' or user_role = 'ROLE_ADMIN') DEFAULT 'ROLE_USER'
)
	SELECT * FROM users;
	DROP TABLE users;

INSERT INTO users(username, "password") VALUES ('qwerty', 'qwerty');
INSERT INTO users(username, "password", user_role) VALUES ('admin', '321456', 'ROLE_ADMIN');

CREATE TABLE products
{
	"id" int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	
}