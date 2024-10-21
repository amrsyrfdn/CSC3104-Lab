/*AMIR SYARIFUDDIN BIN HASBULLAH
  224300
  LAB1Q2
 */
package com.labprojects.csc3104lab.Lab1;

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

public class  Lab1Q2 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        pane.add(new Label("Name"), 0, 0);
        TextField namefield = new TextField();
        pane.add(namefield, 1, 0);
        pane.add(new Label("Weight (Kg)"), 0, 1);
        TextField weightfield = new TextField();
        pane.add(weightfield, 1, 1);
        pane.add(new Label("Height (m)"), 0, 2);
        TextField heightfield = new TextField ();
        pane.add(heightfield, 1, 2);

        Button button = new Button("Calculate");
        pane.add(button, 1, 3);
        GridPane.setHalignment(button, HPos.RIGHT);

        Label result = new Label ();
        pane.add(result, 0,3,2,1);
        GridPane.setHalignment(result, HPos.LEFT);

        button.setOnAction(e ->{
            try{
                double weight = Double.parseDouble(weightfield.getText());
                double height = Double.parseDouble(heightfield.getText());
                double bmi = weight/(height*height);
                result.setText(String.format(namefield.getText()+"'s BMI : %.2f",bmi));
            } catch (Exception ex) {
                result.setText("Invalid Input");
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("BMI Calculator"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}