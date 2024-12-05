/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB4Q1
   This class works with the 'ThreadPoolExecutorDemo' Class
*/
package com.labprojects.csc3104lab.Lab4.Q1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskThreadDemo extends Application {
    private static int n; // Number of iterations for tasks
    private static String s; // String to be printed

    // Main method to initialize input values and launch the JavaFX application
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many Iterations for Print String and Random Number : ");
        n = sc.nextInt(); // Input number of iterations for tasks
        sc.nextLine(); // Consume the newline character
        System.out.println("Specify the String to be printed : ");
        s = sc.nextLine(); // Input string to be printed

        launch(args); // Launch JavaFX application
    }

    // This method is invoked by JavaFX to start the application and display the UI
    public void start(Stage primaryStage) {
        // Create a TextArea to display the output of the tasks
        TextArea txtarea = new TextArea();
        txtarea.setEditable(false); // Make the TextArea non-editable
        Pane pane = new Pane(); // Create a pane to hold the TextArea
        pane.getChildren().add(txtarea); // Add the TextArea to the pane

        // Set up the scene with the created pane and set the primary stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Concurrent Output with Thread Pool");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Use the new ThreadPoolExecutorDemo class to manage tasks
        ThreadPoolExecutorDemo threadPoolExecutorDemo = new ThreadPoolExecutorDemo(n, s, txtarea);

        // Execute tasks through the thread pool
        threadPoolExecutorDemo.executeTasks();
    }

    // The class for managing the thread pool and executing tasks
    public static class ThreadPoolExecutorDemo {
        private int n; // Number of iterations for tasks
        private String s; // String to be printed
        private TextArea txtarea; // TextArea to display output

        // Constructor to initialize the necessary parameters for task execution
        public ThreadPoolExecutorDemo(int n, String s, TextArea txtarea) {
            this.n = n;
            this.s = s;
            this.txtarea = txtarea;
        }

        // Method to execute tasks using a thread pool
        public void executeTasks() {
            // Create a fixed thread pool with 2 threads
            ExecutorService threadPool = Executors.newFixedThreadPool(2);

            // Create a list to hold 100 tasks
            List<Runnable> taskList = new ArrayList<>();

            Random random = new Random();
            // Add 100 tasks to the task list
            for (int i = 0; i < 100; i++) {
                int taskType = random.nextInt(4); // Randomly pick a task type (0 to 3)

                // Based on the random task type, create and add tasks to the list
                if (taskType == 0) {
                    taskList.add(new PrintChar('x', n, txtarea)); // Add PrintChar task
                } else if (taskType == 1) {
                    taskList.add(new PrintNum(n, txtarea)); // Add PrintNum task
                } else if (taskType == 2) {
                    taskList.add(new PrintString(s, n, txtarea)); // Add PrintString task
                } else {
                    taskList.add(new PrintRandom(n, txtarea)); // Add PrintRandom task
                }
            }

            // Submit all tasks to the thread pool for execution
            for (Runnable task : taskList) {
                threadPool.submit(task);
            }

            // Shutdown the thread pool after all tasks are completed
            threadPool.shutdown();
        }
    }

    // Inner class for printing a specified character multiple times
    public static class PrintChar implements Runnable {
        private char charToPrint;
        private int times;
        private TextArea txtarea;

        public PrintChar(char c, int t, TextArea txtarea) {
            charToPrint = c;
            times = t;
            this.txtarea = txtarea;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                final String output = Character.toString(charToPrint);
                appendToTextArea(output);
            }
        }

        private void appendToTextArea(String text) {
            Platform.runLater(() -> txtarea.appendText(text));
        }
    }

    // Inner class for printing numbers from 1 to n
    public static class PrintNum implements Runnable {
        private int lastNum;
        private TextArea txtarea;

        public PrintNum(int n, TextArea txtarea) {
            lastNum = n;
            this.txtarea = txtarea;
        }

        @Override
        public void run() {
            for (int i = 1; i <= lastNum; i++) {
                final String output = Integer.toString(i);
                appendToTextArea(output);
            }
        }

        private void appendToTextArea(String text) {
            Platform.runLater(() -> txtarea.appendText(text));
        }
    }

    // Inner class for printing a specified string multiple times
    public static class PrintString implements Runnable {
        private String string;
        private int noOfTimes;
        private TextArea txtarea;

        public PrintString(String s, int noOfTimes, TextArea txtarea) {
            string = s;
            this.noOfTimes = noOfTimes;
            this.txtarea = txtarea;
        }

        @Override
        public void run() {
            for (int i = 1; i <= noOfTimes; i++) {
                appendToTextArea(string);
            }
        }

        private void appendToTextArea(String text) {
            Platform.runLater(() -> txtarea.appendText(text));
        }
    }

    // Inner class for printing a random number within a range multiple times
    public static class PrintRandom implements Runnable {
        private int noOfTimes;
        private TextArea txtarea;

        public PrintRandom(int noOfTimes, TextArea txtarea) {
            this.noOfTimes = noOfTimes;
            this.txtarea = txtarea;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 1; i <= noOfTimes; i++) {
                int randomNo = random.nextInt(50) + 1;
                final String output = "<" + randomNo + ">";
                appendToTextArea(output);
            }
        }

        private void appendToTextArea(String text) {
            Platform.runLater(() -> txtarea.appendText(text));
        }
    }
}
