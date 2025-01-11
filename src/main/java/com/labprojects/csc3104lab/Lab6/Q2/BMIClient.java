/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB6Q2
 */
package com.labprojects.csc3104lab.Lab6.Q2;

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

public class BMIClient extends Application {
    // IO streams
    DataOutputStream output = null;
    DataInputStream input = null;

    @Override
    public void start(Stage primaryStage) {
        // Create GridPane layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        // Add input fields
        pane.add(new Label("Name"), 0, 0);
        TextField nameField = new TextField();
        pane.add(nameField, 1, 0);
        nameField.setPromptText("Enter Name");

        pane.add(new Label("ID"), 0, 1);
        TextField idField = new TextField();
        pane.add(idField, 1, 1);
        idField.setPromptText("Enter ID");

        pane.add(new Label("Age"), 0, 2);
        TextField ageField = new TextField();
        pane.add(ageField, 1, 2);
        ageField.setPromptText("Enter Age");

        pane.add(new Label("Weight (Kg)"), 0, 3);
        TextField weightField = new TextField();
        pane.add(weightField, 1, 3);
        weightField.setPromptText("Enter Weight");

        pane.add(new Label("Height (m)"), 0, 4);
        TextField heightField = new TextField();
        pane.add(heightField, 1, 4);
        heightField.setPromptText("Enter Height");

        Button calculateButton = new Button("Calculate");
        pane.add(calculateButton, 1, 5);
        GridPane.setHalignment(calculateButton, HPos.RIGHT);

        Label result = new Label();
        pane.add(result, 2, 0, 2, 1);

        Label idLabel = new Label();
        pane.add(idLabel, 2, 1, 2, 1);

        Label ageLabel = new Label();
        pane.add(ageLabel, 2, 2, 2, 1);

        Label bmiCategory = new Label();
        pane.add(bmiCategory, 2, 3, 2, 1);

        Label bmiClassification = new Label();
        pane.add(bmiClassification, 2, 4, 2, 1);

        Label healthRisk = new Label();
        pane.add(healthRisk, 2, 5, 2, 1);

        calculateButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                String id = idField.getText();
                int age = Integer.parseInt(ageField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());

                // Send data to server
                output.writeUTF(name);
                output.writeUTF(id);
                output.writeInt(age);
                output.writeDouble(weight);
                output.writeDouble(height);
                output.flush();

                // Receive results from server
                result.setText(input.readUTF());
                idLabel.setText("ID Number: " + input.readUTF());
                ageLabel.setText("User's Age: " + input.readInt());
                bmiCategory.setText("Category: " + input.readUTF());
                bmiClassification.setText("Classification: " + input.readUTF());
                healthRisk.setText("Health Risk: " + input.readUTF());
            } catch (IOException ex) {
                System.err.println(ex);
            }
        });

        try {
            // Establish connection to server
            Socket socket = new Socket("localhost", 8000);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            result.setText("Error: Unable to connect to server.");
        }

        // Set scene
        Scene scene = new Scene(pane,400,200);
        primaryStage.setTitle("BMI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
