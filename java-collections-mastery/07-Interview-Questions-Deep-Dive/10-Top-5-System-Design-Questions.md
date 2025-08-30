# 🏅 Interview Questions Part 10: System Design with Collections

Mawa, final round! Ikkada manam high-level system design questions ni chuddam, where the choice of the right data structure is the key to the entire design.

---

### 1. *System Design:* Design a simple web crawler. Which collections would you use?

**Answer:**

A web crawler has two main jobs: 1) Keep track of URLs it needs to visit. 2) Keep track of URLs it has *already* visited to avoid loops and duplicate work.

The choice of collections is critical for efficiency.

*   **To store URLs to visit:** A **`Queue`** is the perfect choice.
    *   **Why:** A crawler typically explores a website level by level. It starts at the homepage, finds all links, adds them to a queue, and then processes them one by one. This breadth-first search (BFS) approach is naturally implemented with a FIFO queue.
    *   **Implementation:** An **`ArrayDeque<URL>`** would be the best implementation. It's fast and memory-efficient for FIFO operations.

*   **To store URLs already visited:** A **`Set`** is the perfect choice.
    *   **Why:** We need to do two things very quickly: 1) Check if we have *already* visited a URL to avoid re-processing it. 2) Add the new URL to the "visited" list. A `Set` provides O(1) average time complexity for both `contains()` and `add()`.
    *   **Implementation:** A **`HashSet<URL>`** would be the best implementation for its raw speed.

**The Flow:**
1.  Add the starting URL to the `Queue`.
2.  Start a loop: while the `Queue` is not empty...
3.  `URL currentUrl = queue.poll();`
4.  Check if `visitedSet.contains(currentUrl)`. If yes, `continue` to the next URL.
5.  If not, `visitedSet.add(currentUrl)`.
6.  Process the page at `currentUrl`.
7.  Find all new links on the page. For each new link, `queue.offer(newLink)`.

---

### 2. *System Design:* Design a "trending topics" feature (like on Twitter).

**Answer:**

This feature needs to do two things: 1) Count the frequency of topics (hashtags) over a recent time window. 2. Efficiently retrieve the Top K (e.g., Top 10) topics.

*   **Step 1: Counting Frequencies**
    *   **Data Structure:** A **`Map<String, Integer>`** is the obvious choice to store the topic and its count.
    *   **Implementation:** Since this will be updated by many threads concurrently, a **`ConcurrentHashMap<String, LongAdder>`** is the best choice, as we discussed in the concurrency questions. It handles the high-volume, concurrent updates efficiently.

*   **Step 2: Finding the Top K Topics**
    *   **The Naive Way:** `Map` lo unna anni entries ni teeskuni, sort chesi, top 10 teeyadam. Idi chala slow if you have millions of topics.
    *   **The Efficient Way:** A **`PriorityQueue`** (configured as a **Min-Heap**).
    *   **How it works:**
        1.  Create a `PriorityQueue` of size K (e.g., 10). The priority will be the topic's count.
        2.  Iterate through your frequency map. For each topic:
        3.  If the `PriorityQueue` has less than K items, just `add` the topic.
        4.  If the `PriorityQueue` is full (size K), compare the current topic's count with the count of the **smallest** item in the queue (`pq.peek()`).
        5.  If the current topic's count is **greater** than the smallest item in the queue, `poll()` the smallest item and `add` the current topic.
    *   **Why it works:** At the end, the `PriorityQueue` will contain the Top K elements. This is much more efficient (O(N log K)) than sorting the entire list (O(N log N)).

---

### 3. *System Design:* Design a notification service with priority levels.

**Answer:**

This is a direct mapping to a `PriorityQueue`.

1.  **The Choice:** `PriorityQueue<Notification>`.
2.  **The "Why":**
    *   The core requirement is to process items based on priority, not arrival time. This is exactly what `PriorityQueue` is for.
    *   You would create a `Notification` class that implements `Comparable`, or provide a `Comparator`. The comparison logic would be based on the notification's priority level (e.g., `CRITICAL` > `HIGH` > `LOW`).

**The Flow:**
*   Multiple producer threads (e.g., from different services like "Payment", "Social", "Marketing") would `offer()` `Notification` objects to a single, shared `PriorityQueue`.
*   A pool of consumer threads would continuously `poll()` from the queue. Because it's a `PriorityQueue`, they are guaranteed to always receive the highest-priority notification available, regardless of when it was added.

---

### 4. *System Design:* You are building a system that tracks the real-time location of delivery drivers. Which `Map` implementation is best?

**Answer:**

This question tests your understanding of the trade-offs between the main `Map` implementations.

1.  **The Choice:** A **`ConcurrentHashMap<DriverID, Location>`**.
2.  **The "Why":**
    *   **Key-Value:** The requirement is to map a `DriverID` to a `Location`, so a `Map` is the right base structure.
    *   **Concurrency:** Driver locations are updated from many different driver apps (threads) simultaneously. The system also needs to read these locations to display on a map for customers. This requires a thread-safe map. This eliminates `HashMap`.
    *   **Performance:** `Hashtable` is too slow. `ConcurrentHashMap` is the clear winner for performance in a concurrent scenario.
    *   **Order is not important:** We don't need the drivers to be sorted by their ID (`TreeMap` is not needed) or by the order they came online (`LinkedHashMap` is not needed). We just need fast, concurrent lookup by `DriverID`.

`ConcurrentHashMap` provides the best combination of thread-safety and high performance for this specific use case.

---

### 5. *System Design:* Design the data structure for an auto-complete feature.

**Answer:**

This is a classic question that is often solved with a specialized tree-like data structure called a **Trie** (or Prefix Tree). While not a direct part of the core Java Collections Framework, it's often built using `Map`s.

*   **The Structure: A Trie Node**
    Each node in the Trie represents a character.
    ```java
    class TrieNode {
        // Each child key is a character, the value is the next TrieNode
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>(); // Using HashMap for children
            isEndOfWord = false;
        }
    }
    ```

*   **How it works:**
    1.  **Insertion:** To insert a word like "MAWA", you start at the root. You go to the 'M' child. From there, to the 'A' child, then 'W', then 'A'. At the final 'A' node, you set `isEndOfWord = true`. If any character node doesn't exist along the way, you create it.
    2.  **Search/Auto-complete:** To find all words starting with "MA", you traverse the Trie to the 'M' node, then the 'A' node. From that 'A' node, you perform a search (like a Depth First Search) to find all child paths that end with `isEndOfWord = true`.

**Why not just a `List`?**
Iterating a `List` of all possible words and calling `startsWith("MA")` on each one would be O(N*L) where N is the number of words and L is their length. A Trie is much faster, with search complexity proportional to the length of the prefix, not the number of words in the dictionary.
