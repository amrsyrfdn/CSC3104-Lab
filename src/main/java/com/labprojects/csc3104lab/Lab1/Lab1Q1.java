/*AMIR SYARIFUDDIN BIN HASBULLAH
  224300
  LAB1Q1
  this code is using button instead of textfield
 */
package com.labprojects.csc3104lab.Lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class Lab1Q1 extends Application{
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        Random rand = new Random();
        for (int x=0; x<10; x++){
            for (int y=0; y<8; y++){
                int randomNumber = rand.nextInt(9) + 2;
                Button button = new Button(String.valueOf(randomNumber));
                // Set the size of the button
                button.setPrefSize(50, 50);

                // Center the text
                button.setStyle("-fx-alignment: center;");

                // Add the Button to the GridPane
                pane.add(button, x, y);
            }
        }

        // Create a Scene with the gridPane and set the stage
        Scene scene = new Scene(pane, 400, 500);
        primaryStage.setTitle("10x8 Matrix with Buttons");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
