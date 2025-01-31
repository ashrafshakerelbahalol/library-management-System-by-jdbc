package com.libraryjdbc.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import com.libraryjdbc.Main;
import com.libraryjdbc.entity.Transaction;
import com.libraryjdbc.printer.IPrinter;
import com.libraryjdbc.printer.TransactionPrinter;

public class TransactionMangement implements Imanagement {
    private static TransactionMangement transactionMangement;
    private Scanner sc;
    private Connection connection;
    private IPrinter printer;

    public static TransactionMangement createManagement(Scanner sc, Connection connection) {
        if (transactionMangement != null)
            return transactionMangement;
        else
            return transactionMangement = new TransactionMangement(sc, connection);
    }

    @Override
    public void startManagement() throws ClassNotFoundException, SQLException {
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
                case 3:
                    showAllTransactions(sc, connection);
                    Main.main(new String[1]);
                    break;

                default:

                    System.out.println("something got wrong");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    

    private TransactionMangement(Scanner sc, Connection connection) {
        this.sc = sc;
        this.connection = connection;
        this.printer = new TransactionPrinter();

    }

    public int transactionsCommands(Scanner sc) throws ClassNotFoundException, SQLException {
        int choice;
        do {
            System.out.println("\n=== Transaction Management System ===");

            System.out.println("1. borrow book ");
            System.out.println("2. return a book");
            System.out.println("3. show all transactions ");
            System.out.println("4. return");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
        } while (choice < 0 || choice > 4);
        if (choice == 4) {
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
            if (!isBookFound) {
                System.out.println("there is  no book with this id");
                connection.setAutoCommit(true);
                Main.main(new String[1]);
            } else if (!isUserFound) {
                System.out.println("there is no user with this id");
                connection.setAutoCommit(true);
                Main.main(new String[1]);
            } else if (bookNumber <= 0) {
                System.out.println("there is no copy of this book");
                connection.setAutoCommit(true);
                Main.main(new String[1]);
            }
            statement = connection.prepareStatement("UPDATE book set quantity=quantity-1 where book_id=?;");
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
            statement = connection.prepareStatement("UPDATE book set quantity=quantity+1 where book_id=?;");
            statement.setInt(1, transaction.getBookId());
            statement.execute();

            statement = connection.prepareStatement(
                    "UPDATE transaction set return_date=? where user_id=? And book_id=? And transaction_id=?");
            Date currentDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            statement.setDate(1, sqlDate);
            statement.setInt(3, transaction.getBookId());
            statement.setInt(2, transaction.getUserId());
            statement.setInt(4, transaction.getTransactionId());
            statement.execute();
            System.out.println("The book has been returned");
            statement = connection
                    .prepareStatement("SELECT DATEDIFF(return_date, issue_date) AS date_difference FROM transaction WHERE transaction_id=?");
            statement.setInt(1, transaction.getTransactionId());
            ResultSet result = statement.executeQuery();
            result.next();
            int differnceOfDate = result.getInt(1);
            statement = connection.prepareStatement("UPDATE transaction set fine_amount=? WHERE  transaction_id=?");
            if (differnceOfDate < 7)
               
                System.out.println("There is no fine");
            else {
                
                transaction.setFineAmount(differnceOfDate * 5);
            }
                System.out.println(transaction.getFineAmount());
                statement.setDouble(1, transaction.getFineAmount());
                statement.setInt(2, transaction.getTransactionId());
                statement.execute();

            
            connection.commit();
           System.out.println("The return of book is done");
            connection.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void showAllTransactions(Scanner sc2, Connection connection2) {
        String sql = "select transaction_id,user_id,book_id,issue_date,return_date,fine_amount from transaction ; ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            printer.excute(rs);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
