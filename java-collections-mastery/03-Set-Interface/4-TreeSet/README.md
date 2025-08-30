# 3.4 TreeSet 🌳 - The Sorted Set

Mawa, manam [`HashSet`](../2-HashSet/README.md) (no order), [`LinkedHashSet`](../3-LinkedHashSet/README.md) (insertion order) chusam. Ippudu, a [`Set`](../README.md) that is **always sorted**: `TreeSet`.

`TreeSet` is the perfect choice when you need a collection of unique elements that are always kept in a sorted order.

### The "Leaderboard" Analogy 🏆

Imagine a real-time video game leaderboard.

*   **Always Sorted:** Whenever a player submits a new high score, they are **automatically placed in the correct rank**. The leaderboard doesn't need to be re-sorted manually. `TreeSet` works the same way; elements are always in sorted order.
*   **Uniqueness:** A player can only appear once on the leaderboard. `TreeSet` also ensures all its elements are unique.
*   **Performance:** Adding a new score or finding a player's rank is fast, but not instant. The system has to find the correct position in the sorted list. This is an **O(log n)** operation, which is very efficient.

---

### How It Works: The Sorting Rule

`TreeSet` ki add chese elements ni ela sort cheyalo daaniki teliyali. It needs a rule for comparison. There are two ways to provide this rule:
1.  **`Comparable`:** The object itself defines its "natural order" by implementing the `Comparable` interface.
2.  **`Comparator`:** You provide an external sorting rule by passing a `Comparator` object to the `TreeSet`'s constructor.

Ee topic chala important, so deeni kosam manam oka separate, detailed guide create chesam.

➡️ **[Read The Ultimate Sorting Guide: Comparable vs. Comparator](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)**

---

### Key Properties & Performance

| Feature | `TreeSet` | [`HashSet`](../2-HashSet/README.md) / [`LinkedHashSet`](../3-LinkedHashSet/README.md) |
| :--- | :--- | :--- |
| **Order** | ✅ **Sorted Order** | No order / Insertion order |
| **Performance** | O(log n) | **O(1)** |
| **Nulls Allowed?** | ❌ **NO** | Yes (one) |
| **Internal Structure**| Red-Black Tree | HashMap |
| **Sorting Rule** | Needs [`Comparable` or `Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)| Needs [`hashCode`/`equals`](../2-HashSet/README.md#the-magic-behind-hashset-hashcode-and-equals) |

> ⚠️ **`TreeSet` and `null` Don't Mix**
> `TreeSet` **does not allow `null` elements**. Because elements must be compared to maintain order, and `null` cannot be compared to anything (`null.compareTo(...)` is meaningless), attempting to add a `null` will immediately throw a `NullPointerException`.

---

### 📖 Method Cheatsheet

`TreeSet` implements the `NavigableSet` interface (a sub-interface of [`Set`](../README.md)), giving it powerful methods beyond a basic `Set`.

#### 1. Core `Set` Methods
| Method | Description |
| :--- | :--- |
| `boolean add(E e)`* | Adds an element in sorted order if not present. (O(log n)) |
| `boolean remove(Object o)`* | Removes the specified element if present. (O(log n)) |
| `boolean contains(Object o)`* | Checks for an element's presence. (O(log n)) |
| `int size()`* | Returns the number of elements. |
| `void clear()`* | Removes all elements. |
| `boolean isEmpty()`* | Checks if the set is empty. |

#### 2. Navigational Methods (The Superpower of `TreeSet`)
These methods are extremely useful for searching in a sorted set.
| Method | Description |
| :--- | :--- |
| `E first()`* | Returns the first (lowest) element. |
| `E last()`* | Returns the last (highest) element. |
| `E lower(E e)` | Returns the greatest element that is **strictly less than** `e`. |
| `E floor(E e)` | Returns the greatest element that is **less than or equal to** `e`. |
| `E higher(E e)` | Returns the smallest element that is **strictly greater than** `e`. |
| `E ceiling(E e)` | Returns the smallest element that is **greater than or equal to** `e`. |

#### 3. Polling & Iteration
| Method | Description |
| :--- | :--- |
| `E pollFirst()`* | **Removes** and returns the first (lowest) element. |
| `E pollLast()`* | **Removes** and returns the last (highest) element. |
| `Iterator<E> iterator()`* | Returns an iterator in ascending order. |
| `Iterator<E> descendingIterator()`| Returns an iterator in descending order. |

#### 4. Range View Methods (Subsets)
These methods return a "view" of the set, not a copy. Changes to the view affect the original set.
| Method | Description |
| :--- | :--- |
| `NavigableSet<E> subSet(E from, boolean fromInclusive, E to, boolean toInclusive)` | Returns a view of the portion of this set between `from` and `to`. |
| `NavigableSet<E> headSet(E to, boolean inclusive)` | Returns a view of the portion of this set strictly less than (or equal to) `to`. |
| `NavigableSet<E> tailSet(E from, boolean inclusive)` | Returns a view of the portion of this set greater than (or equal to) `from`. |

---

### 💼 Office Scenarios

*   **Leaderboards/Rankings:** Displaying top N scores, users by reputation, etc.
*   **Sorted Reports:** Generating a report of unique customers sorted alphabetically by name.
*   **Calendar Events:** Storing unique event dates and then easily retrieving all events happening in the next 7 days. `TreeSet` has useful methods like `headSet()`, `tailSet()`, and `subSet()` for range-based queries.

---

### 🎯 Interview Focus

*   **Q: How does a `TreeSet` maintain order?**
    *   **A:** It uses a self-balancing binary search tree data structure, specifically a Red-Black Tree. This ensures that `add`, `remove`, and `contains` operations are always efficient, with a time complexity of O(log n).

*   **Q: What's the difference between `Comparable` and `Comparator`?**
    *   **A:** [`Comparable`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) is used for the **natural ordering** of an object, and the logic is defined *inside* the class itself (by implementing the `compareTo` method). [`Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) is used for **custom or external ordering**, and the logic is defined in a *separate* class.

*   **Q: When would you use `Comparator` over `Comparable`?**
    *   **A:** 1. When you cannot modify the source code of the class you want to sort. 2. When you need multiple different ways to sort the same type of object (e.g., sort employees by ID, then by name, then by salary).

*   **Q: Can you add a custom object to a `TreeSet` without implementing `Comparable` or providing a `Comparator`?**
    *   **A:**
        > ⚠️ **The `ClassCastException` Gotcha**
        > No. If you try to add a custom object to a `TreeSet` without providing a sorting rule (either via [`Comparable`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) or a [`Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)), the `TreeSet` has no idea how to order the elements.
        >
        > It will successfully add the *first* element. But the moment you try to add the *second* element, it will throw a `ClassCastException` because it cannot cast the object to `Comparable` to compare it with the existing element.
