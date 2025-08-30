# 🏅 Interview Questions Part 3: Map Scenarios & Edge Cases

Mawa, `Map` anedi system design and coding rounds lo chala common ga vastundi. Ee section lo manam `Map` meeda adige konni advanced and scenario-based questions ni chuddam.

---

### 1. *Design Question:* How would you implement a simple LRU (Least Recently Used) Cache?

**Answer:**

This is a classic FAANG interview question. The best answer uses `LinkedHashMap`.

`LinkedHashMap` ki oka special constructor undi, adi `accessOrder` ni `true` ga set chestundi. Ee mode lo, oka entry ni `get()` chesinapudu, adi ventane list lo chivariki move avtundi. Ante, list lo mundu eppudu "least recently used" item untundi.

Ee feature ni use cheskuni, manam `removeEldestEntry()` method ni override chesi, cache full ayinapudu oldest entry ni remove cheyochu.

**The Code:**

```java
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // The 'true' for accessOrder is the magic ingredient!
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // This method is called by put and putAll after inserting a new entry.
        // It returns true if the eldest entry should be removed.
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        System.out.println("Cache: " + cache); // {1=One, 2=Two, 3=Three}

        cache.get(1); // Accessing 1, making it most recently used
        System.out.println("Accessed 1. Cache: " + cache); // {2=Two, 3=Three, 1=One}

        cache.put(4, "Four"); // This should evict the least recently used item (2)
        System.out.println("Added 4. Cache: " + cache); // {3=Three, 1=One, 4=Four}
    }
}
```

**Why it works:**
By extending `LinkedHashMap` and overriding just one method, we get a fully functional, efficient LRU cache. This is a very elegant solution that interviewers love to see.

---

### 2. What is the difference between `HashMap`, `Hashtable`, and `ConcurrentHashMap`?

**Answer:**

Ee moodu key-value pairs ni store chestayi, kani vella madhya main difference **thread-safety** and **performance**.

| Feature | `HashMap` | `Hashtable` (Legacy) | `ConcurrentHashMap` |
| :--- | :--- | :--- | :--- |
| **Thread-Safety** | ❌ No | ✅ Yes | ✅ Yes |
| **Locking** | No lock | **Single, map-wide lock** | **Fine-grained lock** (per bucket) |
| **Performance** | Fastest (single-threaded) | Slowest (due to single lock) | High (multi-threaded) |
| **Null Keys/Values**| ✅ One null key, multiple null values | ❌ No null keys or values | ❌ No null keys or values |
| **Iterator** | Fail-Fast | Fail-Fast | **Fail-Safe** |
| **Modern Usage** | Default choice for non-concurrent scenarios. | **Avoid.** Use `ConcurrentHashMap` instead. | Default choice for concurrent scenarios. |

**Summary:**
*   `HashMap` is for single-threaded applications.
*   `Hashtable` is an outdated, slow, thread-safe version. Don't use it.
*   `ConcurrentHashMap` is the modern, high-performance, thread-safe choice.

---

### 3. What is an `IdentityHashMap` and when would you use it?

**Answer:**

`IdentityHashMap` anedi `Map` interface yokka oka special implementation.

Normal `HashMap` lo, keys ni compare cheyadaniki `key.equals()` method vaadutundi.
`IdentityHashMap` lo, keys ni compare cheyadaniki `==` operator vaadutundi.

*   `key1.equals(key2)`: Checks if the two objects are **meaningfully equal**.
*   `key1 == key2`: Checks if the two references point to the **exact same object** in memory.

**When to use it?**
You use it in rare scenarios where you need to distinguish between two objects that are `.equals()` but are different instances. For example, in object serialization, graph traversal, or to store metadata about specific object instances without worrying about their `equals` implementation.

```java
Map<String, String> hashMap = new HashMap<>();
Map<String, String> identityMap = new IdentityHashMap<>();

String s1 = new String("key");
String s2 = new String("key");

System.out.println("s1.equals(s2): " + s1.equals(s2)); // true
System.out.println("s1 == s2: " + (s1 == s2));       // false

hashMap.put(s1, "Value1");
hashMap.put(s2, "Value2"); // Replaces the entry because s1.equals(s2) is true
System.out.println("HashMap size: " + hashMap.size()); // 1

identityMap.put(s1, "Value1");
identityMap.put(s2, "Value2"); // Adds a new entry because s1 != s2
System.out.println("IdentityHashMap size: " + identityMap.size()); // 2
```

---

### 4. What is a `WeakHashMap` and what is its primary use case?

**Answer:**

`WeakHashMap` is another special `Map` implementation that has a unique relationship with the Garbage Collector (GC).

In a normal `HashMap`, the map holds a **strong reference** to its keys. Ante, aa key-value pair map lo unnanta varaku, aa key object ni GC clean cheyyaledu, even if no other part of your application is using it. This can cause memory leaks.

In a `WeakHashMap`, the map holds a **weak reference** to its keys.
*   **How it works:** A weak reference does not prevent an object from being garbage collected. If a key in a `WeakHashMap` is no longer referenced anywhere else in the application, the GC is free to remove it from memory. The next time the `WeakHashMap` is accessed, it will notice that the key has been collected and will remove the entire entry.

**Primary Use Case:**
**Caching.** `WeakHashMap` is perfect for building simple caches for data that is expensive to create. You can cache the data, and if the application runs low on memory, the GC can automatically clear the cache by collecting the keys, thus freeing up memory. It's a self-cleaning cache.

---

### 5. What is an `EnumMap` and why is it more efficient than a `HashMap` for enum keys?

**Answer:**

`EnumMap` is a highly specialized and optimized `Map` implementation just for `enum` keys.

*   **How it works:** Instead of using a hash table, `EnumMap` is internally represented as a simple **array**. The size of the array is equal to the number of constants in the enum.
*   When you `put(MyEnum.CONSTANT, value)`, the map uses the `enum` constant's `ordinal()` value (its position in the enum declaration, e.g., 0, 1, 2...) as a direct index into the internal array.

**Why is it more efficient?**
1.  **No `hashCode()` Calculation:** It doesn't need to calculate any hash codes.
2.  **No Collisions:** Since each enum constant has a unique ordinal, there are zero hash collisions to deal with.
3.  **Performance:** All basic operations (`put`, `get`) are O(1) and are extremely fast, often faster than `HashMap` because there's no overhead of hashing or collision resolution.

**Rule of Thumb:** If your map's keys are an `enum`, always use `EnumMap`. Don't even think about `HashMap`.
