import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- 1. Demonstrating HashMap in a Concurrent Environment (will fail) ---");
        demonstrateConcurrencyProblem();

        System.out.println("\n--- 2. Demonstrating ConcurrentHashMap (the solution) ---");
        demonstrateConcurrentHashMap();
    }

    public static void demonstrateConcurrencyProblem() throws InterruptedException {
        Map<String, Integer> wordCountMap = new HashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("10 threads will try to increment a counter 10,000 times each.");

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    // This is a "race condition". It is not atomic.
                    // One thread can read the value, but before it can write the new value back,
                    // another thread reads the same old value. The first thread's work is lost.
                    Integer count = wordCountMap.get("counter");
                    if (count == null) {
                        wordCountMap.put("counter", 1);
                    } else {
                        wordCountMap.put("counter", count + 1);
                    }
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final count (HashMap): " + wordCountMap.get("counter")); // Example Output: Final count (HashMap): 98432
        System.out.println("Expected: 100000. Actual is likely less due to race conditions.");
    }

    public static void demonstrateConcurrentHashMap() throws InterruptedException {
        Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("10 threads will try to increment a counter 10,000 times each.");

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    // ConcurrentHashMap's methods like 'compute' are atomic and thread-safe.
                    // This whole operation locks only one part of the map, and only when needed.
                    wordCountMap.compute("counter", (key, value) -> (value == null) ? 1 : value + 1);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final count (ConcurrentHashMap): " + wordCountMap.get("counter")); // Output: Final count (ConcurrentHashMap): 100000
        System.out.println("Expected: 100000. Actual is correct because the map handles concurrency internally.");
    }
}
