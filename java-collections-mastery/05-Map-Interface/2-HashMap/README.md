# 5.2 HashMap 🏃‍♂️ - The King of Key-Value Storage

Mawa, Collections lo [`ArrayList`](../../02-List-Interface/2-ArrayList/README.md) entha popularo, [`Map`](../README.md)s lo `HashMap` antha popular. It is the most fundamental and widely used `Map` implementation. When you need to store and retrieve data using a key, `HashMap` is almost always your first choice.

### The "Filing Cabinet" Analogy 🗄️

`HashMap` ni oka well-organized filing cabinet la oohinchuko.

*   **Key:** The label on each folder (e.g., `"EmployeeID-101"`, `"Config-DB_URL"`).
*   **Value:** The documents and information inside that folder (e.g., the `Employee` object, the URL string).
*   **Super Fast Lookup:** Nuvvu oka specific employee document kavali anukunte, cabinet antha vetakakkarledu. Just "EmployeeID-101" aney label chusi, direct ga aa folder teestav. This is why `get(key)` is **extremely fast (O(1) on average)**.
*   **No Order:** The folders are not arranged alphabetically. They are placed in drawers based on a system that makes them easy to find, but not in a predictable order. **`HashMap` does not guarantee any order for its keys.**

---

### How It Works: The `hashCode()` and `equals()` Contract on KEYS

Mawa, [`HashSet`](../../03-Set-Interface/2-HashSet/README.md) gurinchi manam nerchukunna `hashCode()`/`equals()` magic gurtunda? `HashSet` anedi internally `HashMap` eh use chestundi! The exact same logic applies here, but it applies to the **KEYS** of the map.

> 🔥 **Interviewers' Favourite:** Ee topic meeda complete mastery kosam, mana dedicated deep-dive guide ni chudu!
> ➡️ **[The Ultimate Guide to How HashMap Works Internally](./deep-dive/README.md)**

When you do `map.put(key, value)`:
1.  **Find the Bucket:** Java calls `key.hashCode()` to calculate a hash code. This code determines which "bucket" (index in an internal array) the entry should be stored in.
2.  **Check for Duplicates:** Java checks the bucket. If other entries are there (a collision), it uses `key.equals()` to see if any of the existing keys are truly equal to the new key.
    *   If `equals()` is `true`, it's a duplicate key. The **old value is replaced with the new value**.
    *   If `equals()` is `false` for all, it's a new key, and the new entry is added to the bucket.

Here's a simplified diagram of that logic:
```mermaid
graph TD
    A["map.put(key, value)"] --> B{Calculate `key.hashCode()`};
    B --> C{Calculate index `(hash & (n-1))`};
    C --> D[Find bucket at index];

    D --> E{Bucket empty?};
    E -- Yes --> F[Place new Node in bucket];

    E -- No --> G{Iterate through Nodes in bucket};
    G --> H{Found a Node where `node.key.equals(key)`?};
    H -- Yes --> I[Replace that Node's value with new value];
    H -- No --> J[Append new Node to end of list/tree in bucket];

    style F fill:#d5e8d4,stroke:#333,stroke-width:2px
    style I fill:#d5e8d4,stroke:#333,stroke-width:2px
    style J fill:#d5e8d4,stroke:#333,stroke-width:2px
```

> 💡 **The `hashCode()` and `equals()` Contract for Keys**
> If `key1.equals(key2)` is true, then `key1.hashCode()` **MUST** be equal to `key2.hashCode()`.
>
> If you break this rule for your key objects, the `HashMap` will not be able to find entries correctly. You might put a value in the map and then not be able to retrieve it, and you could even end up with duplicate keys.

➡️ **[Wait, I'm confused about this!](../../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md)** (Don't worry, we have a dedicated guide for the `hashCode`/`equals` contract and how it differs from `Comparable`.)

---

### Key Properties & Performance

| Feature | `HashMap` |
| :--- | :--- |
| **Order** | None (Chaotic) |
| **Performance** | **O(1) average** for `put`, `get`, `remove`, `containsKey`. |
| **Nulls Allowed?** | ✅ Yes (one `null` key, multiple `null` values) |
| **Internal Structure**| Array of nodes (buckets), where each node can be a linked list or a tree. |
| **Thread-Safety** | ❌ No |

