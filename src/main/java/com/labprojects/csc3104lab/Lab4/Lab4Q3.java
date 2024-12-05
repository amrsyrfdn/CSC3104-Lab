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
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Lab4Q3 extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a new Pane to hold all the components
        Pane pane = new Pane();

        // Get the number of processors available in the machine
        int noOfProcessor = Runtime.getRuntime().availableProcessors();
        String nop = Integer.toString(noOfProcessor);

        // Create a label to display the number of processors available
        Label processorLabel = new Label("Number of processor : " + nop);
        processorLabel.setLayoutX(10);
        processorLabel.setLayoutY(10);
        pane.getChildren().add(processorLabel);

        // Create labels for displaying sequential and parallel calculation times
        Label seqText = new Label();
        seqText.setLayoutX(10);
        seqText.setLayoutY(30);
        pane.getChildren().add(seqText);

        Label parText = new Label();
        parText.setLayoutX(10);
        parText.setLayoutY(50);
        pane.getChildren().add(parText);

        // Call matrixCalculator method to perform matrix multiplication and update labels
        matrixCalculator(seqText, parText);

        // Set up the scene with the layout and display the stage
        Scene scene = new Scene(pane, 400, 200);
        primaryStage.setTitle("Matrix Calculation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void matrixCalculator(Label seqText, Label parText) {
        double[][] a = generateMatrix(1000, 1000);
        double[][] b = generateMatrix(1000, 1000);

        // Measure the time for sequential multiplication
        long seqStart = System.currentTimeMillis();
        sequentialMultiplyMatrix(a, b);
        long seqEnd = System.currentTimeMillis();
        long seqTime = seqEnd - seqStart;
        seqText.setText("Time taken for sequential multiplication is " + seqTime + " ms");

        // Measure the time for parallel multiplication
        long parStart = System.currentTimeMillis();
        parallelMultiplyMatrix(a, b);
        long parEnd = System.currentTimeMillis();
        long parTime = parEnd - parStart;
        parText.setText("Time taken for parallel multiplication is " + parTime + " ms");
    }

    public static double[][] sequentialMultiplyMatrix(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int common = b.length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        double[][] result = new double[rows][cols];

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new RecursiveAction() {
            private static final int THRESHOLD = 100; // Threshold for dividing tasks

            @Override
            protected void compute() {
                parallelMultiply(a, b, result, 0, rows);
            }

            private void parallelMultiply(double[][] a, double[][] b, double[][] result, int rowStart, int rowEnd) {
                if (rowEnd - rowStart <= THRESHOLD) {
                    // Base case: Perform multiplication directly for this range of rows
                    for (int i = rowStart; i < rowEnd; i++) {
                        for (int j = 0; j < cols; j++) {
                            for (int k = 0; k < b.length; k++) {
                                result[i][j] += a[i][k] * b[k][j];
                            }
                        }
                    }
                } else {
                    // Split the task into two subtasks
                    int mid = (rowStart + rowEnd) / 2;
                    invokeAll(
                            new RecursiveAction() {
                                @Override
                                protected void compute() {
                                    parallelMultiply(a, b, result, rowStart, mid);
                                }
                            },
                            new RecursiveAction() {
                                @Override
                                protected void compute() {
                                    parallelMultiply(a, b, result, mid, rowEnd);
                                }
                            }
                    );
                }
            }
        });

        return result;
    }

    public static double[][] generateMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Math.random();
            }
        }
        return matrix;
    }
}
