package com.labprojects.csc3104lab.Lab5.Q2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BMIServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the implementation
            BMIServerInterface obj = new BMIServerInterfaceImpl();

            // Bind the object to the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("BMIServerInterfaceImpl", obj);
        } catch (Exception e) {
            e.printStackTrace(); // Print exception details
        }
    }
}
