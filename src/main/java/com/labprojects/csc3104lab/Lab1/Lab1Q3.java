/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB1Q3
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

// Main class extending Application to create JavaFX application
public class Lab1Q3 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a GridPane layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER); // Set alignment to center
        pane.setHgap(5.5); // Set horizontal gap between nodes
        pane.setVgap(5.5); // Set vertical gap between nodes
        pane.setPadding(new Insets(11.5, 12.5, 11.5, 12.5)); // Set padding around the grid

        // Add label and text field for unit price input
        pane.add(new Label("Unit Price"), 0, 0);
        TextField unitprice = new TextField();
        pane.add(unitprice, 1, 0);

        // Add label and text field for quantity input
        pane.add(new Label("Quantity"), 0, 1);
        TextField qtt = new TextField();
        pane.add(qtt, 1, 1);

        // Create and add the "Calculate" button
        Button bttn = new Button("Calculate");
        pane.add(bttn, 1, 2);
        GridPane.setHalignment(bttn, HPos.RIGHT); // Align the button to the right

        // Create a label to display the result
        Label result = new Label();
        pane.add(result, 0, 3, 2, 1); // Span the result label over 2 columns
        GridPane.setHalignment(result, HPos.CENTER); // Align the result label to the center

        // Set action for the button when clicked
        bttn.setOnAction(e -> {
            try {
                // Parse unit price and quantity values from the text fields
                double price = Double.parseDouble(unitprice.getText());
                double quantity = Double.parseDouble(qtt.getText());

                // Calculate total price and display it in the result label
                double total = price * quantity;
                result.setText("Total Price is RM" + total);
            } catch (Exception exception) {
                // Handle any invalid input and display error message
                result.setText("Invalid Value");
            }
        });

        // Create a scene with the GridPane layout and set the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Product Calculator"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
