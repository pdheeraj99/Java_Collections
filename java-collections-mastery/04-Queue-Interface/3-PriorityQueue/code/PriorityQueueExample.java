import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Task implements Comparable<Task> {
    private final String name;
    private final int priority; // 1 = High, 2 = Medium, 3 = Low

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task{name='" + name + "', priority=" + priority + '}';
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
}

public class PriorityQueueExample {

    public static void main(String[] args) {
        System.out.println("--- 1. PriorityQueue with Natural Ordering (Comparable) ---");
        demonstrateNaturalOrdering();

        System.out.println("\n--- 2. PriorityQueue with Custom Ordering (Comparator) ---");
        demonstrateCustomOrdering();
    }

    public static void demonstrateNaturalOrdering() {
        Queue<Task> taskQueue = new PriorityQueue<>();

        System.out.println("Offering tasks to the queue...");
        taskQueue.offer(new Task("Send weekly newsletter", 3));
        taskQueue.offer(new Task("Process payment", 1));
        taskQueue.offer(new Task("Handle user inquiry", 2));
        taskQueue.offer(new Task("Delete user account", 1));

        // Note: The internal state is a heap, not a sorted list. Order is not guaranteed here.
        System.out.println("Queue internal state (not guaranteed to be sorted): " + taskQueue);

        System.out.println("\nProcessing tasks from the queue...");
        while (!taskQueue.isEmpty()) {
            System.out.println("Polling -> " + taskQueue.poll());
            // Output 1: Polling -> Task{name='Process payment', priority=1}
            // Output 2: Polling -> Task{name='Delete user account', priority=1}
            // Output 3: Polling -> Task{name='Handle user inquiry', priority=2}
            // Output 4: Polling -> Task{name='Send weekly newsletter', priority=3}
        }
    }

    public static void demonstrateCustomOrdering() {
        Comparator<Task> reversePriorityComparator = (t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority());
        Queue<Task> taskQueue = new PriorityQueue<>(reversePriorityComparator);

        System.out.println("Offering tasks to the queue with REVERSE priority...");
        taskQueue.offer(new Task("Send weekly newsletter", 3)); // High
        taskQueue.offer(new Task("Process payment", 1));         // Low
        taskQueue.offer(new Task("Handle user inquiry", 2));      // Medium

        System.out.println("\nProcessing tasks from the queue...");
        while (!taskQueue.isEmpty()) {
            System.out.println("Polling -> " + taskQueue.poll());
            // Output 1: Polling -> Task{name='Send weekly newsletter', priority=3}
            // Output 2: Polling -> Task{name='Handle user inquiry', priority=2}
            // Output 3: Polling -> Task{name='Process payment', priority=1}
        }
    }
}
