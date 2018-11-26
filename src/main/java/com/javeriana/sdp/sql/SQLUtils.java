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

    public static void allocateDynamicAttributes(final Connection connection, final String modification, final String attributeTag, final String redirectTag) {
        // Parse and update the current postgrads **/
        String [] parts = modification.split(">");
        String [] subparts = null;
        String normalized = "";
        for (int i = 0; i < parts.length - 1; ++ i) {
            // We are interested into this
            // attributeTag = programa?nombre or curso?nombre
            // <a href="programa?nombre=programaabc"
            if (parts[i].contains(attributeTag)) {
                // [<a href, programa?nombre, programaabc"]
                // Notice we are interested in the 3rd element without the " (means - 1)
                subparts = parts[i].split("=");
                // We got it!
                normalized = subparts[2].substring(0, subparts[2].length() - 1);
                // Ask if this program is in the BD, if so continue otherwise insert it
                boolean isEmpty = SQLUtils.isResultSetEmpty(connection, "SELECT * from ContentCategory WHERE name='" + normalized + "'");
                if (isEmpty) {
                    // Insert it into the content category table
                    SQLUtils.executeQueries(connection, "INSERT INTO ContentCategory (id, name) VALUES(null, '" + normalized + "')");
                    // Find the category id
                    long id = (Long) (SQLUtils.getDataFromQuery(connection, "SELECT id from ContentCategory WHERE name='" + normalized + "'", "id").get(0)[0]);
                    // Insert it into the content table
                    SQLUtils.executeQueries(connection, "INSERT INTO Content (categoryId, data, redirect) VALUES('" + id + "', '" + normalized + "', '" + redirectTag + "')");
                }
            }
        }
    }

    /**
     * Polls a category based on a pattern
     * @param connection    the connection instance
     * @param namePattern   the pattern
     * @return  A set of pairs respective to those in the DB who match the name pattern
     */
    public static LinkedList<Pair<Integer, String>> pollCategoryContent(final Connection connection, final String namePattern) {
        final LinkedList<Pair<Integer, String>> answer = new LinkedList<Pair<Integer, String>>();
        Statement statement = null;
        try {
            final String query = "SELECT * FROM ContentCategory WHERE name LIKE '" + namePattern + "'";

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                answer.add(new Pair<Integer, String>(id, name));
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
        return answer;
    }

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
            final String query = "SELECT data FROM Content WHERE categoryId = " + categoryId;
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
     * Checks if whether or not the result set is empty
     * @param connection    the connection instance
     * @param query the query instance
     * @return  true if the result set returned empty, false otherwise
     */
    public static boolean isResultSetEmpty(final Connection connection, String query) {
        Statement statement = null;
        boolean answer = false;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                answer = true;
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
        return answer;
    }

    /**
     * Attemps a query to the DB and returns the attributes asked in the attributes array
     * @param connection    the connection
     * @param query the query
     * @param attributes    the attributes to ask for
     * @return  the result is a list which contains all the answers that match the attributes of the result set
     */
    public static LinkedList<Object []> getDataFromQuery(final Connection connection, final String query, final String ... attributes) {
        Statement statement = null;
        final LinkedList<Object []> attributesAnswer = new LinkedList<Object[]>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Object [] ans = new Object[attributes.length];
                for (int i = 0; i < attributes.length; ++ i) {
                    ans[i] = resultSet.getObject(attributes[i]);
                }
                attributesAnswer.add(ans);
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
        return attributesAnswer;
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
