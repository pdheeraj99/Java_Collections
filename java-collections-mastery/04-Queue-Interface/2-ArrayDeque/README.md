# 4.2 ArrayDeque 🔄 - The Ultimate Stack and Queue

Mawa, `ArrayDeque` anedi Collections Framework lo oka hidden gem. Peru chusi idi array-related anukuntam, kani deeni real power [`Deque`](../README.md) (Double Ended Queue) interface ni implement cheyadam lo undi.

> 💡 **The Golden Rule:** Simple ga cheppalante, **neeku Stack or Queue kavali anukunte, 99% of the time, `ArrayDeque` is the best choice.** It's faster than [`LinkedList`](../../02-List-Interface/3-LinkedList/README.md) and is not legacy like the old [`Stack`](../../02-List-Interface/4-Vector-And-Stack/README.md) class.

### The "Train with Two Engines" Analogy 🚂...🚂

[`LinkedList`](../../02-List-Interface/3-LinkedList/README.md) ni "Treasure Hunt" la anukunnam. `ArrayDeque` ni oka special **Train with an Engine on Both Ends** la oohinchuko.

*   Ee train ki mundu (`head`) and venaka (`tail`) rendu vypula engines unnayi.
*   **Queue Behavior (FIFO):** Passengers venaka nunchi ekkutharu (`offerLast`) and mundu nunchi digutaru (`pollFirst`). This is a standard queue.
*   **Stack Behavior (LIFO):** Passengers mundu nunchi ekkutharu (`push` or `offerFirst`) and mundu nunchi eh digutaru (`pop` or `pollFirst`). This is a standard stack.

Rendu vypula engines undatam valla, mundu nunchi or venaka nunchi add/remove cheyadam anedi **extremely fast (O(1))**.

### Internal Structure: The Circular Array

`ArrayDeque` peru lo unnatte, internal ga resizable **array** use chestundi. But it's very clever. It uses two pointers, `head` and `tail`, to keep track of the start and end of the queue within the array. Array nindipothe, adi automatically resize avtundi. Ee circular nature valla, add/remove operations at both ends are O(1).

### `ArrayDeque` vs. `Stack` (Legacy)

Manam [`Vector/Stack`](../../02-List-Interface/4-Vector-And-Stack/README.md) lesson lo chusinatlu, `Stack` class outdated.
*   **Performance:** `ArrayDeque` is much faster because it's not built on the slow, synchronized [`Vector`](../../02-List-Interface/4-Vector-And-Stack/README.md) class.
*   **API:** `ArrayDeque` implements the [`Deque`](../README.md) interface, which provides a much richer and cleaner API (`push`/`pop`, `offer`/`poll`) than the old `Stack` class.
*   **Flexibility:** `ArrayDeque` can act as both a stack and a queue.

> ⚠️ **The Verdict: `ArrayDeque` vs. `Stack`**
> **Never use the legacy [`Stack`](../../02-List-Interface/4-Vector-And-Stack/README.md) class.** Always use `ArrayDeque` when you need a Stack. It's safer, more modern, and significantly faster.

### `ArrayDeque` vs. `LinkedList` (for Stack/Queue)

[`LinkedList`](../../02-List-Interface/3-LinkedList/README.md) kuda [`Deque`](../README.md) ni implement chestundi, so adi kuda stack/queue la pani chestundi. But, `ArrayDeque` is often better.

| Feature | `ArrayDeque` | [`LinkedList`](../../02-List-Interface/3-LinkedList/README.md) | Winner 🏆 |
| :--- | :--- | :--- | :--- |
| **Memory Usage** | **More Efficient.** Uses a single array. | Less Efficient. Creates a `Node` object for every single element. | `ArrayDeque` |
| **Performance** | **Generally Faster.** Due to better memory locality. | Slower due to scattered memory locations (CPU cache misses). | `ArrayDeque` |
| **Nulls Allowed?** | ❌ **NO** | ✅ Yes | Depends on need |

> 💡 **The Verdict: `ArrayDeque` vs. `LinkedList`**
> For Stack or Queue operations, **`ArrayDeque` is almost always better** due to superior performance and more efficient memory usage. The only time to use [`LinkedList`](../../02-List-Interface/3-LinkedList/README.md) is when you have a specific need to store `null` elements.

---

### 📖 Method Cheatsheet

`ArrayDeque` gets its rich set of methods from the [`Deque`](../README.md) interface.

#### 1. As a Queue (FIFO)
For FIFO behavior, you add to the tail (end) and remove from the head (front).
| Operation | Throws Exception on Failure | Returns `null`/`false` on Failure |
| :--- | :--- | :--- |
| **Add to Tail** | `boolean add(E e)` | `boolean offer(E e)`* or `boolean offerLast(E e)`* |
| **Remove from Head**| `E remove()` | `E poll()`* or `E pollFirst()` |
| **Examine Head** | `E element()` | `E peek()`* or `E peekFirst()` |

#### 2. As a Stack (LIFO)
For LIFO behavior, you add to the head (front) and remove from the head (front).
| Stack Method | `Deque` Equivalent | Description |
| :--- | :--- | :--- |
| `void push(E e)`* | `addFirst(E e)` | Pushes an element onto the stack (adds to head). |
| `E pop()`* | `removeFirst()` | Pops an element from the stack (removes from head). |
| `E peek()`* | `peekFirst()` | Peeks at the top of the stack (views head). |

#### 3. General `Deque` & `Collection` Methods
| Method | Description |
| :--- | :--- |
| `int size()`* | Returns the number of elements. |
| `boolean isEmpty()`* | Checks if the deque is empty. |
| `void clear()`* | Removes all elements. |
| `boolean contains(Object o)` | Checks for an element's presence. (Slow - O(n)) |
| `Iterator<E> iterator()` | Returns a standard head-to-tail iterator. |
| `Iterator<E> descendingIterator()` | Returns a tail-to-head iterator. |

---

### 💼 Office Scenarios

*   **Anywhere you need a Stack:** Expression evaluation, parsing, implementing `undo` functionality, graph/tree traversal (DFS).
*   **Anywhere you need a Queue:** Processing a queue of web server requests, task scheduling, messaging systems, graph/tree traversal (BFS).

---

### 🎯 Interview Focus

*   **Q: What would you use to implement a Stack in Java?**
    *   **A:** `ArrayDeque`. It's the modern, recommended, and most performant choice. I would explain that the legacy [`Stack`](../../02-List-Interface/4-Vector-And-Stack/README.md) class should be avoided because it extends [`Vector`](../../02-List-Interface/4-Vector-And-Stack/README.md).

*   **Q: When would you use `LinkedList` instead of `ArrayDeque` for a queue?**
    *   **A:** The only significant reason is if I need to store `null` elements in the queue, as `ArrayDeque` does not permit nulls. Otherwise, `ArrayDeque` is generally superior in performance and memory usage.

*   **Q: Can `ArrayDeque` be used like an `ArrayList`?**
    *   **A:**
        > ⚠️ **`ArrayDeque` is Not a `List`**
        > No. This is a common point of confusion. Despite its name, `ArrayDeque` **does not implement the [`List`](../../02-List-Interface/README.md) interface**.
        >
        > You cannot access elements by an index using `get(index)`. It is highly optimized for one specific purpose: adding and removing from its head and tail. If you need index-based access, you need an [`ArrayList`](../../02-List-Interface/2-ArrayList/README.md).
