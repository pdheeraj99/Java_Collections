# 2.2 ArrayList 📊 - The Resizable Array

Mawa, `ArrayList` is the rockstar of [Java Collections](../../01-Foundation-Concepts/1-Collection-Framework-Architecture/README.md). 90% of the time neeku oka list kavali ante, nuvvu `ArrayList` eh vaadathav. Let's understand why.

### The "Passenger Train" Analogy 🚆

`ArrayList` ni oka passenger train la oohinchuko.

*   **Internal Structure:** The train has compartments connected in a sequence. This is like the **internal array** of an `ArrayList`.
*   **Fast Access (`get`):** Prathi compartment ki oka number untadi (index). Nuvvu direct ga "Naaku 5th compartment kavali" ani cheppi vellipovachu. This is very fast, so `get(index)` is an **O(1)** operation.
*   **Adding at the End (`add`):** Train ki chivarilo oka kottha passenger ni ekkinchukovadam chala easy. Just last compartment lo add cheyali. This is also fast, an **[amortized O(1)](../../00-Key-Concepts/README.md#4-amortized-time-complexity-⏰)** operation.
*   **Adding in the Middle (`add(index, element)`):** Ippudu kashtam start avtundi. Nuvvu 2nd compartment lo oka passenger ni add cheyali anuko. Appudu, 2nd compartment nunchi last varaku unna passengers andaru okko compartment munduku (right side ki) shift avvali. Idi chala pani, anduke idi slow. It's an **O(n)** operation. `remove()` kuda inthe, andaru venakki (left side ki) shift avvali.
*   **Dynamic Resizing (Adding a new Carriage):** Train antha nindipoyindi anuko. Appudu inkoka passenger vasthe? Railway department ventane train ki oka kottha carriage (bogie) ni add chestundi. `ArrayList` kuda anthe. Adi full aipothe, adi **pedda size unna kottha array** create chesi, paatha elements anni deeniloki copy chesi, paatha array ni delete chestundi. Ee new array size paatha daani కన్నా 50% ekkuva untundi (oldCapacity * 1.5).

---

### Key Features

| Feature | Description | Analogy |
| :--- | :--- | :--- |
| **Internal Structure** | Dynamic Array | A train with compartments |
| **Ordering** | Maintains insertion order | Passengers stay in the order they boarded |
| **Duplicates** | ✅ Allows duplicate elements | Oke name unna iddari passengers undochu |
| **Random Access** | ✅ Super fast (`O(1)`) | Direct ga compartment number tho velladam |
| **Add/Remove (Middle)**| ❌ Very slow (`O(n)`) | Passengers andaru shift avvali |
| **Thread Safety** | ❌ No | Train ki security ledu, evaraina eppudaina ravochu |
| **Null Values** | ✅ Allows null values | "No passenger" aney oka compartment undochu |

---

### Performance (Time Complexity)

| Operation | Complexity | Why? |
| :--- | :--- | :--- |
| `add(element)` | Amortized O(1) | Usually fast, but sometimes slow if it needs to resize. |
| `get(index)` | O(1) | Direct memory address calculation. Super fast. |
| `add(index, element)` | O(n) | All subsequent elements must be shifted. |
| `remove(index)` | O(n) | All subsequent elements must be shifted. |
| `contains(element)` | O(n) | Has to check each element one by one. |
| `size()` | O(1) | It's just a counter variable. |

---

### 📖 Method Cheatsheet

Here's a categorized list of `ArrayList` methods. Most used methods are marked with a `*`.

#### 1. Basic Operations (Adding, Accessing, Modifying)
| Method | Description |
| :--- | :--- |
| `add(E element)`* | List chivarilo element ni add chestundi. |
| `add(int index, E element)` | List lo specific index lo element ni add chestundi (slow). |
| `get(int index)`* | Specific index lo unna element ni istundi (fast). |
| `set(int index, E element)`* | Specific index lo unna element ni replace chestundi. |
| `remove(int index)`* | Specific index lo unna element ni remove chestundi (slow). |
| `remove(Object o)`* | First matching element ni remove chestundi (slow). |
| `size()`* | List lo enni elements unnayo cheptundi. |
| `isEmpty()`* | List empty ga unda leda ani cheptundi. |

#### 2. Bulk Operations (Working with other Collections)
| Method | Description |
| :--- | :--- |
| `addAll(Collection c)`* | Vere collection lo unna elements anni add chestundi. |
| `addAll(int index, Collection c)` | Vere collection elements ni specific index nunchi add chestundi. |
| `removeAll(Collection c)` | Vere collection lo unna matching elements anni remove chestundi. |
| `retainAll(Collection c)` | Vere collection lo unna elements ni matrame unchi, migathavi remove chestundi. |
| `clear()`* | List lo unna anni elements ni remove chestundi. |
| `removeIf(Predicate filter)` | Oka condition (predicate) match ayye anni elements ni remove chestundi. |

#### 3. Search Operations
| Method | Description |
| :--- | :--- |
| `contains(Object o)`* | Element list lo unda leda ani `true`/`false` istundi. |
| `indexOf(Object o)` | Element yokka first occurrence index ni istundi (-1 if not found). |
| `lastIndexOf(Object o)` | Element yokka last occurrence index ni istundi (-1 if not found). |

#### 4. Conversion & Iteration
| Method | Description |
| :--- | :--- |
| `toArray()` | List ni `Object[]` array la convert chestundi. |
| `toArray(T[] a)` | List ni specified type array (`T[]`) la convert chestundi. |
| `iterator()`* | List meeda iterate cheyadaniki `Iterator` ni istundi. (See our lesson on the [Iterator Pattern](../../01-Foundation-Concepts/2-Iterator-Pattern/README.md)) |
| `listIterator()` | `ListIterator` ni istundi (forward/backward traversal). (See our lesson on the [Iterator Pattern](../../01-Foundation-Concepts/2-Iterator-Pattern/README.md)) |
| `forEach(Consumer action)` | Prathi element meeda oka action perform cheyadaniki. |

#### 5. View Operations
| Method | Description |
| :--- | :--- |
| `subList(int from, int to)` | List lo oka bhagam ni `List` view la istundi. Original list lo changes reflect avtayi. |

#### 6. Capacity Management
| Method | Description |
| :--- | :--- |
| `ensureCapacity(int minCapacity)` | `ArrayList` capacity ni penchutundi (performance tuning). |
| `trimToSize()` | Unused capacity ni remove chestundi (memory saving). |

#### 7. Object Methods
| Method | Description |
| :--- | :--- |
| `clone()` | List yokka shallow copy ni create chestundi. |

---

### 💼 Office Scenarios: When to use ArrayList?

Simple rule: **Read ekkuva, Write takkuva** unte, `ArrayList` is your best friend.
*   **Displaying Data:** Database nunchi vachina employee records ni UI lo chupinchadaniki. Read operation eh ga.
*   **Caching:** Read-only configuration data ni cache cheyadaniki.
*   **API Responses:** External API nunchi vachina list of items ni store cheskovadaniki.

---

### 🎯 Interview Focus

*   **Q: `ArrayList` vs. a normal `Array`?**
    *   **A:** Array has a fixed size decided at creation. `ArrayList` is dynamic; its size can grow or shrink at runtime.

*   **Q: How does `ArrayList` resize internally?**
    > ⚠️ **Key Interview Insight: The Resizing Operation**
    > When an `ArrayList` is full, it doesn't just add one more slot. It creates a **brand new, larger array** (the new capacity is typically `oldCapacity + (oldCapacity >> 1)`, which is `1.5x` the old size). Then, it copies all elements from the old array to the new one using `Arrays.copyOf()`. This resizing process is an **O(n) operation**, and it's the reason why `add` is described as *amortized* O(1) time—it's usually fast, but occasionally it's slow.

*   **Q: What is the default initial capacity?**
    *   **A:** If you use `new ArrayList<>()`, the initial capacity is 0 (it becomes 10 on the first `add`). If you use `new ArrayList<>(initialCapacity)`, you can set your own.

*   **Q: `ArrayList` vs. `LinkedList`?**
    *   **A:** (Teaser for the next section!) `ArrayList` is fast for random access (`get`). `LinkedList` is fast for adding/removing elements from the beginning or middle. We'll see this in detail in the [next lesson on LinkedList](../3-LinkedList/README.md)!
