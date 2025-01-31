package com.libraryjdbc.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.libraryjdbc.Main;
import com.libraryjdbc.entity.User;
import com.libraryjdbc.printer.IPrinter;
import com.libraryjdbc.printer.UserPrinter;

public class UserMangement implements Imanagement {
    private static UserMangement userMangement;
    private Scanner sc;
    private Connection connection;
    private IPrinter Printer;

    public static UserMangement createManagement(Scanner sc, Connection connection) {
        if(userMangement!=null)
          return userMangement;
          else
            return userMangement = new UserMangement(sc, connection);
    }
    @Override
    public void startManagement() throws ClassNotFoundException, SQLException {
        try {
            int choice = userCommands(sc);
            switch (choice) {
                case 1:
                    addUser(sc, connection);
                    Main.main(new String[1]);
                    break;
                case 2:
                    removeUser(sc, connection);
                    Main.main(new String[1]);
                    break;
                case 3:
                    searchForUsers(sc, connection);
                    Main.main(new String[1]);
                    break;
                case 4:
                    viewAllUsers(sc, connection);
                    Main.main(new String[1]);
                    break;
                default:
                    System.out.println("something got wrong");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private UserMangement(Scanner sc, Connection connection) {
         this.sc = sc;
        this.connection = connection;
        this.Printer= new UserPrinter();
    }

    private void viewAllUsers(Scanner sc, Connection connection) {
        String sql = "select * from user ; ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Printer.excute(rs);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchForUsers(Scanner sc, Connection connection) {
        String sql = "select * from user where name=?";
        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Enter the user's name:");
            sc.nextLine();
            user.setName(sc.nextLine());
            statement.setString(1, user.getName());
            ResultSet rs = statement.executeQuery();
            if (rs == null)
                System.out.println("user not found");
            else {
               Printer.excute(rs);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void removeUser(Scanner sc, Connection connection) {
        String sql = "delete from user where user_id=?";
        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Enter the user's id:");
            sc.nextLine();
            user.setUserId(sc.nextInt());
            statement.setInt(1, user.getUserId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("the user with the user id " + user.getUserId() + " is deleted");
            } else {
                System.out.println("the user is not  deleted");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void addUser(Scanner sc, Connection connection) {
        User user = new User();
        sc.nextLine(); // Consume the leftover newline
        System.out.println("Enter the user name:");
        user.setName(sc.nextLine());
        System.out.println("Enter the user's Address:");
        user.setAddress(sc.nextLine());
        System.out.println("Enter the user's Email");
        user.setEmail(sc.nextLine());
     
        System.out.println("Enter user's Phone:");
        user.setPhone(sc.nextLine());
        
        String sql = "insert into user(name,address,email,phone)VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            
           
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            } else {
                System.out.println("the user is not inserted");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public int userCommands(Scanner sc) throws ClassNotFoundException, SQLException {
        int choice;
        do {
            System.out.println("\n=== user Management System ===");
            System.out.println("1. Add a user ");
            System.out.println("2. Remove a user");
            System.out.println("3. search for a user");
            System.out.println("4. view all the users");
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
