/*AMIR SYARIFUDDIN BIN HASBULLAH
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

public class Lab1Q3 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.setPadding(new Insets(11.5, 12.5, 11.5, 12.5));

        pane.add(new Label("Unit Price"), 0, 0);
        TextField unitprice = new TextField();
        pane.add(unitprice, 1, 0);
        pane.add(new Label("Quantity"), 0, 1);
        TextField qtt = new TextField();
        pane.add(qtt, 1, 1);

        Button bttn = new Button("Calculate");
        pane.add(bttn, 1, 2);
        GridPane.setHalignment(bttn, HPos.RIGHT);

        Label result = new Label();
        pane.add(result, 0, 3, 2,1 );
        GridPane.setHalignment(result, HPos.CENTER);

        bttn.setOnAction(e -> {
            try {
                double price = Double.parseDouble(unitprice.getText());
                double quantity = Double.parseDouble(qtt.getText());
                double total = price * quantity;
                result.setText("Total Price is RM"+total);
            }
            catch(Exception exception){
                result.setText("Invalid Value");
            }

        });

        // Create a Scene with the gridPane and set the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Product Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
