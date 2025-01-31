package com.libraryjdbc.printer;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

import com.libraryjdbc.entity.Transaction;
import com.libraryjdbc.entity.User;

public class TransactionPrinter implements IPrinter {

    @Override
    public void excute(ResultSet rs) throws Exception {
        try {
            Transaction transaction = new Transaction();
            while (rs.next()) {
                transaction.setTransactionId(rs.getInt(1));
                transaction.setUserId(rs.getInt(2));
                transaction.setBookId(rs.getInt(3));
                Date date = rs.getDate(4);
                if (date != null) {
                   
                    LocalDate localDate = ((java.sql.Date) date).toLocalDate();
                    transaction.setIssueDate(localDate);
                } else {
                    transaction.setIssueDate(null);
                }
                ;
                date = rs.getDate(5);
                if (date != null) {
                    LocalDate localDate = ((java.sql.Date) date).toLocalDate();
                    transaction.setReturnDate(localDate);
                } else {
                    transaction.setReturnDate(null);
                }
                ;
                transaction.setFineAmount(rs.getDouble(6));
                System.out.println(transaction.toString());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}
