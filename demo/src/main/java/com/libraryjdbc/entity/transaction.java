package com.libraryjdbc.entity;

import java.time.LocalDate;
import java.util.*;

public class transaction {
    private int transaction_id;
    private int book_id;
    private int user_id;
    private LocalDate issue_date;
    private LocalDate return_date;
    private double fine_amount;

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(LocalDate issue_date) {
        this.issue_date = issue_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public double getFine_amount() {
        return fine_amount;
    }

    public void setFine_amount(double fine_amount) {
        this.fine_amount = fine_amount;
    }

    public transaction(int book_id, int user_id, LocalDate issue_date, LocalDate return_date, double fine_amount) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.fine_amount = fine_amount;
    }

}
