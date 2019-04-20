package com.javeriana.sdp.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Sebastian on 21/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class provides a simple pool of SQL connections
 * Notice that this class has to be used with caution because if the user
 * doesn't disposes a connection there will be missing connections which will be
 * eventually Garbage collected by the system.
 */
public class SQLProvider {

    /**
     * Constructs the default recommended pool of sql connections
     */
    private static final int DEFAULT_CONNECTIONS = 5;

    /**
     * Constructs a singleton of the SQL Provider
     */
    private static SQLProvider SINGLETON;

    /**
     * Used for lazy initialization
     */
    private static void initialize() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }

        try {
            SINGLETON = new SQLProvider(DEFAULT_CONNECTIONS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Represents a list of all available SQL connections
     */
    private LinkedList<Connection> freeConnections = new LinkedList<Connection>();

    /**
     * Constructs the main instance of the SQL Provider providing up to N freeConnections
     * @param connections   the amount of connections required
     * @throws SQLException throws an exception if the connection couldn't be stablished
     */
    public SQLProvider(final int connections) throws SQLException {
        for (int i = 0; i < connections; ++ i) {
            freeConnections.add(DriverManager.getConnection(SQLConstants.URL, SQLConstants.USERNAME, SQLConstants.PASSWORD));
        }
    }

    /**
     * Checks if whether or not there are free connections to take
     * @return  true if there are free connections, false otherwise
     */
    public boolean hasFreeConnections() {
        return !freeConnections.isEmpty();
    }

    /**
     * Takes a free connection
     * @return  the connection
     */
    public Connection take() {
        final Connection connection = freeConnections.poll();
        return connection;
    }

    /**
     * Disposes a connection and makes it free again
     * @param connection    the connection instance to dispose
     */
    public void dispose(final Connection connection) {
        freeConnections.add(connection);
    }

    /**
     * Returns this singleton instance
     * @return  the singleton
     */
    public static SQLProvider getSingleton() {
        if (SINGLETON == null) {
            initialize();
        }
        return SINGLETON;
    }
}

