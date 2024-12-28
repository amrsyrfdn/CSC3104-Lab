package com.labprojects.csc3104lab.Lab5.Q2;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class BMIServerInterfaceImpl extends UnicastRemoteObject implements BMIServerInterface {
    public BMIServerInterfaceImpl() throws RemoteException {
        super(); // Call parent constructor
    }

    @Override
    public double calcBMI(double weight, double height) throws RemoteException {
        // Calculate BMI using the formula
        double bmi = weight / (height * height);
        return bmi;
    }

    @Override
    public ArrayList getResult(double bmi) {
        ArrayList<String> list = new ArrayList<>(); // Create a list for results
        String category, classification, risk;

        // Determine BMI category and risks
        if (bmi < 18.5) {
            category = "< 18.5";
            classification = "Underweight";
            risk = "Increased";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            category = "18.5 - 24.9";
            classification = "Normal Weight";
            risk = "Least";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            category = "25.0 - 29.9";
            classification = "Overweight";
            risk = "Increased";
        } else if (bmi >= 30.0 && bmi <= 34.9) {
            category = "30.0 - 34.9";
            classification = "Obese class I";
            risk = "High";
        } else if (bmi >= 35.0 && bmi <= 39.9) {
            category = "35.0 - 39.9";
            classification = "Obese class II";
            risk = "Very High";
        } else {
            category = ">= 40.0";
            classification = "Obese class III";
            risk = "Extremely High";
        }

        // Add results to the list
        list.add(category);
        list.add(classification);
        list.add(risk);
        return list; // Return the result list
    }
}
