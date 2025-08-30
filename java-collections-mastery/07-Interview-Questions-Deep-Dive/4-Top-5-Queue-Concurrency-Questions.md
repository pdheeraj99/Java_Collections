# 🏅 Interview Questions Part 4: Queue & Concurrency

Mawa, `Queue` and concurrency-related questions tho mana collections knowledge ni test chestaru. Ee section lo, aa advanced topics meeda focus cheddam.

---

### 1. How would you implement a Stack using a `Deque`? Why is this preferred?

**Answer:**

Modern Java lo `Stack` implement cheyadaniki `Deque` interface, specifically `ArrayDeque`, is the best choice. The legacy `Stack` class should never be used.

`Deque` anedi "Double Ended Queue", so daaniki rendu vypula add/remove chese methods untayi. Manam kevalam oka vypu (`head`) matrame use cheste, adi perfect `Stack` (LIFO) la pani chestundi.

**The Code:**

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class DequeAsStack {
    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();

        // Pushing elements (adds to the front)
        stack.push("A"); // same as addFirst()
        stack.push("B");
        stack.push("C");
        System.out.println("Stack: " + stack); // [C, B, A]

        // Peeking at the top element
        String top = stack.peek(); // same as peekFirst()
        System.out.println("Top element: " + top); // C

        // Popping elements (removes from the front)
        String removed = stack.pop(); // same as removeFirst()
        System.out.println("Popped: " + removed); // C
        System.out.println("Stack after pop: " + stack); // [B, A]
    }
}
```

**Why is it preferred over the legacy `Stack` class?**
1.  **Clean Design:** `ArrayDeque` implements the `Deque` interface, which is a clean, focused API. The legacy `Stack` class `extends Vector`, which is a huge design flaw. It inherits unnecessary and dangerous methods like `get(index)` that break the LIFO principle.
2.  **Performance:** `ArrayDeque` is much faster. It's not synchronized, unlike `Vector` (and therefore `Stack`), so there's no locking overhead in single-threaded use.
3.  **Flexibility:** `ArrayDeque` can be used as both a Stack and a Queue, making it more versatile.

---

### 2. *Design Question:* You need to design a task scheduler where some tasks have higher priority. Which collection would you use?

**Answer:**

This is a classic use case for `PriorityQueue`.

1.  **The Choice:** `PriorityQueue`.
2.  **The "Why":**
    *   A standard queue like `ArrayDeque` is strictly FIFO (First-In, First-Out). It cannot handle priority.
    *   `PriorityQueue` is designed specifically for this. It's a "heap" data structure that always keeps the "highest priority" element at the head of the queue, ready to be polled.
    *   The definition of "priority" is determined by the elements themselves (if they implement `Comparable`) or by a `Comparator` you provide to the `PriorityQueue`'s constructor.

**Example:**

```java
// Task class that implements Comparable based on priority
class Task implements Comparable<Task> {
    String name;
    int priority; // 1 = highest, 5 = lowest

    public Task(String name, int priority) { this.name = name; this.priority = priority; }
    @Override public int compareTo(Task other) { return Integer.compare(this.priority, other.priority); }
    @Override public String toString() { return name + "(p" + priority + ")"; }
}

// Main logic
Queue<Task> scheduler = new PriorityQueue<>();
scheduler.offer(new Task("Send Emails", 5));
scheduler.offer(new Task("Process Payment", 1));
scheduler.offer(new Task("Run Analytics", 3));

