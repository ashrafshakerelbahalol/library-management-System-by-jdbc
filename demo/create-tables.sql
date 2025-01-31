CREATE SCHEMA library;

CREATE TABLE library.Book(  
    book_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50),
    publisher VARCHAR(50),
    genre VARCHAR(30),
    quantity INT,
    year_of_publication INT,
    auther VARCHAR(50)
);
CREATE TABLE library.user(  
    user_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    address VARCHAR(100),
    phone VARCHAR(14),
    email VARCHAR(30),
);
CREATE TABLE library.transaction(  
    transaction_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id int,
    book_id int,
    issueDate DATE,
    returnDate DATE,
    fineAmount DOUBLE,
   FOREIGN KEY (user_id)  REFERENCES  user(user_id),
    FOREIGN KEY (book_id)  REFERENCES book(book_id)
);

