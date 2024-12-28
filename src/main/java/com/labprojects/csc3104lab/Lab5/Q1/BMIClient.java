/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB5Q1
 */
package com.labprojects.csc3104lab.Lab5.Q1;

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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


// Main class extending Application to create a JavaFX application
public class BMIClient extends Application {
    // IO streams
    DataOutputStream output = null;
    DataInputStream input = null;
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a GridPane layout for organizing UI elements
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER); // Align the grid pane to the center
        pane.setHgap(5.5); // Set horizontal gap between nodes
        pane.setVgap(5.5); // Set vertical gap between nodes
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5)); // Set padding around the grid

        // Add label and text field for name input
        pane.add(new Label("Name"), 0, 0);
        TextField namefield = new TextField();
        pane.add(namefield, 1, 0);

        // Add label and text field for weight input
        pane.add(new Label("Weight (Kg)"), 0, 1);
        TextField weightfield = new TextField();
        pane.add(weightfield, 1, 1);

        // Add label and text field for height input
        pane.add(new Label("Height (m)"), 0, 2);
        TextField heightfield = new TextField();
        pane.add(heightfield, 1, 2);

        // Create and add the "Calculate" button
        Button button = new Button("Calculate");
        pane.add(button, 1, 3);
        GridPane.setHalignment(button, HPos.RIGHT); // Align the button to the right

        // Create labels to display the result and additional info
        Label result = new Label();
        pane.add(result, 2, 0, 2, 1); // Span the result label over 2 columns
        GridPane.setHalignment(result, HPos.LEFT); // Align result label to the left

        Label bmicat = new Label(); // Label for BMI category
        pane.add(bmicat, 2, 1, 2, 1);
        GridPane.setHalignment(bmicat, HPos.LEFT);

        Label bmiclass = new Label(); // Label for BMI classification
        pane.add(bmiclass, 2, 2, 2, 1);
        GridPane.setHalignment(bmiclass, HPos.LEFT);

        Label risk = new Label(); // Label for health risk based on BMI
        risk.setMinWidth(250); // Set a minimum width
        pane.add(risk, 2, 3, 2, 1);
        GridPane.setHalignment(risk, HPos.LEFT);


        // Set action for the button when clicked
        button.setOnAction(e -> {
            try {
                String name = namefield.getText();
                double weight = Double.parseDouble(weightfield.getText().trim());
                double height = Double.parseDouble(heightfield.getText().trim());

                // Send the input to the server
                output.writeUTF(name);
                output.writeDouble(weight);
                output.writeDouble(height);
                output.flush();

                result.setText(input.readUTF());
                bmicat.setText(input.readUTF());
                bmiclass.setText(input.readUTF());
                risk.setText(input.readUTF());
            } catch (IOException exception) {
                System.err.println(exception);
            }
        });

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server
            input = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            result.setText(ex.toString() + '\n');
        }


        // Create a scene with the GridPane layout and set the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("BMI Calculator"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }
}
