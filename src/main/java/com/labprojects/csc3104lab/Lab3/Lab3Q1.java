/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB3Q1
*/
package com.labprojects.csc3104lab.Lab3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;


// Main class extending Application to create a JavaFX application
public class Lab3Q1 extends Application {

    private  MediaPlayer mediaPlayer;
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        String mediapath0 = new File("C:\\Users\\amirh\\Downloads\\6592716-uhd_3840_2160_25fps.mp4").toURI().toString();
        Media media = new Media(mediapath0);
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
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

        pane.add(mediaView, 0, 5, 3, 1); // Place MediaView at the bottom, spanning multiple columns
        mediaView.setFitWidth(250); // Adjust the width as needed
        mediaView.setFitHeight(250); // Adjust the height as needed
        mediaView.setPreserveRatio(false);
        mediaPlayer.play(); //play the media

        // Set action for the button when clicked
        button.setOnAction(e -> {
            try {
                mediaPlayer.stop();
                String newMediapath=" ";
                // Parse weight and height from the text fields
                double weight = Double.parseDouble(weightfield.getText());
                double height = Double.parseDouble(heightfield.getText());

                // Calculate BMI and display it in the result label
                double bmi = weight / (height * height);
                result.setText(String.format(namefield.getText() + "'s BMI : %.2f", bmi));

                // Determine BMI category, classification, and risk level
                if (bmi < 18.5) {
                    bmicat.setText("Category  : < 18.5");
                    bmiclass.setText("Underweight");
                    risk.setText("Risk of developing heart problems : Increased");
                    newMediapath = new File("C:\\Users\\amirh\\Downloads\\7987315-uhd_2160_3840_30fps.mp4").toURI().toString();
                } else if (bmi >= 18.5 && bmi <= 24.9) {
                    bmicat.setText("Category  : 18.5 - 24.9");
                    bmiclass.setText("Normal Weight");
                    risk.setText("Risk of developing heart problems : Least");
                    newMediapath = new File("C:\\Users\\amirh\\Downloads\\3125907-uhd_3840_2160_25fps.mp4").toURI().toString();
                } else if (bmi >= 25.0 && bmi <= 29.9) {
                    bmicat.setText("Category  : 25.0 - 29.9");
                    bmiclass.setText("Overweight");
                    risk.setText("Risk of developing heart problems : Increased");
                    newMediapath = new File("C:\\Users\\amirh\\Downloads\\5726654-uhd_4096_2160_25fps.mp4").toURI().toString();
                } else if (bmi >= 30.0 && bmi <= 34.9) {
                    bmicat.setText("Category  : 30.0 - 34.9");
                    bmiclass.setText("Obese class I");
                    risk.setText("Risk of developing heart problems : High");
                    newMediapath = new File("C:\\Users\\amirh\\Downloads\\5726654-uhd_4096_2160_25fps.mp4").toURI().toString();
                } else if (bmi >= 35.0 && bmi <= 39.9) {
                    bmicat.setText("Category  : 35.0 - 39.9");
                    bmiclass.setText("Obese class II");
                    risk.setText("Risk of developing heart problems : Very High");
                    newMediapath = new File("C:\\Users\\amirh\\Downloads\\5726654-uhd_4096_2160_25fps.mp4").toURI().toString();
                } else if (bmi >= 40.0) {
                    bmicat.setText("Category : >= 40.0");
                    bmiclass.setText("Obese class III");
                    risk.setText("Risk of developing heart problems : Extremely High");
                    newMediapath = new File("C:\\Users\\amirh\\Downloads\\5726654-uhd_4096_2160_25fps.mp4").toURI().toString();
                }

                Media newMedia = new Media(newMediapath);
                mediaPlayer = new MediaPlayer(newMedia);
                mediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();

            } catch (Exception ex) {
                // Handle any invalid input and display an error message
                result.setText("Invalid Input");
            }
        });

        // Create a scene with the GridPane layout and set the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("BMI Calculator"); // Set the stage title
        // Create a Timeline for blinking the title
        Timeline blinkTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> primaryStage.setTitle("")), new KeyFrame(Duration.seconds(1.0), e -> primaryStage.setTitle("BMI Calculator")));
        blinkTimeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        blinkTimeline.play(); // Start the blinking animation
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }
}
