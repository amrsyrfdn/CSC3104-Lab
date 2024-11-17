/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB3Q3
*/
package com.labprojects.csc3104lab.Lab3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.Date;
import java.util.Random;

public class Lab3Q3 extends Application {
    private int n = 0; // Counter to track the number of clicks
    private Pane pane; // Pane to hold all elements in the scene
    private final Date start = new Date(); // Track the start time of the game

    @Override
    public void start(Stage primaryStage) {
        // Initialize the pane and set its background color
        pane = new Pane();
        pane.setStyle("-fx-background-color: white;");

        // Create a Random object to generate random values
        Random random = new Random();

        // Create a circle with an initial random position and color
        Circle circle = new Circle(10); // Circle with radius 10
        circle.setCenterX(random.nextDouble() * pane.getWidth()); // Random X-coordinate
        circle.setCenterY(random.nextDouble() * pane.getWidth()); // Random Y-coordinate
        circle.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble())); // Random color
        pane.getChildren().add(circle); // Add the circle to the pane

        // Set an event handler for mouse clicks on the circle
        circle.setOnMouseClicked(e -> {
            // Change the circle's color to a new random color
            circle.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));

            // Move the circle to a new random position
            circle.setCenterX(random.nextDouble() * pane.getWidth());
            circle.setCenterY(random.nextDouble() * pane.getWidth());

            // Increment the click counter
            n++;

            // If the circle is clicked 20 times, end the game
            if (n == 20) {
                // Remove the circle from the pane
                pane.getChildren().remove(circle);

                // Calculate the time spent playing the game
                Date end = new Date();
                long timeSpent = end.getTime() - start.getTime();

                // Display a label with the time spent in milliseconds
                Label label = new Label("Time spent is " + timeSpent + " milliseconds");
                pane.getChildren().add(label); // Add the label to the pane
            }
        });

        // Create a scene with the pane and set the stage
        Scene scene = new Scene(pane, 400, 400); // Scene size: 400x400 pixels
        primaryStage.setTitle("Circle Game"); // Set the title of the window
        primaryStage.setScene(scene); // Attach the scene to the stage
        primaryStage.show(); // Display the stage
    }
}
