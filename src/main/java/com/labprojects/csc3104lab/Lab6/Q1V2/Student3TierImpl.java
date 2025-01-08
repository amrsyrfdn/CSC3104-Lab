package com.labprojects.csc3104lab.Lab6.Q1V2;

import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;

/**
 * Student3TierImpl is the implementation of the StudentServerInterface.
 * This class handles the RMI operations and queries the database to retrieve student scores.
 */
public class Student3TierImpl extends UnicastRemoteObject implements StudentServerInterface {
    // Prepared statement used for querying the database
    private PreparedStatement pstmt;

    /**
     * Default constructor that exports the object and initializes the database connection.
     */
    public Student3TierImpl() throws RemoteException {
        initializeDB();
    }

    /**
     * Constructor that exports the object on a specified port and initializes the database connection.
     *
     * @param port The port for exporting
     */
    public Student3TierImpl(int port) throws RemoteException {
        super(port);
        initializeDB();
    }

    /**
     * Initializes the database connection by loading the JDBC driver and creating a prepared statement
     * for querying the database.
     */
    protected void initializeDB() {
        try {
            // Load Oracle JDBC Driver for database connection
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver registered");

            // Establish the connection to the Oracle database using JDBC
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521/FSKTM", "E224300", "224300");
            System.out.println("Database connected");

            // Prepare the SQL query to fetch student scores by name
            pstmt = conn.prepareStatement("select * from Scores where name = ?");
        } catch (Exception ex) {
            // Catch any exceptions during the database initialization
            System.out.println(ex);
        }
    }

    /**
     * Queries the database for the score of a student by name.
     *
     * @param name The name of the student
     * @return The student's score if found, or -1 if the score is not found or not permitted
     */
    @Override
    public double findScore(String name) throws RemoteException {
        double score = -1; // Default to -1 if score is not found
        try {
            // Set the name parameter in the prepared statement
            pstmt.setString(1, name);

            // Execute the query to retrieve the score from the database
            ResultSet rs = pstmt.executeQuery();

            // Check if a result was returned
            if (rs.next()) {
                // Check if the score is permitted to be shown (column 3 in the database)
                if (rs.getBoolean(3)) {
                    // Retrieve the score from the second column and return it
                    score = rs.getDouble(2);
                }
            }
        } catch (SQLException ex) {
            // Catch any SQL exceptions
            System.out.println(ex);
        }

        return score;
    }
}
