package com.javeriana.sdp.sql;

import java.sql.*;

/**
 * Created by Sebastian on 18/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class SqlTest {

    /**
     * Static initializer for the driver
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:mysql://" + "localhost" + ":3306/" + "SDP", "root", "");
        final String query = "SELECT * FROM RESOURCESSECTION";
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("Id");
            final String name = resultSet.getString("Name");
            System.out.println("ID: " + id + " NAME: " + name);
        }
    }
}
