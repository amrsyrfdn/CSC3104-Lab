/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB3Q2
*/
package com.labprojects.csc3104lab.Lab3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.*;
import javafx.util.Duration;

import java.io.File;

public class Lab3Q2 extends Application {
    private String[] itemName = {"Roti", "Beras", "Minyak", "Telur", "Sayur"};
    private MediaView[] mediaViews = {
        new MediaView(new MediaPlayer(new Media(new File("C:\\Users\\amirh\\Downloads\\roti.mp4").toURI().toString()))),
        new MediaView(new MediaPlayer(new Media(new File("C:\\Users\\amirh\\Downloads\\beras.mp4").toURI().toString()))),
        new MediaView(new MediaPlayer(new Media(new File("C:\\Users\\amirh\\Downloads\\minyak.mp4").toURI().toString()))),
        new MediaView(new MediaPlayer(new Media(new File("C:\\Users\\amirh\\Downloads\\telur.mp4").toURI().toString()))),
        new MediaView(new MediaPlayer(new Media(new File("C:\\Users\\amirh\\Downloads\\sayur.mp4").toURI().toString()))),
    };
    private MediaView mediaView;
    @Override
    public void start (Stage primaryStage){
        // Create a ComboBox for a dropdown list
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(itemName));
        comboBox.setPromptText("Select a product");
        // Set the ComboBox to allow selection only (no free text input)
        comboBox.setEditable(false);

        // Create a GridPane layout to organize the nodes
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER); // Set alignment to center
        pane.setHgap(5.5); // Set horizontal gap between nodes
        pane.setVgap(5.5); // Set vertical gap between nodes
        pane.setPadding(new Insets(11.5, 12.5, 11.5, 12.5)); // Set padding around the grid


        // Add input field for product name
        pane.add(new Label("Product Name"), 0, 0);
        pane.add(comboBox, 1, 0);

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

        // Add MediaView to the layout to display videos
        mediaView = new MediaView();
        mediaView.setFitHeight(250);
        mediaView.setFitWidth(500);
        mediaView.setPreserveRatio(false);
        pane.add(mediaView, 0, 10, 2, 1); // Add the MediaView spanning two columns
        GridPane.setHalignment(mediaView, HPos.CENTER); // Center-align the MediaView

        // Set action for the button when clicked
        bttn.setOnAction(e -> {
            try {
                // Parse unit price and quantity from text fields
                double price = Double.parseDouble(unitprice.getText());
                double quantity = Double.parseDouble(qtt.getText());
                double newtotal;

                // Display product name and code
                nameresult.setText("Product : " + comboBox.getValue() + " (" + pcode.getText() + ")");
                ppu.setText("Price Per Unit : RM" + unitprice.getText());
                qttrslt.setText("Quantity : " + qtt.getText());

                if (comboBox.getValue().equals("Roti")){
                    mediaView.setMediaPlayer(mediaViews[0].getMediaPlayer());
                    mediaViews[0].getMediaPlayer().play();
                }
                else if (comboBox.getValue().equals("Beras")){
                    mediaView.setMediaPlayer(mediaViews[1].getMediaPlayer());
                    mediaViews[1].getMediaPlayer().play();
                }
                else if (comboBox.getValue().equals("Minyak")){
                    mediaView.setMediaPlayer(mediaViews[2].getMediaPlayer());
                    mediaViews[2].getMediaPlayer().play();
                }
                else if (comboBox.getValue().equals("Telur")){
                    mediaView.setMediaPlayer(mediaViews[3].getMediaPlayer());
                    mediaViews[3].getMediaPlayer().play();
                }
                else if (comboBox.getValue().equals("Sayur")){
                    mediaView.setMediaPlayer(mediaViews[4].getMediaPlayer());
                    mediaViews[4].getMediaPlayer().play();
                }

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

        // Create a scene with the GridPane layout and set the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Product Calculator"); // Set the stage title
        // Create a Timeline for blinking the title
        Timeline blinkTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> primaryStage.setTitle("")), new KeyFrame(Duration.seconds(1.0), e -> primaryStage.setTitle("BMI Calculator")));
        blinkTimeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        blinkTimeline.play(); // Start the blinking animation
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
