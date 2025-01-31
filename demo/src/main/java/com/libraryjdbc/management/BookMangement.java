package com.libraryjdbc.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.libraryjdbc.Main;
import com.libraryjdbc.entity.Book;
import com.libraryjdbc.printer.BookPrinter;
import com.libraryjdbc.printer.IPrinter;

public class BookMangement implements Imanagement {
    private static BookMangement bookMangement;
    private Scanner sc;
    private Connection connection;
    private IPrinter printer;

    public static BookMangement createManagement(Scanner sc, Connection connection) {
        if (bookMangement != null)
            return bookMangement;
        else
            return bookMangement = new BookMangement(sc, connection);
    }

    private BookMangement(Scanner sc, Connection connection) {
        this.sc = sc;
        this.connection = connection;
        this.printer = new BookPrinter();

    }

    @Override
    public void startManagement() {
        try {
            int choice = bookCommands();
            switch (choice) {
                case 1:
                    addBook();
                    Main.main(new String[1]);
                    break;
                case 2:
                    removeBook();
                    Main.main(new String[1]);
                    break;
                case 3:
                    searchForBooks();
                    Main.main(new String[1]);
                    break;
                case 4:
                    viewAllBooks();
                    Main.main(new String[1]);
                    break;
                default:
                    System.out.println("something got wrong");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void viewAllBooks() {
        String sql = "select book_id,title,author,publisher,quantity,genre,year_of_publication from book ; ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            printer.excute(rs);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchForBooks() {
        String sql = "select book_id,title,author,publisher,quantity,genre,year_of_publication from book where title=?";
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

                printer.excute(rs);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void removeBook() {
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

    public void addBook() {
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
        book.setYearOfPublication(sc.nextInt());
        String sql = "insert into book(title,author,publisher,quantity,genre,year_of_publication)VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getQuantity());
            statement.setString(5, book.getGenre());
            statement.setInt(6, book.getYearOfPublication());
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

    public int bookCommands() throws ClassNotFoundException, SQLException {
        int choice;
        do {
            System.out.println("\n=== Book Management System ===");
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
