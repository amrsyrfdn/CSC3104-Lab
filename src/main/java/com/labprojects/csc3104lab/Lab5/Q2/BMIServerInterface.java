package com.labprojects.csc3104lab.Lab5.Q2;

import java.rmi.*;
import java.util.ArrayList;

public interface BMIServerInterface extends Remote {
    // Method to calculate BMI
    double calcBMI(double height, double weight) throws RemoteException;

    // Method to get BMI classification and risks
    ArrayList getResult(double bmi) throws RemoteException;
}
