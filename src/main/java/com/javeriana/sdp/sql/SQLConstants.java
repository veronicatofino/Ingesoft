package com.javeriana.sdp.sql;

/**
 * Created by Daniel Mamian on 18/11/18
 * Email: danimaster99@hotmail.com
 *
 * This class manages the constant values for the SQL related stuff
 */
public class SQLConstants {

    /**
     * Represents the host of the database
     */
    public static final String HOST = "localhost";

    /**
     * Represents the port of the database
     */
    public static final String PORT = "3306";

    /**
     * Represents the database name
     */
    public static final String DATABASE_NAME = "laura";

    /**
     * Represents the username of the database
     */
    public static final String USERNAME = "root";

    /**
     * Represents the password of the database
     */
    public static final String PASSWORD = "";

    /**
     * Represents the url of the database
     */
    public static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" +  DATABASE_NAME;
}
