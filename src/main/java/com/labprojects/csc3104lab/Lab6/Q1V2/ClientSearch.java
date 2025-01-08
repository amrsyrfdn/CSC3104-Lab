package com.labprojects.csc3104lab.Lab6.Q1V2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.rmi.Naming;

/**
 * ClientSearch class represents a JavaFX GUI application for searching student scores
 * via RMI (Remote Method Invocation).
 */
public class ClientSearch extends Application {

    /**
     * The main entry point for the JavaFX application.
     * Sets up the GUI components and handles user interactions.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create a TextField for user input (Student's Name)
        TextField namefield = new TextField();
        namefield.setPromptText("Enter Student's Name"); // Placeholder text for user guidance

        // Create a Label to display the student's score or status
        Label scorefield = new Label();
        scorefield.setText("Student's Score will appear here");

        // Create a Button for triggering the search
        Button bttn = new Button("Search");

        // Arrange components vertically with padding
        VBox pane = new VBox(namefield, scorefield, bttn);
        pane.setPadding(new Insets(30));
        pane.setSpacing(10); // Adds spacing between components for better layout

        /**
         * Set up the button's action handler.
         * When the button is clicked, it retrieves the student's score from the remote server.
         */
        bttn.setOnAction(e -> {
            try {
                String name = namefield.getText(); // Get input from the TextField

                // Lookup the remote RMI server object
                StudentServerInterface server = (StudentServerInterface) Naming.lookup("rmi://localhost:1101/Student3TierImpl");

                // Call the remote method to find the student's score
                double score = server.findScore(name);

                // Update the Label based on the returned score
                if (score == -1.0) {
                    scorefield.setText("This user does not allow their score to be published");
                } else {
                    scorefield.setText(name + "'s Score: " + score);
                }
            } catch (Exception ex) {
                // Handle remote or network errors
                scorefield.setText("Error: Unable to retrieve score. Check server connection.");
                ex.printStackTrace();
            }
        });

        // Set up the Scene and Stage
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Student Score Search"); // Set window title
        primaryStage.setScene(scene); // Attach scene to stage
        primaryStage.show(); // Display the GUI
    }
}
