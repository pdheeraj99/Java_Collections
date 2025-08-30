# 4.3 PriorityQueue 🥇 - The "VIPs First" Queue

Mawa, manam standard queue anedi FIFO (First-In, First-Out) ani nerchukunnam. `PriorityQueue` anedi ee rule ni break chese oka special queue.

A `PriorityQueue` doesn't care about when an element arrived. It only cares about the element's **priority**. The element with the highest priority is always at the head of the queue, ready to be processed.

### The "Hospital Emergency Room" Analogy 🚑

Ee concept ardham cheskovadaniki idi best analogy.

*   **A normal queue ([`ArrayDeque`](../2-ArrayDeque/README.md))** is like a regular doctor's clinic. First come, first served.
*   **A `PriorityQueue`** is like a **hospital Emergency Room (ER)**.
    *   Patients are **not** treated in the order they arrive.
    *   A patient with a critical heart attack (highest priority) will be treated before a patient with a minor cold (lowest priority), even if the cold patient arrived hours earlier.
    *   The `poll()` method on a `PriorityQueue` is like the ER doctor asking, "Who is the most critical patient right now?". It will always return the highest priority element.

---

### How Does it Determine Priority?

`PriorityQueue` ki "priority" ante ento maname cheppali. It needs a sorting rule. How? Using the same concepts we learned for [`TreeSet`](../../03-Set-Interface/4-TreeSet/README.md)!

1.  **Natural Ordering ([`Comparable`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)):** The objects you add to the queue should implement the `Comparable` interface. This defines their default, natural priority.
2.  **Custom Ordering ([`Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)):** If you need a different priority rule, you can pass a `Comparator` to the `PriorityQueue`'s constructor.

Ee topic meeda full clarity kosam, mana dedicated guide ni chudu. Ee concept [`TreeSet`](../../03-Set-Interface/4-TreeSet/README.md), `PriorityQueue`, [`TreeMap`](../../05-Map-Interface/4-TreeMap/README.md)... chala chotla vastundi.

➡️ **[Read The Ultimate Sorting Guide: Comparable vs. Comparator](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)**

---

### Key Properties & Performance

| Feature | `PriorityQueue` |
| :--- | :--- |
| **Order** | Priority-based (Min-Heap). Head is always the "least" element. |
| **Performance** | `offer` (add): **O(log n)** <br> `poll` (remove head): **O(log n)** <br> `peek` (view head): **O(1)** |
| **Nulls Allowed?** | ❌ **NO** |
| **Internal Structure**| Binary Heap (a type of binary tree) |
| **Thread-Safety** | ❌ No |

> ⚠️ **`PriorityQueue` and `null` Don't Mix**
> Just like [`TreeSet`](../../03-Set-Interface/4-TreeSet/README.md), `PriorityQueue` **does not allow `null` elements**. This is because it must be able to compare elements to determine their priority. `null` cannot be compared to anything, so attempting to add `null` will result in a `NullPointerException`.

> ⚠️ **Common Misconception: Is `PriorityQueue` sorted?**
> No. This is a critical point. A `PriorityQueue` **only guarantees that the head element is the highest-priority item**. The rest of the elements are stored in an efficient heap structure, but they are **not in a sorted order**. If you iterate over the queue, it will not return the elements in sorted order. If you need a sorted collection, use a [`TreeSet`](../../03-Set-Interface/4-TreeSet/README.md) or sort a [`List`](../../02-List-Interface/README.md).

---

### 📖 Method Cheatsheet

`PriorityQueue` implements the [`Queue`](../README.md) interface. Its methods behave based on priority, not FIFO.

#### Core `Queue` Methods
| Operation | Throws Exception on Failure | Returns `null`/`false` on Failure | Description |
| :--- | :--- | :--- | :--- |
| **Add** | `boolean add(E e)` | `boolean offer(E e)`* | Adds an element to the queue based on its priority. (O(log n)) |
| **Remove Head**| `E remove()` | `E poll()`* | **Removes** and returns the element with the **highest priority**. (O(log n)) |
| **Examine Head** | `E element()` | `E peek()`* | **Retrieves**, but does not remove, the element with the **highest priority**. (O(1)) |

#### Other Common Methods
| Method | Description |
| :--- | :--- |
| `int size()`* | Returns the number of elements in the queue. |
| `void clear()`* | Removes all elements from the queue. |
| `boolean contains(Object o)` | Returns `true` if the queue contains the element. (Slow: O(n)) |
| `Object[] toArray()` | Returns an array containing all elements (no order guarantee). |
| `Comparator<? super E> comparator()` | Returns the `Comparator` used to order the elements, or `null` if it uses natural ordering. |

---

### 💼 Office Scenarios

*   **Task Scheduling:** Oka system lo, high-priority background jobs (e.g., "process payment") ni low-priority jobs (e.g., "send promotional email") kanna mundu run cheyadaniki.
*   **Event Processing:** Real-time system lo, "CRITICAL_ERROR" events ni "INFO" or "WARN" level events kanna mundu handle cheyadaniki.
*   **Pathfinding Algorithms:** In GPS navigation or games, algorithms like Dijkstra's and A* use a `PriorityQueue` to always explore the node with the shortest known distance first.

---

### 🎯 Interview Focus

*   **Q: What's the main difference between `ArrayDeque` and `PriorityQueue`?**
    *   **A:** [`ArrayDeque`](../2-ArrayDeque/README.md) is a strict FIFO queue. `PriorityQueue` is a priority-based queue where the element with the highest priority (as defined by [`Comparable`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) or [`Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)) is always at the head.

*   **Q: How do you define the priority of elements in a `PriorityQueue`?**
    *   **A:** Either the elements must implement the [`Comparable`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) interface (for natural ordering), or you must provide a [`Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) to the `PriorityQueue`'s constructor (for custom ordering).

*   **Q: What is the time complexity for adding and removing elements from a `PriorityQueue`?**
    *   **A:** O(log n) for both `add` (offer) and `remove` (poll), because it has to maintain the heap property. `peek` is O(1).

*   **Q: Is a `PriorityQueue` sorted internally?**
    *   **A:** No. As explained in the warning block above, this is a common misconception. Only the head element is guaranteed to have the highest priority. The rest of the queue is stored in a heap structure, not a sorted order.
