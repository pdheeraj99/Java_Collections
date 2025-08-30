# 5.3 LinkedHashMap 🔗 - Order in the Court!

Mawa, [`HashMap`](../2-HashMap/README.md) chala fast, kani order ni pattinchukodu. If you need the speed of a `HashMap` but also need to maintain the **insertion order** of the keys, `LinkedHashMap` is your perfect solution.

### The "Recipe" Analogy 📜

*   A **[`HashMap`](../2-HashMap/README.md)** is like a random pile of ingredients on a kitchen counter. You can grab any ingredient quickly (e.g., "salt"), but you have no idea which ingredient you picked up first.
*   A **`LinkedHashMap`** is like a **recipe**. The ingredients (key-value pairs) are listed in the exact order you need to use them. You can still find any ingredient quickly, but you also know the sequence from first to last.

`LinkedHashMap` gives you the best of both worlds:
1.  The **O(1) fast performance** of [`HashMap`](../2-HashMap/README.md) for `put`, `get`, and `remove`.
2.  The **predictable insertion order** of keys.

---

### How It Works Internally

`LinkedHashMap` extends [`HashMap`](../2-HashMap/README.md), so it uses the same hashing mechanism for speed. The extra magic is that it also maintains a **doubly-linked list** running through all its entries. This list is what remembers the order in which keys were inserted.

---

### Killer Feature: Access Order & LRU Caches

> 💡 **The #1 Use Case: Building an LRU Cache**
> This is the most famous and powerful feature of `LinkedHashMap`, and a very common interview topic.
>
> By default, `LinkedHashMap` remembers the order in which you insert items. However, you can activate a special **"access-order" mode** by using a specific constructor:
> `Map<K, V> lruCache = new LinkedHashMap<>(capacity, 0.75f, true);`
>
> When the `true` parameter is used:
> *   Whenever you call `get(key)`, that entry is moved to the **end** of the internal linked list.
> *   This means the entry that hasn't been accessed for the longest time (the "least recently used") will always be at the **head** of the list.
>
> This behavior makes implementing an **LRU (Least Recently Used) Cache** incredibly simple. An LRU cache is a cache that automatically evicts the least-used items when it reaches its capacity.

```java
// A simple LRU Cache implementation
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // The 'true' for accessOrder is the magic ingredient!
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // This method is called after a new entry is put.
        // If it returns true, the eldest entry is removed.
        return size() > capacity;
    }
}
```
Just by extending `LinkedHashMap` and overriding one method, you have a fully functional LRU Cache. This is a very impressive pattern to know for interviews.

---

### 📖 Method Cheatsheet

`LinkedHashMap` does not add any new public methods of its own. It uses the exact same methods as [`HashMap`](../2-HashMap/README.md). The only difference is the behavior of the iterators (`keySet().iterator()`, `values().iterator()`, `entrySet().iterator()`), which will return elements in **insertion order** (or access order, if configured).

---

### 💼 Office Scenarios

*   **LRU Cache:** The number one reason to use `LinkedHashMap`. Caching database results, images, or any data where you want to keep the most frequently used items and discard the old ones.
*   **Maintaining Order:** Storing key-value pairs where the order of insertion is important, for example, the fields in a JSON object that must be written out in a specific order.
*   **Historical Data:** Storing a history of user actions where you care about the sequence.

---

### 🎯 Interview Focus

*   **Q: When would you use `LinkedHashMap` over `HashMap`?**
    *   **A:** When I need the O(1) performance of a [`HashMap`](../2-HashMap/README.md) but also need to preserve the insertion order of the keys. The most important use case is for building an LRU cache.

*   **Q: How do you build an LRU cache with `LinkedHashMap`?**
    *   **A:** By creating a class that extends `LinkedHashMap`, calling the special constructor with `accessOrder = true`, and overriding the `removeEldestEntry` method to return `true` when the cache size exceeds its capacity.
