package com.libraryjdbc.entity;

import java.time.LocalDate;
import java.util.*;

public class transaction {
    private int transactionId;
    private int bookId;
    private int userId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private double fineAmount;
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public double getFineAmount() {
        return fineAmount;
    }
    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
    @Override
    public String toString() {
        return "transaction [transactionId=" + transactionId + ", bookId=" + bookId + ", userId=" + userId
                + ", issueDate=" + issueDate + ", returnDate=" + returnDate + ", fineAmount=" + fineAmount + "]";
    }

  

}