> 💡 **`HashMap` and `null`**
> `HashMap` is one of the few map implementations that is flexible with `null`.
> *   You can have **one `null` key**.
> *   You can have **multiple `null` values**.
> This is different from [`TreeMap`](../4-TreeMap/README.md) and [`ConcurrentHashMap`](../5-ConcurrentHashMap/README.md), which do not allow `null` keys.

---

### 📖 Method Cheatsheet
| Method | Description |
| :--- | :--- |
| `V put(K key, V value)`* | Associates the value with the key. Replaces the old value if the key already exists. |
| `V get(Object key)`* | Returns the value for the given key, or `null` if not found. |
| `V remove(Object key)`* | Removes the mapping for the key. |
| `boolean containsKey(Object key)`*| Returns `true` if the map contains the key. (Fast, O(1)) |
| `boolean containsValue(Object value)`| Returns `true` if the map contains the value. (Slow, O(n)) |
| `int size()`* | Returns the number of key-value mappings. |
| `void clear()`* | Removes all mappings. |
| `V getOrDefault(Object key, V def)`*| Gets a value, or returns a default value if key not found. |
| `Set<K> keySet()`* | Returns a [`Set`](../../03-Set-Interface/README.md) view of all keys. Use this to iterate over keys. |
| `Collection<V> values()`* | Returns a [`Collection`](../../01-Foundation-Concepts/1-Collection-Framework-Architecture/README.md#methods-of-the-collection-interface) view of all values. |
| `Set<Map.Entry<K, V>> entrySet()`*| Returns a [`Set`](../../03-Set-Interface/README.md) view of the key-value pairs. |

> 💡 **Best Practice: Iterate with `entrySet()`**
> The most efficient way to iterate over a `HashMap` is by using the `entrySet()`. This is because it gives you both the key and the value at the same time, avoiding a separate `get(key)` lookup for each key.
> ```java
> for (Map.Entry<String, Integer> entry : map.entrySet()) {
>     String key = entry.getKey();
>     Integer value = entry.getValue();
>     // ... do something with key and value
> }
> ```

---

### 💼 Office Scenarios

*   **Caching:** The most common use case. Cache database results with the query as the key and the result set as the value.
*   **Frequency Counting:** Count occurrences of items in a list or words in a document. The item/word is the key, the count is the value.
*   **Data Lookup:** Store user data with `userId` as the key and the `User` object as the value for quick retrieval.
*   **Configuration:** Loading application properties from a file into a `Map<String, String>`.

---

### 🎯 Interview Focus

*   **Q: How does `HashMap` work internally?**
    *   **A:** This is the most important interview question. The short answer is: it uses the key's `hashCode()` to find a bucket and then `equals()` to handle collisions. For a complete, step-by-step explanation of the `put`, `get`, `resize`, and treeify logic, check out our **[Ultimate Deep-Dive Guide](./deep-dive/README.md)**.
*   **Q: What is the load factor and initial capacity?**
    *   **A:** **Initial Capacity** is the number of buckets in the map when it's created (default 16). The **Load Factor** is a measure of how full the map is allowed to get before its capacity is automatically increased (default 0.75). When `size > capacity * loadFactor`, the map **resizes**.
*   **Q: What happens when a `HashMap` resizes?**
    *   **A:**
        > ⚠️ **Resizing is an Expensive Operation**
        > When a `HashMap` resizes, a new underlying array is created (usually double the size). Then, **every single entry** in the old map must be re-hashed to find its new correct bucket in the new, larger array. This is an O(n) operation that can be slow if the map is very large. If you know you're going to store many items, it's a good practice to set an initial capacity to avoid multiple resizing operations.

*   **Q: Why should you use immutable objects for `HashMap` keys?**
    *   **A:**
        > ⚠️ **Never Use Mutable Objects for Keys**
        > This is the same critical rule as with [`HashSet`](../../03-Set-Interface/2-HashSet/README.md). If you use a mutable object as a key and then change its state after putting it in the map, its `hashCode()` will likely change.
        >
        > The `HashMap` will now look for the key in the **wrong bucket** (based on the new hash code), but the object is still sitting in the **old bucket**. You will "lose" the entry—you can't retrieve it, and you can't remove it. This leads to subtle bugs and memory leaks. Always use immutable keys like `String`, `Integer`, or your own immutable classes.
