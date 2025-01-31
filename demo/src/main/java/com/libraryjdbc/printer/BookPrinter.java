package com.libraryjdbc.printer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.libraryjdbc.entity.Book;

public class BookPrinter implements IPrinter {

    @Override
    public void excute(ResultSet rs) throws SQLException {
        Book book = new Book();
        while (rs.next()) {
            book.setBookId(rs.getInt(1));
            book.setTitle(rs.getString(2));
            book.setPublisher(rs.getString(4));
            book.setAuthor(rs.getString(3));
            book.setGenre(rs.getString(6));
            book.setYearOfPublication(rs.getInt(7));
            book.setQuantity(rs.getInt(5));
            System.out.println(book.toString());

        }

    }
}
