package com.example.servletproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection initDatabase() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch( ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/books", "root", "root");
    }
}