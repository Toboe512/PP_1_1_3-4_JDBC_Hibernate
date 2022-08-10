package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private final String DB_URL = "jdbc:mysql://localhost:3306/pp_113?useSSL=false";
    private final String DB_USER_NAME = "root";
    private final String DB_PASSWORD = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
    }
}
