package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection con = null;

    static
    {
        String url = "jdbc:mysql://localhost:3306/db-task-manager";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            // System.out.println("good");
        }
        catch (ClassNotFoundException | SQLException e) {
throw new RuntimeException(e);
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}