System.out.println("Next task to run: " + scheduler.poll()); // Process Payment(p1)
System.out.println("Next task to run: " + scheduler.poll()); // Run Analytics(p3)
```
This demonstrates how the `PriorityQueue` ignores insertion order and always provides the element with the highest priority (lowest number in this case).

---

### 3. What is a `BlockingQueue` and in what kind of scenario would you use it?

**Answer:**

`BlockingQueue` is a special `Queue` interface from the `java.util.concurrent` package, designed for **producer-consumer** scenarios.

Its key feature is that it **blocks** (waits) under certain conditions:
*   If a "producer" thread tries to `put()` an element into a **full** queue, the thread will wait until a "consumer" thread makes space.
*   If a "consumer" thread tries to `take()` an element from an **empty** queue, the thread will wait until a "producer" thread adds an element.

**Scenario: A Restaurant Kitchen**
Imagine a restaurant kitchen.
*   **Producers:** The Chefs. They cook dishes and place them on a counter.
*   **Consumers:** The Waiters. They pick up dishes from the counter and serve them.
*   **The Counter is a `BlockingQueue`:**
    *   If the counter is full, the chefs have to **wait** before placing a new dish. They are *blocked*.
    *   If the counter is empty, the waiters have to **wait** for a dish to be cooked. They are *blocked*.

This flow control is essential for building robust concurrent systems, as it handles the coordination between threads automatically without needing manual `wait()` and `notify()` calls. `ArrayBlockingQueue` and `LinkedBlockingQueue` are common implementations.

---

### 4. *Design Question:* You are building a real-time analytics system that counts event occurrences from multiple threads. Which `Map` implementation is the best choice?

**Answer:**

This is a perfect use case for `ConcurrentHashMap` combined with `LongAdder` or `AtomicLong`.

1.  **The Choice:** `ConcurrentHashMap<String, LongAdder>`.
2.  **The "Why":**
    *   **Multiple Threads:** The requirement "from multiple threads" immediately disqualifies the non-thread-safe `HashMap`.
    *   **High Write Volume:** An analytics system implies many threads will be updating the counters frequently. This is a high-contention, write-heavy scenario.
    *   **`ConcurrentHashMap`:** It provides thread-safe access to the map itself, allowing different threads to update counters for *different* event types simultaneously.
    *   **`LongAdder` (or `AtomicLong`):** Why not just `ConcurrentHashMap<String, Long>`? If two threads try to update the counter for the *same* event at the same time, you'd have to do a "read-modify-write" operation (`map.put("eventA", map.get("eventA") + 1)`), which is not atomic and would cause race conditions. Using a thread-safe number class like `LongAdder` or `AtomicLong` as the value solves this. `LongAdder` is often preferred under high contention as it uses multiple internal variables to reduce contention, making it faster than `AtomicLong`.

**Example:**
```java
ConcurrentMap<String, LongAdder> counts = new ConcurrentHashMap<>();
// Thread 1:
counts.computeIfAbsent("login_event", k -> new LongAdder()).increment();
// Thread 2:
counts.computeIfAbsent("login_event", k -> new LongAdder()).increment();
```

---

### 5. Explain the trade-offs between `Collections.synchronizedMap` and `ConcurrentHashMap`.

**Answer:**

Both provide a thread-safe `Map`, but their approach and performance are vastly different.

*   **`Collections.synchronizedMap`:**
    *   **Mechanism:** It's a **wrapper**. It takes an existing `Map` (like a `HashMap`) and wraps every single one of its methods in a `synchronized` block.
    *   **Locking:** It uses a **single, map-wide lock**. Only one thread can be doing *anything* (reading or writing) to the map at any given time.
    *   **Performance:** Poor in high-concurrency environments. It becomes a bottleneck.
    *   **Iterator:** Fail-fast. You must manually synchronize the block where you iterate.

*   **`ConcurrentHashMap`:**
    *   **Mechanism:** It's a true concurrent implementation, not a wrapper.
    *   **Locking:** It uses **fine-grained locking** (lock striping). It locks only the specific part (bucket) of the map that is being written to, allowing other threads to access other parts simultaneously. Read operations are generally non-blocking.
    *   **Performance:** Excellent in high-concurrency environments, especially with more reads than writes.
    *   **Iterator:** Fail-safe. It will not throw `ConcurrentModificationException`.

**Conclusion:**
For any new development, **always prefer `ConcurrentHashMap`**. `Collections.synchronizedMap` is a legacy approach that is rarely the right choice in modern applications.
