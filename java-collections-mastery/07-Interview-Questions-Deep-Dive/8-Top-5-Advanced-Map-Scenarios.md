# 🏅 Interview Questions Part 8: Advanced `Map` Scenarios

Mawa, basic `Map` questions tarvata, interviewers vella meeda advanced scenarios and design patterns adugutaru. Ee section lo, alanti questions ni cover cheddam.

---

### 1. Explain the `computeIfAbsent()` method in `Map`. How can it be used to create a multi-value map?

**Answer:**

`computeIfAbsent()` is a powerful method introduced in Java 8. It allows you to compute a value and associate it with a key **only if** the key is not already present in the map. This is an atomic operation, making it great for concurrent environments.

**Use Case: Creating a Multi-Value Map (`Map<K, List<V>>`)**
Imagine you want to group a list of strings by their first letter. The map's key would be the character, and the value would be a list of strings starting with that character.

**The Old Way (before Java 8):**
```java
Map<Character, List<String>> map = new HashMap<>();
for (String s : names) {
    char firstChar = s.charAt(0);
    List<String> list = map.get(firstChar);
    if (list == null) {
        list = new ArrayList<>();
        map.put(firstChar, list);
    }
    list.add(s);
}
```
Ee code verbose ga undi and thread-safe kaadu.

**The Modern Way (with `computeIfAbsent`):**
```java
Map<Character, List<String>> map = new HashMap<>();
for (String s : names) {
    char firstChar = s.charAt(0);
    // If the key is not present, it creates a new ArrayList,
    // puts it in the map, and returns it.
    // If the key is present, it just returns the existing list.
    map.computeIfAbsent(firstChar, k -> new ArrayList<>()).add(s);
}
```
This code is concise, expressive, and much cleaner. `k -> new ArrayList<>()` is the mapping function that gets called only when the key is absent.

---

### 2. You have a `Map<String, Integer>`. How would you sort it by its values?

**Answer:**

This is a very common interview question. `HashMap` itself cannot be sorted. `TreeMap` sorts by keys, not values. So, the solution is to:
1.  Get the `entrySet()` of the map.
2.  Put the entries into a `List`.
3.  Sort the `List` using a custom `Comparator` that compares the values.

**The Code:**
```java
Map<String, Integer> unsortedMap = new HashMap<>();
unsortedMap.put("A", 5);
unsortedMap.put("B", 1);
unsortedMap.put("C", 3);

// 1. Get the entrySet and put it into a List
List<Map.Entry<String, Integer>> entryList = new ArrayList<>(unsortedMap.entrySet());

// 2. Sort the list using a Comparator on the values
// Using a lambda for the Comparator
entryList.sort((e1, e2) -> e1.getValue().compareTo(e2.getValue()));

// 3. Optional: Put it back into a LinkedHashMap to preserve the sorted order
Map<String, Integer> sortedMap = new LinkedHashMap<>();
for (Map.Entry<String, Integer> entry : entryList) {
    sortedMap.put(entry.getKey(), entry.getValue());
}

System.out.println("Sorted by value: " + sortedMap);
// Output: {B=1, C=3, A=5}
```
Using Java 8 Streams, this can be done more concisely:
```java
Map<String, Integer> sortedMapStream = unsortedMap.entrySet().stream()
    .sorted(Map.Entry.comparingByValue())
    .collect(Collectors.toMap(
        Map.Entry::getKey,
        Map.Entry::getValue,
        (e1, e2) -> e1,
        LinkedHashMap::new
    ));
```

---

### 3. What is a `NavigableMap` and what kind of problems does it solve?

**Answer:**

`NavigableMap` is a sub-interface of `SortedMap`. It adds powerful navigation methods that allow you to search for entries based on proximity to a given key. `TreeMap` is the primary implementation.

**Problems it solves:**
A `SortedMap` can only give you the first key, the last key, or a subset. `NavigableMap` can answer much more complex questions:
*   "What's the entry with the **closest key less than or equal to** my key?" -> `floorEntry()`
*   "What's the entry with the **closest key strictly less than** my key?" -> `lowerEntry()`
*   "What's the entry with the **closest key greater than or equal to** my key?" -> `ceilingEntry()`
*   "What's the entry with the **closest key strictly greater than** my key?" -> `higherEntry()`

It also allows you to iterate the map in descending order (`descendingMap()`) and get a "navigable" view of the keys (`navigableKeySet()`).

**Use Case:**
Imagine a `TreeMap` storing stock prices at different timestamps. `NavigableMap` methods would allow you to easily find the price at the exact time of a trade, or the price immediately before or after if no exact match exists.

---

### 4. How could you use a `TreeMap` to implement a time-based key-value store?

**Answer:**

This is a great system design question that directly maps to `TreeMap`'s features. A time-based key-value store needs to not only store data at specific timestamps but also retrieve data for a given time range.

1.  **The Data Structure:** Use a `TreeMap<LocalDateTime, String>` where the key is the timestamp and the value is the event data. `TreeMap` will automatically keep the entries sorted by time.

2.  **Implementation:**
    *   **Storing data:** Use the standard `put(key, value)` method.
    *   **Retrieving data in a range:** This is where `TreeMap` shines. Use the `subMap(fromKey, toKey)` method. It returns a "view" of the map containing all the entries within the specified time range. This operation is very efficient.

**The Code:**
```java
NavigableMap<LocalDateTime, String> eventLog = new TreeMap<>();
LocalDateTime now = LocalDateTime.now();

eventLog.put(now.minusHours(1), "User logged in");
eventLog.put(now, "User placed order");
eventLog.put(now.plusHours(1), "Order shipped");
eventLog.put(now.plusHours(2), "Order delivered");

// Get all events that happened in the last 90 minutes
LocalDateTime ninetyMinsAgo = now.minusMinutes(90);
SortedMap<LocalDateTime, String> recentEvents = eventLog.tailMap(ninetyMinsAgo);

System.out.println("Recent Events: " + recentEvents);
// Output: {<now_timestamp>=User placed order, <now+1h>=Order shipped, <now+2h>=Order delivered}
```

---

### 5. Explain the purpose of the `merge()` method in `Map` and provide a use case.

**Answer:**

The `merge()` method, introduced in Java 8, is a powerful atomic operation used to update a value in a map based on its previous value. It's especially useful for aggregating data.

**Signature:**
`V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)`

**How it works:**
1.  If the `key` is not present, it simply does `put(key, value)`.
2.  If the `key` is present with an `oldValue`, it uses the `remappingFunction` to compute a `newValue` from the `oldValue` and the given `value`. The result is then put back into the map.

**Use Case: Frequency Counting**
The most common use case is counting occurrences of items, like words in a document.

**The Old Way:**
```java
Map<String, Integer> counts = new HashMap<>();
for (String word : words) {
    Integer count = counts.get(word);
    if (count == null) {
        counts.put(word, 1);
    } else {
        counts.put(word, count + 1);
    }
}
```

**The Modern Way (with `merge()`):**
```java
Map<String, Integer> counts = new HashMap<>();
for (String word : words) {
    // If key is new, put 1.
    // If key exists, apply the function (oldValue + 1).
    counts.merge(word, 1, (oldValue, newValue) -> oldValue + newValue);
    // Or even simpler with a method reference:
    // counts.merge(word, 1, Integer::sum);
}
```
The `merge()` version is more concise, expressive, and importantly, it can be an atomic operation in concurrent maps, making it safer for concurrent code.
