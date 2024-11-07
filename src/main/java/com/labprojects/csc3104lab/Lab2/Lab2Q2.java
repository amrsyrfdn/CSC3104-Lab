/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB1Q3
 */
package com.labprojects.csc3104lab.Lab2;

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

// Main class extending Application to create a JavaFX application
public class Lab2Q2 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a GridPane layout to organize the nodes
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER); // Set alignment to center
        pane.setHgap(5.5); // Set horizontal gap between nodes
        pane.setVgap(5.5); // Set vertical gap between nodes
        pane.setPadding(new Insets(11.5, 12.5, 11.5, 12.5)); // Set padding around the grid

        // Add input field for product name
        pane.add(new Label("Product Name"), 0, 0);
        TextField pname = new TextField();
        pane.add(pname, 1, 0);

        // Add input field for product code
        pane.add(new Label("Product Code"), 0, 1);
        TextField pcode = new TextField();
        pane.add(pcode, 1, 1);

        // Add input field for unit price
        pane.add(new Label("Unit Price"), 0, 2);
        TextField unitprice = new TextField();
        pane.add(unitprice, 1, 2);

        // Add input field for quantity
        pane.add(new Label("Quantity"), 0, 3);
        TextField qtt = new TextField();
        pane.add(qtt, 1, 3);

        // Add "Calculate" button and align it to the left
        Button bttn = new Button("Calculate");
        pane.add(bttn, 1, 4);
        GridPane.setHalignment(bttn, HPos.LEFT);

        // Add labels to display calculated results and align them to the center
        Label nameresult = new Label();
        pane.add(nameresult, 0, 5, 2, 1); // Span result label across 2 columns
        GridPane.setHalignment(nameresult, HPos.CENTER);

        Label ppu = new Label();
        pane.add(ppu, 0, 6, 2, 1);
        GridPane.setHalignment(ppu, HPos.CENTER);

        Label qttrslt = new Label();
        pane.add(qttrslt, 0, 7, 2, 1);
        GridPane.setHalignment(qttrslt, HPos.CENTER);

        Label discount = new Label();
        pane.add(discount, 0, 8, 2, 1);
        GridPane.setHalignment(discount, HPos.CENTER);

        Label totalrslt = new Label();
        totalrslt.setMinWidth(265); // Set a minimum width
        pane.add(totalrslt, 0, 9, 2, 1);
        GridPane.setHalignment(totalrslt, HPos.CENTER);

        // Set action for the button when clicked
        bttn.setOnAction(e -> {
            try {
                // Parse unit price and quantity from text fields
                double price = Double.parseDouble(unitprice.getText());
                double quantity = Double.parseDouble(qtt.getText());
                double newtotal;

                // Display product name and code
                nameresult.setText("Product : " + pname.getText() + " (" + pcode.getText() + ")");
                ppu.setText("Price Per Unit : RM" + unitprice.getText());
                qttrslt.setText("Quantity : " + qtt.getText());

                // Calculate total price based on unit price and quantity
                double total = price * quantity;

                // Apply discount based on total price and display the result
                if (total <= 500) {
                    newtotal = total * 0.97;
                    discount.setText("Discount : 3%");
                    totalrslt.setText("Total : RM" + total + "\t Discounted Total : RM" + newtotal);
                } else if (total > 500.01 && total <= 5000) {
                    newtotal = total * 0.925;
                    discount.setText("Discount : 7.5%");
                    totalrslt.setText("Total : RM" + total + "\t Discounted Total : RM" + newtotal);
                } else if (total > 5000.01 && total <= 10000) {
                    newtotal = total * 0.90;
                    discount.setText("Discount : 10%");
                    totalrslt.setText("Total : RM" + total + "\t Discounted Total : RM" + newtotal);
                } else if (total > 10000.01 && total <= 15000) {
                    newtotal = total * 0.85;
                    discount.setText("Discount : 15%");
                    totalrslt.setText("Total : RM" + total + "\t Discounted Total : RM" + newtotal);
                } else {
                    newtotal = total; // No discount for totals above 15000
                    discount.setText("No Discount");
                    totalrslt.setText("Total : RM" + total);
                }
            } catch (Exception exception) {
                // Handle invalid input and display error message
                nameresult.setText("Invalid Value");
            }
        });

        // Set up the scene with the layout and display the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Product Calculator"); // Set stage title
        primaryStage.setScene(scene); // Add scene to stage
        primaryStage.show(); // Display the stage
    }
}
