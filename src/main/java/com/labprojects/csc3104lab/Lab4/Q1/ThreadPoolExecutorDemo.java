/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB4Q1
   This class works with the 'TaskThreadDemo' Class
*/
package com.labprojects.csc3104lab.Lab4.Q1;

import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorDemo {
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
                taskList.add(new TaskThreadDemo.PrintChar('x', n, txtarea)); // Add PrintChar task
            } else if (taskType == 1) {
                taskList.add(new TaskThreadDemo.PrintNum(n, txtarea)); // Add PrintNum task
            } else if (taskType == 2) {
                taskList.add(new TaskThreadDemo.PrintString(s, n, txtarea)); // Add PrintString task
            } else {
                taskList.add(new TaskThreadDemo.PrintRandom(n, txtarea)); // Add PrintRandom task
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
