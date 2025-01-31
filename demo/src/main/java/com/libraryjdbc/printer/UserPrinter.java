package com.libraryjdbc.printer;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.libraryjdbc.entity.User;

public class UserPrinter implements IPrinter {

    @Override
    public void excute(ResultSet rs) throws SQLException {
        User user=new User();
        while (rs.next()) {
            user.setUserId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setAddress(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setPhone(rs.getString(5));
            System.out.println(user.toString());
        }  
    }
    
}
