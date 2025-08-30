# Phase 4: The Queue Interface 🚶‍♂️🚶‍♀️🚶‍♂️

Welcome to Phase 4! Ikkada manam `Queue` interface gurinchi nerchukundam.

A `Queue` is a collection designed for holding elements prior to processing. The most important principle of a standard queue is **FIFO (First-In, First-Out)**.

**The "Movie Ticket Counter" Analogy 🎟️:**
Imagine a line of people at a movie ticket counter.
*   The first person to join the queue (`offer`) is the first person to get a ticket and leave (`poll`).
*   New people are always added to the end of the line.
*   You can see who is at the front of the line (`peek`), but you can't just jump the queue.

This FIFO principle is fundamental to many programming problems, like task scheduling, message processing, and algorithms like Breadth-First Search (BFS).

**Key Operations of a `Queue`:**
*   `offer(e)`: Adds an element to the end of the queue.
*   `poll()`: Removes and returns the element at the head of the queue. Returns `null` if the queue is empty.
*   `peek()`: Returns the element at the head of the queue without removing it. Returns `null` if the queue is empty.

> 💡 **`offer`/`poll` vs. `add`/`remove`**
> The `Queue` interface has two sets of methods for the same operations, with one key difference: how they handle failure.
> *   **Methods that throw exceptions:** `add(e)`, `remove()`, `element()`
> *   **Methods that return a special value (`false` or `null`):** `offer(e)`, `poll()`, `peek()`
>
> **Best Practice:** In most cases, it's safer and cleaner to use **`offer()`, `poll()`, and `peek()`**. They prevent your program from crashing if you try to operate on a full or empty queue.

Ee phase lo manam kindi implementations gurinchi nerchukundam:
*   [**4.2 ArrayDeque**](./2-ArrayDeque/README.md) 🔄 (The go-to choice for a fast, general-purpose queue and stack)
*   [**4.3 PriorityQueue**](./3-PriorityQueue/README.md) 🥇 (A special queue that orders elements by priority, not FIFO)

---

## When to Use Which Queue? (The Ultimate Summary) 🤔

| Scenario | Recommendation | Why? (The Simple Reason) |
| :--- | :--- | :--- |
| **I need a standard First-In, First-Out (FIFO) queue.** | [`ArrayDeque`](./2-ArrayDeque/README.md) | Best performance and memory usage for a general-purpose queue. |
| **I need a Stack (LIFO).** | [`ArrayDeque`](./2-ArrayDeque/README.md) | The modern, correct, and fastest way to implement a stack. |
| **I need to process elements based on importance, not arrival time.** | [`PriorityQueue`](./3-PriorityQueue/README.md) | The only choice for a priority-based system. It's a "VIPs first" queue. |
| **I need a queue that can store `null` elements.** | [`LinkedList`](../02-List-Interface/3-LinkedList/README.md) | `ArrayDeque` and `PriorityQueue` do not allow nulls. `LinkedList` implements `Deque` and can be used as a Queue that accepts nulls. |

> ⚠️ **Gotcha: Need `null`s in your Queue?**
> The high-performance [`ArrayDeque`](./2-ArrayDeque/README.md) and [`PriorityQueue`](./3-PriorityQueue/README.md) **do not accept `null` elements**. If you have a requirement to store `null`s in a queue, your best option is to use [`LinkedList`](../02-List-Interface/3-LinkedList/README.md), as it implements the `Queue` interface and allows `null` values.

> 💡 **The Golden Rule:** When in doubt, **start with [`ArrayDeque`](./2-ArrayDeque/README.md)**. It is the most efficient and common choice for any general-purpose Stack or Queue requirement.
