# 5.4 TreeMap 🌳 - The Sorted Map

Mawa, [`HashMap`](../2-HashMap/README.md) gives us speed, [`LinkedHashMap`](../3-LinkedHashMap/README.md) gives us insertion order. What if we need a map that is **always sorted by its keys**? For that, we have `TreeMap`.

`TreeMap` is the [`Map`](../README.md) implementation that keeps its entries sorted according to the natural ordering of its keys, or by a `Comparator` provided at map creation time.

### The "Glossary" Analogy 📚

*   A **[`HashMap`](../2-HashMap/README.md)** is like a standard dictionary. The words are generally alphabetical, but not strictly guaranteed.
*   A **`TreeMap`** is like the **glossary at the back of a textbook**. The terms (keys) are always kept in perfect alphabetical order, making it easy to find a term (and its definition), and also to read all the terms in a sorted sequence.

---

### How It Works: Sorting by KEY

The most important thing to remember is that a `TreeMap` sorts its entries based on its **keys**. The values do not affect the sort order.

How does it know how to sort the keys? Using the same two mechanisms we've seen before:
1.  **[`Comparable`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md):** The key objects must implement the `Comparable` interface to define their "natural order".
2.  **[`Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md):** You can provide an external sorting rule by passing a `Comparator` for the keys to the `TreeMap`'s constructor.

This topic is so important that we have a dedicated guide for it. Understanding this is essential for using `TreeMap` effectively.

➡️ **[Read The Ultimate Sorting Guide: Comparable vs. Comparator](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)**

---

### Key Properties & Performance

| Feature | `TreeMap` |
| :--- | :--- |
| **Order** | ✅ **Sorted Order by Key** |
| **Performance** | `put`, `get`, `remove`, `containsKey`: **O(log n)** |
| **Nulls Allowed?** | ❌ **NO `null` keys**. ✅ `null` values are okay. |
| **Internal Structure**| Red-Black Tree (just like [`TreeSet`](../../03-Set-Interface/4-TreeSet/README.md)) |
| **Thread-Safety** | ❌ No |

> ⚠️ **`TreeMap` and `null` Keys Don't Mix**
> `TreeMap` **cannot have a `null` key**. This is because it needs to compare keys to keep them sorted, and `null` cannot be compared to any other key. Attempting to `put` a `null` key will result in a `NullPointerException`.
>
> However, `TreeMap` **does allow `null` values**. The values are not used for sorting, so they can be `null`.

---

### 📖 Method Cheatsheet

`TreeMap` implements the `NavigableMap` interface (a sub-interface of [`Map`](../README.md)), giving it powerful navigational methods similar to [`TreeSet`](../../03-Set-Interface/4-TreeSet/README.md).

#### 1. Core `Map` Methods
| Method | Description |
| :--- | :--- |
| `V put(K key, V value)`* | Adds an entry, keeping the map sorted by key. (O(log n)) |
| `V get(Object key)`* | Retrieves the value for a key. (O(log n)) |
| `V remove(Object key)`* | Removes the entry for a key. (O(log n)) |
| `boolean containsKey(Object o)`*| Checks for a key's presence. (O(log n)) |

#### 2. Navigational Methods (The Superpower of `TreeMap`)
| Method | Description |
| :--- | :--- |
| `Map.Entry<K,V> firstEntry()`* | Returns the entry with the lowest key. |
| `Map.Entry<K,V> lastEntry()`* | Returns the entry with the highest key. |
| `K lowerKey(K key)` | Returns the greatest key **strictly less than** the given key. |
| `K floorKey(K key)` | Returns the greatest key **less than or equal to** the given key. |
| `K higherKey(K key)` | Returns the smallest key **strictly greater than** the given key. |
| `K ceilingKey(K key)`| Returns the smallest key **greater than or equal to** the given key. |

#### 3. Polling & Iteration
| Method | Description |
| :--- | :--- |
| `Map.Entry<K,V> pollFirstEntry()`* | **Removes** and returns the entry with the lowest key. |
| `Map.Entry<K,V> pollLastEntry()`* | **Removes** and returns the entry with the highest key. |
| `NavigableSet<K> navigableKeySet()`| Returns a [`NavigableSet`](../../03-Set-Interface/README.md) view of the keys. |
| `NavigableSet<K> descendingKeySet()`| Returns a reverse-order [`NavigableSet`](../../03-Set-Interface/README.md) view of the keys. |

---

### 💼 Office Scenarios

*   **Time-Series Data:** Storing sensor data where the key is a `Timestamp` and the value is the reading. `TreeMap` allows you to easily query for data within a specific time range (e.g., "give me all readings from 9 AM to 10 AM").
*   **Sorted Reports:** Generating a report of company sales, with the data sorted by month (the key).
*   **Data Visualization:** Providing data to a charting library that requires the data points to be in a sorted order by their x-axis value (the key).
*   **Directory/Index:** Creating an index of a book where you need to quickly find the page number for a given term.

---

### 🎯 Interview Focus

*   **Q: When would you use a `TreeMap` over a `HashMap`?**
    *   **A:** When I need the map to be constantly sorted by its keys. While [`HashMap`](../2-HashMap/README.md) is faster for simple `put`/`get` operations, `TreeMap` is essential when I need to retrieve entries in sorted order or perform range queries (e.g., "get all entries between key A and key B").

*   **Q: Can you add a key to a `TreeMap` that doesn't implement `Comparable`?**
    *   **A:**
        > ⚠️ **The `ClassCastException` Gotcha**
        > No. If you use a custom object as a key, you **must** provide a sorting rule.
        >
        > If the key class doesn't implement [`Comparable`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) AND you don't provide a [`Comparator`](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) to the `TreeMap`'s constructor, it will throw a `ClassCastException`. This happens the moment you add the *second* entry, because that's when the `TreeMap` first needs to compare two keys to decide their order.
