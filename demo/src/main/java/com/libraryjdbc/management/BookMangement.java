package com.libraryjdbc.management;
import java.sql.SQLException;
import java.util.Scanner;
import com.libraryjdbc.Main;
public class BookMangement {
    
    public BookMangement(Scanner sc) throws ClassNotFoundException, SQLException {
        int choice =bookCommands(sc);
        switch (choice) {
            case 1:
                 new BookMangement(sc);
                 Main.main(new String[1]);
                break;
            case 2:
                 new UserMangement(sc);
                 Main.main(new String[1]);
                break;
            case 3:
                 new TransactionMangement(sc);
                 Main.main(new String[1]);
                break;
            default:
                System.out.println("something got wrong");
        }
    }
    public void addBook(){
   
}
public int bookCommands(Scanner sc) throws ClassNotFoundException, SQLException{
    int choice;
    do {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Add a book ");
        System.out.println("2. Remove a book");
        System.out.println("3. search for a book");
        System.out.println("4.  return to previous list");
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();
    } while (choice < 0 || choice > 4);
    if (choice == 4) {
        Main.main(new String[1]);
    }
    return choice;
}
}
