# Phase 5: The Map Interface 🗺️

Welcome to the final and most powerful phase, mawa! Ikkada manam `Map` interface gurinchi nerchukundam.

Unlike `Collection`, a `Map` does not store single elements. Instead, it stores pairs of **keys and values** (`key-value` pairs). Prathi key anedi unique ga undali.

**The "Dictionary" Analogy 📖:**
The best way to think of a `Map` is like a dictionary.
*   **Key:** The word you look up (e.g., "Java"). Keys must be unique.
*   **Value:** The definition of that word (e.g., "A high-level, object-oriented programming language.").
*   You use the key to find the value. You don't look up a definition to find the word. This lookup is extremely fast.

**Key Features of a `Map`:**
*   **Key-Value Pairs:** Stores data as key-value pairs.
*   **Unique Keys:** A map cannot contain duplicate keys. If you try to put a new value with an existing key, the old value will be replaced.

> 💡 **`Map` is Not a `Collection`**
> A very important point: `Map` is a top-level interface and **does not** extend the [`Collection`](../01-Foundation-Concepts/1-Collection-Framework-Architecture/README.md#methods-of-the-collection-interface) interface. They are two separate branches of the Java Collections Framework. This is why you can't, for example, pass a `Map` directly to a method that expects a `Collection`. You can, however, get `Collection` *views* from a map using `keySet()`, `values()`, or `entrySet()`.

Ee phase lo manam kindi implementations gurinchi nerchukundam:
*   [**5.2 HashMap**](./2-HashMap/README.md) 🏃‍♂️ (The most common, fastest map; unordered)
*   [**5.3 LinkedHashMap**](./3-LinkedHashMap/README.md) 🔗 (Maintains insertion order of keys)
*   [**5.4 TreeMap**](./4-TreeMap/README.md) 🌳 (Keeps keys sorted)
*   [**5.5 ConcurrentHashMap**](./5-ConcurrentHashMap/README.md) 🚀 (The thread-safe choice for maps)

---

### ✨ Methods of the `Map` Interface

#### 1. Abstract Methods (The Contract `Map` Promises 📜)
| Method | Description |
| :--- | :--- |
| `V put(K key, V value)`* | Associates the value with the key. If key already exists, old value is replaced. |
| `V get(Object key)`* | Returns the value for the given key, or `null` if key not found. |
| `V remove(Object key)`* | Removes the mapping for a key. |
| `boolean containsKey(Object key)`*| Returns `true` if the map contains a mapping for the key. |
| `boolean containsValue(Object value)`| Returns `true` if the map maps one or more keys to the value. (Slow) |
| `int size()`* | Returns the number of key-value mappings. |
| `boolean isEmpty()`* | Returns `true` if the map is empty. |
| `void clear()`* | Removes all mappings from the map. |
| `Set<K> keySet()`* | Returns a [`Set`](../03-Set-Interface/README.md) view of all the keys. |
| `Collection<V> values()`* | Returns a [`Collection`](../01-Foundation-Concepts/1-Collection-Framework-Architecture/README.md#methods-of-the-collection-interface) view of all the values. |
| `Set<Map.Entry<K, V>> entrySet()`*| Returns a [`Set`](../03-Set-Interface/README.md) view of the key-value mappings (`Map.Entry`). |

#### 2. Default Methods (The Free Helpers 🎁 - Java 8+)
| Method | Description |
| :--- | :--- |
| `V getOrDefault(Object key, V defaultValue)`* | Gets the value for a key, but returns a default value if key is not found. |
| `V putIfAbsent(K key, V value)` | Puts a value only if the key is not already present. |
| `boolean remove(Object key, Object value)` | Removes the entry for a key only if it is currently mapped to the given value. |
| `boolean replace(K key, V oldValue, V newValue)`| Replaces the entry for a key only if currently mapped to the given old value. |
| `void forEach(BiConsumer<? super K, ? super V> action)`*| Performs an action for each entry. |

#### 3. Static Methods (The Utilities 🛠️ - Java 9+)
| Method | Description |
| :--- | :--- |
| `static <K,V> Map<K,V> of(...)`* | Creates an **immutable** map with a fixed number of entries. |
| `static <K,V> Map<K,V> copyOf(Map m)`| Creates an immutable map from an existing map. |
| `static <K,V> Map.Entry<K,V> entry(K k, V v)`| Creates an immutable `Map.Entry` object. |

---

## When to Use Which Map? (The Ultimate Summary) 🤔

| Scenario | Recommendation | Why? (The Simple Reason) |
| :--- | :--- | :--- |
| **I need the fastest possible key-value storage. Order doesn't matter.** | [`HashMap`](./2-HashMap/README.md) | **The king of speed.** O(1) average time for `get`/`put`. The general-purpose default choice. |
| **I need to iterate through the entries in the order I inserted them.** | [`LinkedHashMap`](./3-LinkedHashMap/README.md) | Almost as fast as `HashMap`, but also maintains insertion order with an internal linked list. |
| **I need to build a simple LRU Cache.** | [`LinkedHashMap`](./3-LinkedHashMap/README.md) | Its special `accessOrder` constructor makes this incredibly easy. A classic use case. |
| **I need the map to be constantly sorted by its keys.** | [`TreeMap`](./4-TreeMap/README.md) | The only choice for sorted maps. Essential for range queries (e.g., "get all users with names starting from A-D"). |
| **I need a thread-safe map for a high-concurrency environment.** | [`ConcurrentHashMap`](./5-ConcurrentHashMap/README.md) | The modern, high-performance solution for concurrent access. Vastly superior to `Hashtable`. |
| **I need a small, unchangeable map.** | `Map.of(...)` | Creates an immutable `Map`. Safe and concise for constants or configuration. |

> 💡 **The Golden Rule:** Neeku em vaadalo a specific reason teliyanapudu, **start with [`HashMap`](./2-HashMap/README.md)**. It's the most common, general-purpose map and is usually the right choice for key-value storage.
