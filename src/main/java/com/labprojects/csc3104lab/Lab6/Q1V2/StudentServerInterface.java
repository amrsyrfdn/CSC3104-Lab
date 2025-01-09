/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB6Q1
 */
package com.labprojects.csc3104lab.Lab6.Q1V2;

import java.rmi.*;

/**
 * StudentServerInterface is the RMI interface for the server that allows clients
 * to query student scores. The interface extends the Remote interface, which means
 * that its methods can be invoked remotely.
 */
public interface StudentServerInterface extends Remote {

    /**
     * Method to find the score of a student by name.
     * @param name The name of the student whose score is to be retrieved.
     * @return The score of the student, or -1 if the score is not available.
     * @throws RemoteException if there is an issue with the remote method invocation.
     */
    double findScore(String name) throws RemoteException;
}
