package com.libraryjdbc.entity;

public class Book {
     private int bookId ;
     private String  title;
     private String  author;
     private String  publisher;
     private String  year_of_publication;
     private String  genre;
     private int  quantity;
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
    public String getYear_of_publication() {
        return year_of_publication;
    }
    public void setYear_of_publication(String year_of_publication) {
        this.year_of_publication = year_of_publication;
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
    public Book(String title, String author, String publisher, String year_of_publication, String genre, int quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year_of_publication = year_of_publication;
        this.genre = genre;
        this.quantity = quantity;
    }
 
    

}
