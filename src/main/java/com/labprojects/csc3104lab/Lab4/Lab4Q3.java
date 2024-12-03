/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB4Q3
 */
package com.labprojects.csc3104lab.Lab4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lab4Q3 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage){
        // Create a new Pane to hold all the components
        Pane pane = new Pane();

        // Get the number of processors available in the machine
        int noOfProcessor = Runtime.getRuntime().availableProcessors();
        String nop = Integer.toString(noOfProcessor);

        // Create a label to display the number of processors available
        Label processorLabel = new Label("Number of processor : "+nop);
        processorLabel.setLayoutX(10); // Set position of the label
        processorLabel.setLayoutY(10); // Set position of the label
        pane.getChildren().add(processorLabel); // Add label to the pane

        // Create labels for displaying sequential and parallel calculation times
        Label seqText = new Label();
        seqText.setLayoutX(10); // Position of the sequential label
        seqText.setLayoutY(30); // Position of the sequential label
        pane.getChildren().add(seqText); // Add to the pane

        Label parText =  new Label();
        parText.setLayoutX(10); // Position of the parallel label
        parText.setLayoutY(50); // Position of the parallel label
        pane.getChildren().add(parText); // Add to the pane

        // Call matrixCalculator method to perform matrix multiplication and update labels
        matrixCalculator(seqText, parText);

        // Set up the scene with the layout and display the stage
        Scene scene = new Scene(pane, 400, 200); // Create a scene with the specified size
        primaryStage.setTitle("Matrix Calculation"); // Set stage title
        primaryStage.setScene(scene); // Add scene to stage
        primaryStage.show(); // Display the stage
    }

    // Method to perform matrix calculations and update labels with time taken for both methods
    private void matrixCalculator (Label seqText, Label parText){
        double[][] a = generateMatrix(2000, 2000);
        double[][] b = generateMatrix(2000, 2000);

        // Measure the time for sequential multiplication
        long seqStart = System.currentTimeMillis();
        sequentialMultiplyMatrix(a, b);
        long seqEnd = System.currentTimeMillis();
        long seqTime = seqEnd - seqStart;
        seqText.setText("Time taken for sequential multiplication is "+seqTime+" ms");

        // Measure the time for parallel multiplication
        long parStart = System.currentTimeMillis();
        parallelMultiplyMatrix(a, b);
        long parEnd = System.currentTimeMillis();
        long parTime = parEnd - parStart;
        parText.setText("Time taken for parallel multiplication is "+parTime+" ms");
    }

    // Method for sequential matrix multiplication (multiplying two matrices one row at a time)
    public static double [][] sequentialMultiplyMatrix(double [][] a, double [][] b){
        int rows = a.length; // Get the number of rows in matrix 'a'
        int cols = b[0].length; // Get the number of columns in matrix 'b'
        int common = b.length; // Number of columns in 'a' and number of rows in 'b'
        double [][] result = new double [rows][cols]; // Resultant matrix to store multiplication

        // Perform matrix multiplication
        for (int i = 0; i < rows; i++){
            final int currentRow = i; // Current row (declared to use in the lambda expression)
            for (int j = 0; j < cols; j++){
                for (int k = 0; k < common; k++){
                    result[currentRow][j] = result[currentRow][j] + a[currentRow][k] * b[k][j]; // Multiply and sum
                }
            }
        }
        return result; // Return the resultant matrix
    }

    // Method for parallel matrix multiplication (each row of the result is computed in parallel)
    public static double [][] parallelMultiplyMatrix(double [][] a, double [][] b){
        int rows = a.length; // Get the number of rows in matrix 'a'
        int cols = b[0].length; // Get the number of columns in matrix 'b'
        int common = b.length; // Number of columns in 'a' and number of rows in 'b'
        double [][] result = new double [rows][cols]; // Resultant matrix to store multiplication

        // Create thread pool with the number of available processors
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // For each row, assign a task to compute that row in parallel
        for (int i = 0; i < rows; i++){
            final int currentRow = i; // Current row (declared to use in the lambda expression)
            executor.execute(() -> { // Submit task to the executor
                for (int j = 0; j < cols; j++){
                    for (int k = 0; k < common; k++){
                        result[currentRow][j] = result[currentRow][j] + a[currentRow][k] * b[k][j]; // Multiply and sum
                    }
                }
            });
        }
        // Shutdown the executor and wait for all tasks to complete
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait until all tasks are completed
        }
        return result; // Return the resultant matrix
    }

    // Method to generate a random matrix with the given number of rows and columns
    public static double[][] generateMatrix(int rows, int cols){
        double[][] matrix = new double[rows][cols]; // Create the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Math.random(); // Assign a random value to each element
            }
        }
        return matrix; // Return the generated matrix
    }
}
