package com.libraryjdbc.management;

import java.sql.Connection;
import java.util.Scanner;

import com.libraryjdbc.Main;

public class TransactionMangement {

    public TransactionMangement(Scanner sc, Connection connection) {
       
    }
    public int transactionsCommands(){
        int choice;
        do {
            System.out.println("\n=== user Management System ===");
            System.out.println("1. borrow book ");
            System.out.println("2. return a book");
            choice = sc.nextInt();
        } while (choice < 0 || choice > 5);
        if (choice == 5) {
            Main.main(new String[1]);
        }
        return choice;

    }
    
}
