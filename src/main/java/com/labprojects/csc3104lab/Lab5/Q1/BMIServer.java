/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB5Q1
 */
package com.labprojects.csc3104lab.Lab5.Q1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class BMIServer extends Application {
    @Override // Override the start method in the Application class
    public void start (Stage primaryStage){
        // Pane to output server info
        GridPane pane= new GridPane();
        // Text area for displaying contents
        TextArea ta = new TextArea();
        ta.setPrefWidth(400); // Set preferred width
        ta.setPrefHeight(50); // Set preferred height
        ta.setEditable(false);
        Label in1 = new Label();
        Label in2 = new Label();
        Label in3 = new Label();

        pane.add(ta,0,0);
        pane.add(in1,0,1);
        pane.add(in2,0,2);
        pane.add(in3,0,3);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        new Thread( () -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                ta.appendText("Server started at " + new Date() + '\n');

                while (true){
                    // Listen for a connection request
                    Socket socket = serverSocket.accept();
                    // Create data input and output streams
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    // Read input data from the client
                    String name = input.readUTF();
                    double weight = input.readDouble();
                    double height = input.readDouble();

                    // Update the labels on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        in1.setText("Input received: " + name);
                        in2.setText("Input received: " + weight);
                        in3.setText("Input received: " + height);
                    });

                    // Calculate BMI
                    double bmi = weight / (height * height);
                    String category, classification, risk;

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

                    // Send results back to the client
                    output.writeUTF(String.format("%s's BMI is: %.2f", name, bmi));
                    output.writeUTF(category);
                    output.writeUTF(classification);
                    output.writeUTF(risk);
                    System.out.println("BMI results sent to client.");

                    // Close the connection
                    socket.close();
                }
            }
            catch (IOException e){
                    e.printStackTrace();
            }
        }).start();
    }
}
