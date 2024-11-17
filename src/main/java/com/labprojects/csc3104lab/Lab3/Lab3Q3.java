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

public class Lab3Q3 extends Application{
    private int n = 0;
    private Pane pane;
    private final Date start = new Date();
    @Override
    public void start (Stage primaryStage){
        pane = new Pane();
        pane.setStyle("-fx-background-color: white;");

        Random random = new Random();
        Circle circle = new Circle(10);
        circle.setCenterX(random.nextDouble()* pane.getWidth());
        circle.setCenterY(random.nextDouble()* pane.getWidth());
        circle.setFill(Color.color(random.nextDouble(),random.nextDouble(),random.nextDouble()));
        pane.getChildren().add(circle);

        circle.setOnMouseClicked(e->{
            circle.setFill(Color.color(random.nextDouble(),random.nextDouble(),random.nextDouble()));
            circle.setCenterX(random.nextDouble()* pane.getWidth());
            circle.setCenterY(random.nextDouble()* pane.getWidth());
            n++;
            if (n==20){
                pane.getChildren().remove(circle);
                Date end = new Date();
                long timeSpent = end.getTime()- start.getTime();
                Label label = new Label("Time spent is "+timeSpent+" milliseconds");
                pane.getChildren().add(label);
            }
        });

        // Create a scene with the GridPane layout and set the stage
        Scene scene = new Scene(pane,400,400);
        primaryStage.setTitle("Circle Game"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }
}
