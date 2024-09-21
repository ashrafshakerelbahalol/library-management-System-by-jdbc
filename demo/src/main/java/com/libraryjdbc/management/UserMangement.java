package com.libraryjdbc.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.libraryjdbc.Main;
import com.libraryjdbc.entity.User;

public class UserMangement {

    public UserMangement(Scanner sc, Connection connection) {
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

    private void viewAllUsers(Scanner sc, Connection connection) {
        String sql = "select * from user ; ";
        User user = new User();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                user.setUserId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setAddress(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setUserType(rs.getString(6));
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchForUsers(Scanner sc, Connection connection) {
        String sql = "select * from user where user_id=?";
        User user = new User();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Enter the user id:");
            sc.nextLine();
            user.setUserId(sc.nextInt());
            statement.setInt(1, user.getUserId());
            ResultSet rs = statement.executeQuery();
            if (rs == null)
                System.out.println("user not found");
            else {
                while (rs.next()) {
                    user.setUserId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setAddress(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    user.setPhone(rs.getString(5));
                    user.setUserType(rs.getString(6));
                    System.out.println(user.toString());
                }
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
        System.out.println("Enter user's type:");
        user.setUserType(sc.nextLine());
        String sql = "insert into user(name,address,email,phone,user_type)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getUserType());
           
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
