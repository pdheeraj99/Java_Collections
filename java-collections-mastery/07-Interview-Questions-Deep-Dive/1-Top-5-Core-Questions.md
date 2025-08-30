# 🏅 Interview Questions Part 1: Core & Foundational

Mawa, ee section lo manam the most fundamental and frequently asked collections interview questions ni cover cheddam. Ee concepts meeda grip unte, nuvvu ఏ collection-based question ayina answer cheyagalav.

---

### 1. What is the difference between `fail-fast` and `fail-safe` iterators?

**Answer:**

Idi concurrency-related chala important question.

*   **Fail-Fast Iterator:**
    *   **Analogy:** The "Strict Guard".
    *   **How it works:** Ee iterator direct ga original collection meeda pani chestundi. Ala iterate chestunnapudu, collection structure marithe (vere thread `add` or `remove` cheste), ee "strict guard" ki kopam vachi, ventane `ConcurrentModificationException` throw chestadu.
    *   **Why:** To prevent unpredictable behavior from working on a collection that's changing under the hood. It fails *fast* instead of giving a potentially wrong result.
    *   **Examples:** `ArrayList`, `LinkedList`, `HashSet`, `HashMap` yokka iterators.

*   **Fail-Safe Iterator:**
    *   **Analogy:** The "Smart Guard".
    *   **How it works:** Ee iterator original collection meeda pani cheyyadu. Daaniki బదులుగా, adi collection yokka oka **copy** or **snapshot** meeda pani chestundi. So, iteration start ayyaka original collection lo em changes jarigina, ee iterator ki sambandam ledu.
    *   **Why:** To provide a safe traversal without exceptions in a concurrent environment. The trade-off is that the data might be stale (not up-to-date).
    *   **Examples:** `ConcurrentHashMap`, `CopyOnWriteArrayList` yokka iterators.

---

### 2. What is the `hashCode()` and `equals()` contract?

**Answer:**

`HashMap`, `HashSet`, and `Hashtable` lanti hash-based collections correct ga pani cheyyadaniki, ee contract chala critical. Idi 2 rules tho untundi:

1.  **Rule 1:** Rendu objects `a.equals(b)` prakaram `true` aithe, vaati `a.hashCode()` and `b.hashCode()` **kacchitanga equal ga undali**.
2.  **Rule 2:** Rendu objects `a.hashCode()` and `b.hashCode()` equal ga unte, avi `a.equals(b)` prakaram `true` avvalsina avasaram **ledu**. (Idi "hash collision" antaru).

**Why is it important?**
`HashMap` lanti collections, first `hashCode()` ni use chesi oka object ni ఏ bucket lo pettalo decide chestayi. Tarvata, aa bucket lo unna vere objects tho `equals()` use chesi duplicate aa kada ani check chestayi.

Nuvvu ee contract ni break cheste (e.g., equal objects ki different hash codes isthe), `HashMap` confuse aipotundi. Nuvvu `put` chesina object ni `get` chesinapudu adi dorakadu, endukante `HashMap` veredo bucket lo vetukutundi.

> 🔥 **Master this concept:** Ee topic paina inka clarity kosam, mana **[HashMap Internals Deep-Dive](../../05-Map-Interface/2-HashMap/deep-dive/README.md)** chudu.

---

### 3. What happens internally if two different keys in a `HashMap` have the same `hashCode()`?

**Answer:**

This is called a **hash collision**. `HashMap` was designed to handle this perfectly.

1.  **Find the Bucket:** Rendu keys ki oke `hashCode` vachina, `(n-1) & hash` formula valla, avi rendu **oke bucket index** ki map avtayi.
2.  **Check the Bucket:** `HashMap` aa bucket ki veltundi. Already akkada oka `Node` (key-value pair) untadi.
3.  **Use `equals()`:** Ippudu `HashMap`, kottha key ni paatha key tho `equals()` method use chesi compare chestundi.
    *   If `equals()` returns `true`, they are the same key. So, `HashMap` paatha value ni kottha value tho **replace** chestundi.
    *   If `equals()` returns `false`, they are different keys that just happened to have the same hash code. `HashMap` ee kottha key-value pair tho oka `Node` create chesi, aa bucket lo unna linked list ki **chivarina add chestundi**.

So, a collision results in a **linked list of `Node` objects** forming within a single bucket.

---

### 4. Why should you use immutable objects as keys in a `Map` or elements in a `Set`?

**Answer:**

This is a critical best practice to avoid serious bugs.

The problem is with **mutable** (changeable) objects. Imagine nuvvu oka mutable object ni `HashMap` lo key ga pettav.

1.  **`put` operation:** `HashMap` aa object yokka `hashCode()` ni calculate chesi, daani prakaram oka bucket lo store chestundi.
2.  **Object Mutation:** Ippudu, `HashMap` bayata, nuvvu aa object ni modify chesav. For example, `Employee` object lo unna `employeeId` ni change chesav.
3.  **`hashCode` Changes:** `employeeId` meeda base ayi unna `hashCode()` ippudu kottha value istundi.
4.  **The Object is Lost:** Ippudu nuvvu `map.get(myEmployeeObject)` ani call cheste, `HashMap` kottha `hashCode` prakaram veredo bucket lo vetukutundi. Kani, mana object emo paatha `hashCode` tho paatha bucket lo undi. The result? You can't find the object anymore. It's still in memory, taking up space, but it's effectively lost.

Using **immutable** objects (like `String`, `Integer`, or your own final classes) as keys prevents this entirely, because their `hashCode()` can never change after creation.

---

### 5. How would you make a given `ArrayList` thread-safe?

**Answer:**

Standard `ArrayList` anedi thread-safe kaadu. Daanini thread-safe cheyadaniki two primary ways unnayi:

1.  **`Collections.synchronizedList()` (The Old Way):**
    This is a static factory method that takes your `ArrayList` and returns a thread-safe wrapper around it.

    ```java
    List<String> list = new ArrayList<>();
    List<String> synchronizedList = Collections.synchronizedList(list);

    // Now, multiple threads can use synchronizedList safely.
    // But there's a catch...
    synchronized (synchronizedList) {
        Iterator<String> i = synchronizedList.iterator();
        while (i.hasNext()) {
            // ... do something
        }
    }
    ```

    *   **How it works:** Ee wrapper lo unna prathi method (`add`, `get`, etc.) `synchronized` cheyabadi untadi. Idi building ki oke oka main lock pettinattu (`Hashtable` laaga).
    *   **Problem:** Performance is not great because only one thread can access the list at a time for any operation. Also, iteration ni maname `synchronized` block lo pettali, lekapothe `ConcurrentModificationException` ravochu.

2.  **`CopyOnWriteArrayList` (The Modern Way):**
    This is a specialized, thread-safe `List` implementation from the `java.util.concurrent` package.

    ```java
    List<String> list = new CopyOnWriteArrayList<>();
    list.add("A"); // Safe to call from multiple threads
    ```

    *   **How it works:** Idi chala clever. Read operations (`get`) ki asalu lock undadu, so avi chala fast. Write operations (`add`, `remove`) matram chala expensive. Prathi write ki, adi lopalana unna array ni **full ga copy** chesi, aa copy meeda change chesi, tarvata reference ni aa kottha copy ki point chestundi.
    *   **Best For:** Read-heavy scenarios. Ekkada aithe list ni 1000s of times read chestaru, but only okasari, eppudo modify chestaro, akkada idi the best choice. Write-heavy scenarios ki idi asalu paniki raadu.
