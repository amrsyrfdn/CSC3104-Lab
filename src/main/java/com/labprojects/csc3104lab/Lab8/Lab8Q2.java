/* AMIR SYARIFUDDIN BIN HASBULLAH
   224300
   LAB8Q2
 */
package com.labprojects.csc3104lab.Lab8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the Queue class.
 * The tests validate the behavior of enqueue, dequeue, and the Queue's
 * full/empty state.
 */
public class Lab8Q2 {

    /**
     * Test if the queue is empty when initialized.
     */
    @Test
    void testIsEmptyInitially() {
        Queue queue = new Queue(); // Create a new Queue instance
        assertTrue(queue.isEmpty(), "Queue should be empty initially");
    }

    /**
     * Test if the queue is not full when initialized.
     */
    @Test
    void testIsFullInitially() {
        Queue queue = new Queue(); // Create a new Queue instance
        assertFalse(queue.isFull(), "Queue should not be full initially");
    }

    /**
     * Test enqueue operation to ensure the queue is not empty after adding an element.
     */
    @Test
    void testEnQueue() {
        Queue queue = new Queue();
        queue.enQueue(10); // Add an element to the queue
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
    }

    /**
     * Test dequeue operation to ensure it returns the first inserted element.
     */
    @Test
    void testDeQueue() {
        Queue queue = new Queue();
        queue.enQueue(10); // Add elements to the queue
        queue.enQueue(20);
        int element = queue.deQueue(); // Remove the first element
        assertEquals(10, element, "Dequeued element should be the first inserted element");
    }

    /**
     * Test if the queue becomes full after inserting the maximum allowed elements.
     */
    @Test
    void testQueueIsFull() {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5); // Insert 5 elements (assuming the queue capacity is 5)
        assertTrue(queue.isFull(), "Queue should be full after inserting 5 elements");
    }

    /**
     * Test overflow behavior when attempting to enqueue an element into a full queue.
     */
    @Test
    void testQueueOverflow() {
        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5); // Fill the queue
        queue.enQueue(6); // Attempt to add another element (should not be allowed)
        assertEquals(5, queue.rear + 1, "Rear index should remain 5 when queue overflows");
    }

    /**
     * Test underflow behavior when attempting to dequeue from an empty queue.
     */
    @Test
    void testQueueUnderflow() {
        Queue queue = new Queue(); // Create an empty queue
        int element = queue.deQueue(); // Attempt to dequeue
        assertEquals(-1, element, "Dequeuing from an empty queue should return -1");
    }

    /**
     * Test the combination of enqueue and dequeue operations to ensure proper behavior.
     */
    @Test
    void testEnQueueAndDeQueue() {
        Queue queue = new Queue();
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);

        // Dequeue elements and check their values
        assertEquals(10, queue.deQueue(), "First dequeued element should be 10");
        assertEquals(20, queue.deQueue(), "Second dequeued element should be 20");
        assertEquals(30, queue.deQueue(), "Third dequeued element should be 30");
        assertTrue(queue.isEmpty(), "Queue should be empty after all elements are dequeued");
    }
}
