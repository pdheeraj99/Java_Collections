# 🏅 Interview Questions Part 2: List & Set Scenarios

Mawa, ee section lo manam `List` and `Set` meeda adige konni tricky and scenario-based questions ni chuddam.

---

### 1. How would you efficiently remove elements from a `List` while iterating over it?

**Answer:**

Idi chala common mistake chese question. The **only safe way** to modify a collection while iterating is by using the `Iterator`'s own `remove()` method.

**The Wrong Way (throws `ConcurrentModificationException`):**
Using a for-each loop and calling the list's `remove()` method will cause a crash.

```java
List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

try {
    for (String fruit : fruits) {
        if (fruit.equals("Banana")) {
            fruits.remove(fruit); // DANGER! Modifying the list directly
        }
    }
} catch (ConcurrentModificationException e) {
    System.out.println("See? It crashed! -> " + e);
}
```
*   **Why it fails:** The for-each loop lopalana oka `Iterator` ni create chestundi. Nuvvu `fruits.remove()` ani direct ga call cheste, aa iterator ki teliyakunda list modify avtundi. The "Strict Guard" (fail-fast iterator) ventane `ConcurrentModificationException` throw chestadu.

**The Right Way (using `Iterator.remove()`):**
`Iterator` object ni direct ga use chesi, daani `remove()` method ni call cheyyali.

```java
List<String> fruits2 = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
Iterator<String> it = fruits2.iterator();

while (it.hasNext()) {
    String fruit = it.next();
    if (fruit.equals("Banana")) {
        it.remove(); // SAFE! We are using the iterator's own remove method.
    }
}
System.out.println("After safe removal: " + fruits2); // Output: [Apple, Cherry]
```
*   **Why it works:** `it.remove()` call cheste, iterator ki telusu "Nene ee element ni teesesanu", so adi tana internal state ni correctly update cheskuni, exception throw cheyyadu.

---

### 2. How do you remove duplicates from an `ArrayList` while preserving the original order?

**Answer:**

The best and cleanest way to do this is by using a `LinkedHashSet`.

1.  `LinkedHashSet` maintains insertion order.
2.  `LinkedHashSet` automatically handles duplicates (it only stores unique elements).

So, the logic is: `ArrayList` -> `LinkedHashSet` (to remove duplicates) -> `ArrayList` (back to a list).

```java
// Original list with duplicates
ArrayList<String> originalList = new ArrayList<>(Arrays.asList("A", "B", "C", "A", "D", "B"));
System.out.println("Original: " + originalList);

// 1. Convert the ArrayList to a LinkedHashSet
// This automatically removes duplicates while preserving the first-seen order.
LinkedHashSet<String> uniqueSet = new LinkedHashSet<>(originalList);

// 2. Convert the LinkedHashSet back to an ArrayList
ArrayList<String> listWithoutDuplicates = new ArrayList<>(uniqueSet);

System.out.println("Deduplicated: " + listWithoutDuplicates);
// Output: [A, B, C, D]
```
> 🔥 **Mawa Tip:** Ee "one-liner" interview lo chepthe super anukuntaru: `new ArrayList<>(new LinkedHashSet<>(originalList));`

---

### 3. When would you choose a `TreeSet` over a `HashSet`?

**Answer:**

The choice depends entirely on one question: **Do you need the elements to be sorted?**

*   **Use `HashSet` when:**
    *   **Speed is the #1 priority.** `HashSet` offers O(1) average time for `add`, `remove`, and `contains`.
    *   The order of elements does not matter at all.

*   **Use `TreeSet` when:**
    *   You **must** keep the elements in a sorted order at all times (e.g., alphabetical, numerical).
    *   You need to perform range queries, like "get all elements between X and Y". `TreeSet` provides methods like `headSet()`, `tailSet()`, and `subSet()` for this.
    *   You are willing to accept a slightly slower performance of O(log n) for the benefit of ordering.

---

### 4. Explain `CopyOnWriteArrayList`. When is it a better choice for concurrency than a synchronized `ArrayList`?

**Answer:**

`CopyOnWriteArrayList` is a specialized, thread-safe `List` implementation.

*   **How it works:** Its name tells the whole story: **Copy-On-Write**.
    *   **Read operations (`get`)** are very fast because they don't need any locks. They just read from the existing array.
    *   **Write operations (`add`, `remove`)** are very expensive. Whenever you modify the list, it creates a **brand new copy** of the entire underlying array, performs the modification on that new copy, and then replaces the old array with the new one.

*   **When is it better?**
    It's better than `Collections.synchronizedList` in a very specific scenario: **Read-heavy, write-rare concurrency.**
    *   Imagine a configuration list that is loaded at startup and then read by thousands of threads per second, but only modified once a day by an admin.
    *   In this case, the thousands of read operations will be lock-free and super fast. The one write operation per day will be slow, but that's an acceptable trade-off.
    *   A `synchronizedList` would force every single one of those thousands of reads to acquire a lock, which would be much slower overall.

---

### 5. *Design Question:* You need to store a list of user IDs for a high-traffic feature. The list will be read very frequently, but updated very rarely. Which `List` implementation would you choose and why?

**Answer:**

This is a classic scenario that points directly to `CopyOnWriteArrayList`.

1.  **The Choice:** `CopyOnWriteArrayList`.
2.  **The "Why":**
    *   The primary requirement is **high-performance reads** from multiple threads ("read very frequently"). `CopyOnWriteArrayList` provides lock-free, and therefore very fast, read operations.
    *   The secondary requirement is infrequent updates ("updated very rarely"). This means we can tolerate the high cost of the write operations. A standard `ArrayList` would not be thread-safe. A `Collections.synchronizedList` would make the frequent read operations slow due to unnecessary locking.
    *   Therefore, `CopyOnWriteArrayList` offers the perfect performance trade-off for this specific "read-heavy, write-rare" use case.
