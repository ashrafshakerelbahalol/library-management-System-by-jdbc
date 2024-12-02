package com.libraryjdbc.management;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import com.libraryjdbc.Main;
import com.libraryjdbc.entity.Transaction;

public class TransactionMangement {

    public TransactionMangement(Scanner sc, Connection connection) {

        try {
            int choice = transactionsCommands(sc);
            switch (choice) {
                case 1:
                    borrowABook(sc, connection);
                    Main.main(new String[1]);
                    break;
                case 2:
                    retunABook(sc, connection);
                    Main.main(new String[1]);
                    break;
                default:
                    System.out.println("something got wrong");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int transactionsCommands(Scanner sc) throws ClassNotFoundException, SQLException {
        int choice;
        do {
            System.out.println("\n=== user Management System ===");
            System.out.println("1. borrow book ");
            System.out.println("2. return a book");
            System.out.println("3. return");
            choice = sc.nextInt();
        } while (choice < 0 || choice > 3);
        if (choice == 3) {
            Main.main(new String[1]);
        }
        return choice;

    }

    public void borrowABook(Scanner sc, Connection connection) {
        Transaction transaction = new Transaction();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user_id=?");
            System.out.println("Enter user id :");
            transaction.setUserId(sc.nextInt());
            statement.setInt(1, transaction.getUserId());
            boolean isUserFound = statement.execute();
            statement = connection.prepareStatement("SELECT * FROM book WHERE book_id=?");
            System.out.println("Enter book id :");
            transaction.setBookId(sc.nextInt());
            statement.setInt(1, transaction.getBookId());
            boolean isBookFound = statement.execute();
     
            ResultSet result = statement.executeQuery();
            result.next();
            int bookNumber = result.getInt("quantity");
            if (!isBookFound ) {
                System.out.println("there is  no book with this id");
                connection.setAutoCommit(true);
                Main.main(new String[1]);
            }else if (!isUserFound ) {
                System.out.println("there is no user with this id");
                connection.setAutoCommit(true);
                Main.main(new String[1]);
             }else if( bookNumber <= 0){
                System.out.println("there is no copy of this book");
                connection.setAutoCommit(true);
                Main.main(new String[1]);
             }
            statement = connection.prepareStatement("Update book set quantity=quantity-1 where book_id=?;");
            statement.setInt(1, transaction.getBookId());
            statement.execute();
            statement = connection.prepareStatement("INSERT INTO transaction (book_id, user_id " +
                    " ,issue_date)" +
                    "  VALUES(?,?,?) ;");
            statement.setInt(1, transaction.getBookId());
            statement.setInt(2, transaction.getUserId());
            Date currentDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            statement.setDate(3, sqlDate);
            statement.execute();
            connection.commit();
            System.out.println(" the transaction has been created");
      
            connection.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("something got wrong");
            System.out.println("Error: " + e.getMessage());

        }

    }

    private void retunABook(Scanner sc, Connection connection) {
        Transaction transaction = new Transaction();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user_id=?");
            System.out.println("Enter user id :");
            transaction.setUserId(sc.nextInt());
            statement.setInt(1, transaction.getUserId());
            boolean isUserFound = statement.execute();
            statement = connection.prepareStatement("SELECT * FROM book WHERE book_id=?");
            System.out.println("Enter book id :");
            transaction.setBookId(sc.nextInt());
            statement.setInt(1, transaction.getBookId());
            boolean isBookFound = statement.execute();
            statement = connection.prepareStatement("SELECT * FROM transaction WHERE transaction_id=?");
            System.out.println("Enter transaction id :");
            transaction.setTransactionId(sc.nextInt());
            statement.setInt(1, transaction.getTransactionId());
            boolean isTransactionFound = statement.execute();
            if (!isBookFound || !isUserFound || !isTransactionFound) {
                System.out.println("something got wrong");
                connection.setAutoCommit(true);
                Main.main(new String[1]);
            }
            statement = connection.prepareStatement("Update book set quantity=quantity+1 where book_id=?;");
            statement.setInt(1, transaction.getBookId());
            statement.execute();

            statement = connection.prepareStatement(
                    "update transaction set return_date=? where user_id=? And book_id=? And transaction_id=?");
            Date currentDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            statement.setDate(1, sqlDate);
            statement.setInt(2, transaction.getBookId());
            statement.setInt(3, transaction.getUserId());
            statement.setInt(4, transaction.getTransactionId());
            statement.execute();
            System.out.println(" the book has been returned");
            statement = connection
                    .prepareStatement("SELECT return_date -issue_date FROM transaction WHERE transaction_id=?");
            statement.setInt(1, transaction.getTransactionId());
            ResultSet result = statement.executeQuery();
            result.next();
            int differnceOfDate = result.getInt(1);

            if (differnceOfDate < 7)
                System.out.println("there is no fine");
            else {
                statement = connection.prepareStatement("update transaction set fine_amount=? where  transaction_id=?");
                transaction.setFineAmount(differnceOfDate * 5);
                System.out.println(transaction.getFineAmount());
                statement.setDouble(1, transaction.getFineAmount());
                statement.setInt(2, transaction.getTransactionId());
                statement.execute();

            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
