package com.libraryjdbc.entity;

public class Book {
     private int bookId ;
     private String  title;
     private String  author;
     private String  publisher;
     private String  yearOfPublication;
     private String  genre;
     private int  quantity;
  
    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", publisher=" + publisher
                + ", yearOfPublication=" + yearOfPublication + ", genre=" + genre + ", quantity=" + quantity + "]";
    }
    public String getYearOfPublication() {
        return yearOfPublication;
    }
    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    } 
      public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Book(String title, String author, String publisher, String yearOfPublication, String genre, int quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
        this.quantity = quantity;
    }
    public Book(){

    }
 
    

}
