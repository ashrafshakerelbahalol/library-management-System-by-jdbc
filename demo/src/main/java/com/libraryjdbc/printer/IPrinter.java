package com.libraryjdbc.printer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPrinter {
    void excute(ResultSet rs) throws Exception;
}
