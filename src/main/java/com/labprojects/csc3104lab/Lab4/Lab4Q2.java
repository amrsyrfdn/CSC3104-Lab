/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB4Q2
 */
package com.labprojects.csc3104lab.Lab4;

public class Lab4Q2 {
    // Shared variable to hold the total
    private static Integer total = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Without synchronization:");
        runThreads(false); // Run without synchronization

        System.out.println("\nWith synchronization:");
        runThreads(true); // Run with synchronization
    }

    private static void runThreads(boolean useSynchronization) throws InterruptedException {
        // Reset total to 0
        total = 0;

        // Create an array of threads
        Thread[] threads = new Thread[400];

        // Launch 400 threads
        for (int i = 0; i < 400; i++) {
            threads[i] = new Thread(() -> {
                if (useSynchronization) {
                    synchronized (total) { // Synchronized access
                        total += 20;
                    }
                } else {
                    total += 20; // Unsynchronized access
                }
            });
            threads[i].start(); // Start the thread
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Print the final total
        System.out.println("Final total: " + total);
    }
}
