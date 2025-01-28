/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB8Q1
 */
package com.labprojects.csc3104lab.Lab8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab8Q1 {
    private BMIServerInterfaceImpl bmiServer;

    // Set up the BMI server implementation before each test
    @BeforeEach
    public void setUp() throws RemoteException {
        bmiServer = new BMIServerInterfaceImpl(); // Initialize the server implementation
    }

    // Test the BMI calculation for valid weight and height
    @Test
    public void testCalcBMI() {
        try {
            // Test with different values of weight and height
            double bmi = bmiServer.calcBMI(70, 1.75); // Example: weight = 70kg, height = 1.75m
            assertEquals(22.86, bmi, 0.01); // Expected BMI value with tolerance

            bmi = bmiServer.calcBMI(50, 1.60); // Example: weight = 50kg, height = 1.60m
            assertEquals(19.53, bmi, 0.01); // Expected BMI value with tolerance
        } catch (RemoteException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
    }

    // Test the result of BMI categorization
    @Test
    public void testGetResult() throws RemoteException {
        // Test with different BMI values
        ArrayList<String> result = bmiServer.getResult(22.86); // Normal weight BMI
        assertEquals("18.5 - 24.9", result.get(0)); // Expected BMI category
        assertEquals("Normal Weight", result.get(1)); // Expected classification
        assertEquals("Least", result.get(2)); // Expected risk

        result = bmiServer.getResult(30.5); // Obese class I BMI
        assertEquals("30.0 - 34.9", result.get(0)); // Expected BMI category
        assertEquals("Obese class I", result.get(1)); // Expected classification
        assertEquals("High", result.get(2)); // Expected risk
    }
}
