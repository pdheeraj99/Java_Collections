# 🏅 Interview Questions Part 6: Performance & Edge Cases

Mawa, good developers ki great developers ki madhya unna theda performance gurinchi alochinchadam. Ee section lo, collections yokka performance tuning and edge cases meeda adige questions ni chuddam.

---

### 1. When is it better to use an `Array` instead of an `ArrayList`?

**Answer:**

`ArrayList` antha powerful ga unna, konni specific scenarios lo plain old `Array` eh better choice.

*   **Fixed Size:** Neeku telusu, nee collection lo eppudu correct ga 10 (or any fixed number) elements matrame untayi ani. Size asalu maaradu. Ee case lo, `ArrayList` yokka dynamic resizing overhead anavasaram.
*   **Performance with Primitives:** `ArrayList` anedi objects ni matrame store cheyagaladu. Ante, `ArrayList<int>` anedi possible kaadu; `ArrayList<Integer>` ani vaadali. Idi boxing/unboxing valla chinna performance overhead create chestundi. Nuvvu million-and-millions of `int`s tho pani chestu, prathi microsecond count aithe, `int[]` array vaadadam chala faster.
*   **Multidimensional Arrays:** Two-dimensional (or more) data ni represent cheyadaniki, `int[][] matrix` lanti arrays chala natural ga and efficient ga untayi, `List<List<Integer>>` kanna.

**Rule of Thumb:** If size is fixed and known, and you are working with primitives where performance is absolutely critical, consider an `Array`. For everything else, the flexibility of an `ArrayList` is better.

---

### 2. Explain the importance of setting an initial capacity for a `HashMap`.

**Answer:**

`HashMap` create chesetappudu initial capacity set cheyyadam anedi oka simple kani powerful performance tuning technique.

**The Problem:**
Default ga, `HashMap` capacity 16 tho start avtundi. Nuvvu elements add chestu pothe, `size > capacity * loadFactor` (e.g., `12 > 16 * 0.75`) condition reach ayinapudu, adi **resize** avtundi.

**Resizing is Expensive:**
Ee `resize()` operation lo, `HashMap` oka kottha, double-size array ni create chesi, paatha array lo unna **prathi single entry ni re-hash** chesi, kottha array lo correct bucket lo place chestundi. Idi O(n) operation. Nuvvu 1 million items add chestunte, `HashMap` chala sarlu resize avvochu, prathi sari pedda performance hit teeskuntundi.

**The Solution:**
Neeku mundhe telusu, nuvvu oka 1 million items store cheyyali ani. Appudu, `HashMap` ni correct initial capacity tho create cheyyadam better.

```java
// Bad: This will resize many times.
Map<String, String> map = new HashMap<>();

// Good: Give it enough space from the start.
// Formula: (targetSize / loadFactor) + 1
int initialCapacity = (int) (1_000_000 / 0.75f) + 1;
Map<String, String> map = new HashMap<>(initialCapacity);
```
Ee chinna change tho, manam expensive `resize()` operations ni completely avoid chesi, `put` operations ni consistently fast (O(1)) ga unchutunnam.

---

### 3. What is the time complexity of `HashMap.containsKey()` vs. `ArrayList.contains()`? Why?

**Answer:**

This question tests your understanding of the internal data structures.

*   **`HashMap.containsKey(key)`:**
    *   **Complexity:** **O(1)** on average.
    *   **Why:** `HashMap` hashing aney magic ni use chestundi. Adi `key.hashCode()` ni teeskuni, direct ga ఏ bucket lo vetakalo calculate chestundi. So, list antha vetakalsina pani ledu. Average case lo, idi constant time operation. (Worst case, with many collisions, it can be O(n)).

*   **`ArrayList.contains(object)`:**
    *   **Complexity:** **O(n)**.
    *   **Why:** `ArrayList` lopalana simple array untundi. Daaniki hashing concept teliyadu. So, `contains` call chesinapudu, adi list lo unna **prathi element** tho (`object.equals(element)`) compare cheskuntu veltundi, from start to end. List lo 'n' elements unte, adi 'n' comparisons chestundi.

**Conclusion:** Key-based lookup kosam, `HashMap` anedi `ArrayList` kanna exponentially faster.

---

### 4. If you have a `List` of custom objects and call `list.remove(myObject)`, what needs to be implemented correctly?

**Answer:**

`list.remove(myObject)` correct ga pani cheyyadaniki, nee custom object class lo **`equals(Object obj)`** method ni correctly override cheyyali.

**Why?**
The `remove(Object o)` method iterates through the `ArrayList` and compares the input object `o` with each element in the list using the `o.equals(element)` method.

*   **If you DON'T override `equals()`:** Your class will use the default `Object.equals()` method. Ee default method kevalam reference equality ni check chestundi (`==`). Ante, rendu references oke object ni point cheste matrame `true` istundi.
    ```java
    // Without equals() override
    Person p1 = new Person("Jules");
    Person p2 = new Person("Jules");
    list.add(p1);
    list.remove(p2); // This will FAIL! Because p1 != p2
    ```
*   **If you DO override `equals()`:** Nuvvu nee own logic rayochu, for example, "id's same unte, ee objects equal".
    ```java
    // With a proper equals() override based on name
    Person p1 = new Person("Jules");
    Person p2 = new Person("Jules");
    list.add(p1);
    list.remove(p2); // This will WORK! Because p1.equals(p2) is true
    ```
So, without a proper `equals()` implementation, `remove(Object o)` will not be able to find and remove the object you intend to.

---

### 5. What is the difference between `Collections.emptyList()` and `new ArrayList<>()`?

**Answer:**

Rendu empty list laage kanipinchina, vella madhya performance and behavior lo theda undi.

*   **`new ArrayList<>()`:**
    *   **Object Creation:** Prathi sari kottha `ArrayList` object ni create chestundi. Heap lo kottha memory allocate avtundi.
    *   **Mutability:** Idi **mutable**. Nuvvu deeniki `add()` cheyochu.
    *   **Use Case:** Neeku oka empty list kavali, kani tarvata daaniki elements add cheyyali anukunnapudu.

*   **`Collections.emptyList()`:**
    *   **Object Creation:** Idi eppudu, oke oka **static final immutable** empty list instance ni return chestundi. No new object is created.
    *   **Mutability:** Idi **immutable**. Nuvvu deeniki `add()` cheyadaniki try cheste, `UnsupportedOperationException` vastundi.
    *   **Use Case:** Oka method nunchi empty list ni return cheyyali, kani aa caller aa list ni modify cheyyakudadu anukunnapudu idi perfect. It's also slightly more memory efficient because it doesn't create new objects.

**Example:**
```java
public List<String> getRolesForUser(User user) {
    if (user.isAdmin()) {
        return List.of("ADMIN", "USER"); // Or a new ArrayList
    } else {
        // Don't create a new object every time for no reason.
        // Just return the shared, immutable, empty list.
        return Collections.emptyList();
    }
}
```
