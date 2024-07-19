package com.revature.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection = null;
    private static Properties properties = new Properties();
    private static Connection makeConnection() throws FileNotFoundException {
        try {
            properties.load(new FileInputStream("C:\\Users\\asafb\\IdeaProjects\\Asaf_Ahmed_P0\\src\\main\\resources\\db.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName("org.postgresql.Driver");
           return DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"), properties.getProperty("password"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException, FileNotFoundException {

        return makeConnection();
    }
    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
            System.out.println("Connected");

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException | FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            conn.close();
            System.out.println("Closed connection");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
