package org.carrental.dao;

import java.sql.*;

public class BaseDAO {
    static final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
    static final String USER = "root";
    static final String PASS = "root";
    Connection conn;

    BaseDAO(){
        try {
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
