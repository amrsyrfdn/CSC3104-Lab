package com.labprojects.csc3104lab.Lab6.Q1V2;

import java.rmi.registry.*;

/**
 * RegisterStudent3TierServer class registers the remote Student3TierImpl
 * RMI object with the RMI registry on a specified port (1101 in this case).
 */
public class RegisterStudent3TierServer {

    /**
     * Main method to start the RMI server and register the remote object.
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        try {
            // Create an instance of the Student3TierImpl class, which implements StudentServerInterface
            StudentServerInterface obj = new Student3TierImpl();

            // Create and bind the RMI registry on port 1101
            Registry registry = LocateRegistry.createRegistry(1101); // Set up the registry on port 1101

            // Register the remote object in the RMI registry under the name "Student3TierImpl"
            registry.rebind("Student3TierImpl", obj);

            // Confirmation message to indicate the server is registered
            System.out.println("Student server " + obj + " registered");

        } catch (Exception ex) {
            // Handle any exceptions that occur during registry creation or binding
            ex.printStackTrace();
        }
    }
}
