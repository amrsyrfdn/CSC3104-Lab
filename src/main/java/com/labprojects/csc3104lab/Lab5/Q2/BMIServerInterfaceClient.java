/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB5Q2
 */
package com.labprojects.csc3104lab.Lab5.Q2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class BMIServerInterfaceClient extends Application {
    private BMIServerInterface bmiServerInterface; // RMI Interface reference
    private TextField namefield = new TextField(); // Text field for user name input
    private TextField weightfield = new TextField(); // Text field for user weight input
    private TextField heightfield = new TextField(); // Text field for user height input
    private Button button = new Button("Calculate"); // Button to trigger BMI calculation
    private Label result = new Label(); // Label to display BMI result
    private Label bmicat = new Label(); // Label to display BMI category
    private Label bmiclass = new Label(); // Label to display BMI classification
    private Label risk = new Label(); // Label to display health risk

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane layout for organizing UI elements
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER); // Align the grid pane to the center
        pane.setHgap(5.5); // Set horizontal gap between nodes
        pane.setVgap(5.5); // Set vertical gap between nodes
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5)); // Set padding around the grid

        // Add label and text field for name input
        pane.add(new Label("Name"), 0, 0);
        pane.add(namefield, 1, 0);

        // Add label and text field for weight input
        pane.add(new Label("Weight (Kg)"), 0, 1);
        pane.add(weightfield, 1, 1);

        // Add label and text field for height input
        pane.add(new Label("Height (m)"), 0, 2);
        pane.add(heightfield, 1, 2);

        // Add the "Calculate" button
        pane.add(button, 1, 3);
        GridPane.setHalignment(button, HPos.RIGHT); // Align the button to the right

        // Add labels to display the result and additional info
        pane.add(result, 2, 0, 2, 1); // Span the result label over 2 columns
        GridPane.setHalignment(result, HPos.LEFT); // Align result label to the left

        // Label for BMI category
        pane.add(bmicat, 2, 1, 2, 1);
        GridPane.setHalignment(bmicat, HPos.LEFT);

        // Label for BMI classification
        pane.add(bmiclass, 2, 2, 2, 1);
        GridPane.setHalignment(bmiclass, HPos.LEFT);

        // Label for health risk based on BMI
        risk.setMinWidth(250); // Set a minimum width
        pane.add(risk, 2, 3, 2, 1);
        GridPane.setHalignment(risk, HPos.LEFT);

        initializeRMI(); // Initialize RMI connection
        button.setOnAction(e -> getResult()); // Attach event handler to button

        // Create a scene with the GridPane layout and set the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("BMI Calculator"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public void getResult() {
        try {
            // Call the RMI method to calculate BMI
            double bmi = bmiServerInterface.calcBMI(
                    Double.parseDouble(weightfield.getText()),
                    Double.parseDouble(heightfield.getText())
            );
            result.setText(String.format("%s's BMI is: %.2f", namefield.getText(), bmi));

            // Fetch additional results from the RMI server
            ArrayList arrayList = bmiServerInterface.getResult(bmi);
            bmicat.setText(arrayList.get(0).toString()); // Display BMI category
            bmiclass.setText(arrayList.get(1).toString()); // Display BMI classification
            risk.setText("Risk : " + arrayList.get(2).toString()); // Display health risk
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
    }

    /** Initialize RMI */
    protected void initializeRMI() {
        String host = ""; // Host for the RMI registry

        try {
            Registry registry = LocateRegistry.getRegistry(host); // Connect to RMI registry
            bmiServerInterface = (BMIServerInterface)
                    registry.lookup("BMIServerInterfaceImpl"); // Look up the RMI object
            System.out.println("Server object " + bmiServerInterface + " found");
        } catch (Exception ex) {
            System.out.println(ex); // Print exception details
        }
    }
}
