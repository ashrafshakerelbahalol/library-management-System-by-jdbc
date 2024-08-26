package com.libraryjdbc.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.libraryjdbc.Main;
import com.libraryjdbc.entity.Book;

public class BookMangement {

    public BookMangement(Scanner sc, Connection connection) throws ClassNotFoundException, SQLException {
        int choice = bookCommands(sc);
        switch (choice) {
            case 1:
                addBook(sc, connection);
                Main.main(new String[1]);
                break;
            case 2:
                removeBook(sc, connection);
                Main.main(new String[1]);
                break;
            case 3:
                searchForBooks(sc, connection);
                Main.main(new String[1]);
                break;
            case 4:
                viewAllBooks(sc, connection);
                Main.main(new String[1]);
                break;
            default:
                System.out.println("something got wrong");
        }
    }

    private void viewAllBooks(Scanner sc, Connection connection) {
        String sql = "select * from book ; ";
        Book book = new Book();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                book.setBookId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setPublisher(rs.getString(4));
                book.setGenre(rs.getString(5));
                book.setYearOfPublication(rs.getString(6));
                book.setQuantity(rs.getInt(7));
                System.out.println(book.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchForBooks(Scanner sc, Connection connection) {
        String sql = "select * from book where title=?";
        Book book = new Book();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Enter the book name:");
            sc.nextLine();
            book.setTitle(sc.nextLine());
            statement.setString(1, book.getTitle());
            ResultSet rs = statement.executeQuery();
            if (rs == null)
                System.out.println("book not found");
            else {
                while (rs.next()) {
                    book.setBookId(rs.getInt(1));
                    book.setAuthor(rs.getString(3));
                    book.setPublisher(rs.getString(4));
                    book.setGenre(rs.getString(5));
                    book.setYearOfPublication(rs.getString(6));
                    book.setQuantity(rs.getInt(7));
                    System.out.print(book.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void removeBook(Scanner sc, Connection connection) {
        String sql = "delete from book where title=?";
        Book book = new Book();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Enter the book name:");
            sc.nextLine();
            book.setTitle(sc.nextLine());
            statement.setString(1, book.getTitle());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("the book with the title " + book.getTitle() + " is deleted");
            } else {
                System.out.println("the book is not  deleted");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void addBook(Scanner sc, Connection connection) {
        Book book = new Book();
        sc.nextLine(); // Consume the leftover newline
        System.out.println("Enter the book name:");
        book.setTitle(sc.nextLine());
        System.out.println("Enter the book's author:");
        book.setAuthor(sc.nextLine());
        System.out.println("Enter the book's publisher:");
        book.setPublisher(sc.nextLine());
        System.out.println("Enter the number of books:");
        book.setQuantity(sc.nextInt());
        sc.nextLine(); // Consume the leftover newline
        System.out.println("Enter book genre:");
        book.setGenre(sc.nextLine());
        System.out.println("Enter year of publication:");
        book.setYearOfPublication(sc.nextLine());
        String sql = "insert into book(title,author,publisher,quantity,genre,year_of_publication)VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getQuantity());
            statement.setString(5, book.getGenre());
            statement.setString(6, book.getYearOfPublication());
            System.out.println(book);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new book was inserted successfully!");
            } else {
                System.out.println("the book is not inserted");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public int bookCommands(Scanner sc) throws ClassNotFoundException, SQLException {
        int choice;
        do {
            System.out.println("\n=== book Management System ===");
            System.out.println("1. Add a book ");
            System.out.println("2. Remove a book");
            System.out.println("3. search for a book");
            System.out.println("4. view all the books");
            System.out.println("5. return to previous list");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
        } while (choice < 0 || choice > 5);
        if (choice == 5) {
            Main.main(new String[1]);
        }
        return choice;
    }
}
