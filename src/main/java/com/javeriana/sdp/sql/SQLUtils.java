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
     * This method is in charge of polling from the database all the information related to any section among students and aspirants.
     * This method also takes care of the composition of the main sections with the information mapping each main section with a set of topics
     * in that section.
     * @param connection    the sql connection instance
     * @param section   the section to poll from the database
     * @return  the result of the composition
     */
    public static final HashMap<String, LinkedList<Pair<String, String>>> pollResourcesComposition(final Connection connection, final int section) {
        // Polls the main sections (i.e Recursos, Informacion Financiera, ...)
        final HashMap<Integer, String> mainSections = SQLUtils.pollResourcesMainSections(connection);
        // Polls the information associated to the respective section (i.e If the section is 'Students' Then Guia de trabajo, Directrices, ... )
        final HashMap<Integer, LinkedList<Pair<String, String>>> information = SQLUtils.pollInformativeSection(connection, section);

        // Compose that information to dispense to the website frontend (Compose main section with information section)
        final HashMap<String, LinkedList<Pair<String, String>>> composition = new HashMap<String, LinkedList<Pair<String, String>>>();
        for (Map.Entry<Integer, LinkedList<Pair<String, String>>> entry : information.entrySet()) {
            String categoryName = mainSections.get(entry.getKey());
            composition.put(categoryName, entry.getValue());
        }
        return composition;
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
     * Attempts to poll the resources stored in the DB.
     * The mapping used here is Id -> Section name
     * Exceptions may be thrown
     * @param connection    the connection
     * @return  a hashmap version of the main sections
     */
    private static final HashMap<Integer, String> pollResourcesMainSections(final Connection connection) {
        final HashMap<Integer, String> answer = new HashMap<Integer, String>();
        Statement statement = null;
        try {
            final String query = "SELECT * FROM RESOURCESSECTION";
            statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                final String name = resultSet.getString("Name");
                answer.put(id, name);
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
     * Attempts to poll the resources stored in the DB.
     * The mapping used here is Section name -> Id
     * Exceptions may be thrown
     * @param connection    the connection
     * @return  a hashmap version of the main sections
     */
    public static final HashMap<String, Integer> pollResourcesSections(final Connection connection) {
        final HashMap<String, Integer> answer = new HashMap<String, Integer>();
        Statement statement = null;
        try {
            final String query = "SELECT * FROM RESOURCESSECTION";
            statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                final String name = resultSet.getString("Name");
                answer.put(name, id);
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
     * Attempts to poll the informative resources stored in the DB.
     * Exceptions may be thrown
     * @param connection    the connection
     * @param section  1 for the students section, 2 for the appplicant section
     * @return  a hashmap version of the main sections
     */
    private static final HashMap<Integer, LinkedList<Pair<String, String>>> pollInformativeSection(final Connection connection, final int section) {
        final HashMap<Integer, LinkedList<Pair<String, String>>> answer = new HashMap<Integer, LinkedList<Pair<String, String>>>();
        String table = section == 1 ? "STUDENTSINFO" : "APPLICANTINFO";
        Statement statement = null;
        try {
            final String query = "SELECT * FROM " + table;
            statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                final int resourceSection = resultSet.getInt("ResourceSection");
                final String text = resultSet.getString("Text");
                final String redirect = resultSet.getString("Redirect");
                if (!answer.containsKey(resourceSection)) {
                    answer.put(resourceSection, new LinkedList<Pair<String, String>>());
                }
                answer.get(resourceSection).add(new Pair<String, String>(text, redirect));
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
     * Executes multiple queries in one single pass
     * @param connection    the connection instance
     * @param queries   the queries
     */
    public static void executeQueries(Connection connection, String[] queries) {
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
