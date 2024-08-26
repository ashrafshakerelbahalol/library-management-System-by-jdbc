package com.libraryjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.libraryjdbc.management.BookMangement;
import com.libraryjdbc.management.TransactionMangement;
import com.libraryjdbc.management.UserMangement;

public class Main {
    public static  Connection connection = null;

    public static void makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "1234");
        System.out.println("make a connection");
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);
        int choice = commands(sc);
       if (connection == null)
          makeConnection();
        switch (choice) {
            case 1:
                 new BookMangement(sc,connection);
               
                break;
            case 2:
                 new UserMangement(sc);
               
                break;
            case 3:
                 new TransactionMangement(sc);
              
                break;
            default:
                System.out.println("something got wrong");
        }

    }

    public static int commands(Scanner sc) {
        int choice;
        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Book Management");
            System.out.println("2. User Management");
            System.out.println("3. Transaction Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
        } while (choice < 0 || choice > 4);
        if (choice == 4) {
            System.exit(0);
        }
        return choice;
    }

}