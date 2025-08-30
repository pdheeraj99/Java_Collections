import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeExample {

    public static void main(String[] args) {
        System.out.println("--- ArrayDeque used as a Queue (First-In, First-Out) ---");
        useAsQueue();

        System.out.println("\n--- ArrayDeque used as a Stack (Last-In, First-Out) ---");
        useAsStack();
    }

    public static void useAsQueue() {
        Deque<String> taskQueue = new ArrayDeque<>();

        System.out.println("Offering tasks to the queue...");
        taskQueue.offer("Task 1: Process payment");
        taskQueue.offer("Task 2: Send email");
        taskQueue.offer("Task 3: Log event");

        System.out.println("Current task queue: " + taskQueue); // Output: Current task queue: [Task 1: Process payment, Task 2: Send email, Task 3: Log event]

        System.out.println("Next task to process: " + taskQueue.peek()); // Output: Next task to process: Task 1: Process payment

        while (!taskQueue.isEmpty()) {
            String task = taskQueue.poll();
            System.out.println("Processing and removing -> " + task);
            // Output 1: Processing and removing -> Task 1: Process payment
            // Output 2: Processing and removing -> Task 2: Send email
            // Output 3: Processing and removing -> Task 3: Log event
        }

        System.out.println("Final task queue: " + taskQueue); // Output: Final task queue: []
    }

    public static void useAsStack() {
        Deque<String> browserHistory = new ArrayDeque<>();

        System.out.println("Simulating browser navigation...");
        browserHistory.push("google.com");
        browserHistory.push("github.com");
        browserHistory.push("java-collections-mastery");

        System.out.println("Current browser history stack: " + browserHistory); // Output: Current browser history stack: [java-collections-mastery, github.com, google.com]

        System.out.println("Current page: " + browserHistory.peek()); // Output: Current page: java-collections-mastery

        System.out.println("\nSimulating 'Back' button clicks...");
        String previousPage1 = browserHistory.pop();
        System.out.println("Navigated back from: " + previousPage1); // Output: Navigated back from: java-collections-mastery
        System.out.println("Current page is now: " + browserHistory.peek()); // Output: Current page is now: github.com

        String previousPage2 = browserHistory.pop();
        System.out.println("Navigated back from: " + previousPage2); // Output: Navigated back from: github.com
        System.out.println("Current page is now: " + browserHistory.peek()); // Output: Current page is now: google.com

        System.out.println("Final browser history stack: " + browserHistory); // Output: Final browser history stack: [google.com]
    }
}
