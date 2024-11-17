package com.labprojects.csc3104lab.BuddyTesting;

// NURUL WAHIDA BINTI MOHD JISMANI
// 225585
// QUESTION 2 LAB 2

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Lab2Q2 extends Application {

    private TextField codeField, nameField, priceField, quantityField;
    private Label totalSaleLabel, discountLabel, finalPriceLabel;
    private Label productCodeOutput, nameOutput, unitPriceOutput, quantityOutput;

    @Override
    public void start(Stage primaryStage) {
        // Set up the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Create Labels and TextFields for user inputs
        // Product code
        Label codeInput = new Label("Product Code: ");
        codeField = new TextField(); // Use instance variable instead of local declaration
        gridPane.add(codeInput, 0, 0);
        gridPane.add(codeField, 1, 0);

        // Product name
        Label nameInput = new Label("Name: ");
        nameField = new TextField(); // Use instance variable instead of local declaration
        gridPane.add(nameInput, 0, 1);
        gridPane.add(nameField, 1, 1);

        // Price per unit
        Label priceInput = new Label("Unit Price:");
        priceField = new TextField(); // Use instance variable instead of local declaration
        gridPane.add(priceInput, 0, 2);
        gridPane.add(priceField, 1, 2);

        // Quantity for product sold
        Label quantityInput = new Label("Quantity:");
        quantityField = new TextField(); // Use instance variable instead of local declaration
        gridPane.add(quantityInput, 0, 3);
        gridPane.add(quantityField, 1, 3);

        // Label to display the result
        productCodeOutput = new Label("Product Code: ");
        gridPane.add(productCodeOutput, 0, 5, 2, 1);

        nameOutput = new Label("Product Name: ");
        gridPane.add(nameOutput, 0, 6, 2, 1);

        unitPriceOutput = new Label("Price per unit: ");
        gridPane.add(unitPriceOutput, 0, 7, 2, 1);

        quantityOutput = new Label("Quantity: ");
        gridPane.add(quantityOutput, 0, 8, 2, 1);

        totalSaleLabel = new Label("Total Sale: ");
        gridPane.add(totalSaleLabel, 0, 9, 2, 1);

        discountLabel = new Label("Discount: ");
        gridPane.add(discountLabel, 0, 10, 2, 1);

        finalPriceLabel = new Label("Final Price: ");
        gridPane.add(finalPriceLabel, 0, 11, 2, 1); // Added final price label to gridPane

        // Button to calculate total sale
        Button calculateButton = new Button("Calculate Total Sale");
        gridPane.add(calculateButton, 1, 4);
        GridPane.setHalignment(calculateButton, HPos.RIGHT);

        // Set up button action
        calculateButton.setOnAction(e -> calculateTotal());

        // Set up scene
        Scene scene = new Scene(gridPane, 500, 500);
        primaryStage.setTitle("Sales Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void calculateTotal() {
        try {
            // Get input values
            String productCode = codeField.getText();
            String nameInput = nameField.getText();
            double priceInput = Double.parseDouble(priceField.getText());
            int quantityInput = Integer.parseInt(quantityField.getText());

            // Calculate total sale
            double totalSale = priceInput * quantityInput;
            double discountRate = getDiscountRate(totalSale);
            double discount = totalSale * discountRate;
            double finalPrice = totalSale - discount;

            // Display result
            productCodeOutput.setText(String.format("Product Code: %s", productCode));
            nameOutput.setText(String.format("Name: %s", nameInput));
            unitPriceOutput.setText(String.format("Unit Price: RM%.2f", priceInput));
            quantityOutput.setText(String.format("Quantity: %d", quantityInput));
            totalSaleLabel.setText(String.format("Total Sale: RM%.2f", totalSale));
            discountLabel.setText(String.format("Discount: RM%.2f", discount));
            finalPriceLabel.setText(String.format("Final Price: RM%.2f", finalPrice));

        } catch (NumberFormatException ex) {
            totalSaleLabel.setText("Please enter valid numbers for price and quantity.");
            discountLabel.setText("");
            finalPriceLabel.setText("");
        }
    }

    private double getDiscountRate(double totalSale) {
        if (totalSale <= 500) {
            return 0.03;
        } else if (totalSale <= 5000) {
            return 0.075;
        } else if (totalSale <= 10000) {
            return 0.10;
        } else if (totalSale <= 15000) {
            return 0.15;
        } else {
            return 0.0; // no discount for sales above 15000
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
