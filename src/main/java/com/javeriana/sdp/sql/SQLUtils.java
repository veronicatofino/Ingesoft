package com.javeriana.sdp.sql;

import com.javeriana.sdp.utils.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Sebastian on 21/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class SQLUtils {

    /**
     * Represents the aspirant section used for the resources
     */
    public static final int ASPIRANT_SECTION = 2;

    /**
     * Represents the students section used for the resources
     */
    public static final int STUDENTS_SECTION = 1;

    /** This class can't be instantiated **/
    private SQLUtils() { }

    /**
     * Polls all the updatable content from the web site
     * @param categoryId    the category of the content
     * @param connection    the connection instance
     * @return  the string representing the content
     */
    public static String pollContent(int categoryId, Connection connection) {
        Statement statement = null;
        String data = "";
        try {
            final String query = "SELECT data FROM Content where categoryId = " + categoryId;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                data = resultSet.getString("data");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }
    /**
     * Clears the data in a certain table
     * @param table the table to clear
     */
    public static void clearTable(final String table, final Connection connection) {
        Statement statement = null;
        try {
            final String query = "DELETE FROM " + table;
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Executes multiple queries in one single pass
     * @param connection    the connection instance
     * @param queries   the queries
     */
    public static void executeQueries(Connection connection, String ... queries) {
        Statement statement = null;
        for (String query : queries) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
