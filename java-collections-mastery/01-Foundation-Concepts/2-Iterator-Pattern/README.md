# 1.2 Iterator Pattern 🔄

Mawa, manam collections lo data ni store cheyadam nerchukunnam. Kani aa data ni access cheyadam ela? Okko element ni visit cheyadam ela? Enter the **Iterator**!

### The "Universal Remote" Analogy 📺

Imagine you have a music playlist ([`List`](../../02-List-Interface/README.md)), a TV channel list ([`Set`](../../03-Set-Interface/README.md)), and a queue of downloads ([`Queue`](../../04-Queue-Interface/README.md)). Veeti anni different types ayina, nuvvu 'next' button click chesi munduku veltav.

An **Iterator** is exactly that—a **universal remote** for your collections. Collection type ([`ArrayList`](../../02-List-Interface/2-ArrayList/README.md), [`HashSet`](../../03-Set-Interface/2-HashSet/README.md), etc.) edaina sare, an iterator provides a standard way (`hasNext()`, `next()`) to traverse its elements one by one.

---

### ✨ Methods of the `Iterator` Interface

`Iterator` interface lo unna methods ni 2 categories ga chuddam.

#### 1. Abstract Methods (The Contract 📜)
Ee methods ni `iterator()` call chesinapudu vachina concrete iterator class **kacchitanga implement cheyyali**.
*   `boolean hasNext()`: Iterate cheyadaniki inka elements unnaya leda ani `true`/`false` cheptundi.
*   `E next()`: Next element ni return chesi, cursor ni munduku jarpuntundi.

#### 2. Default Methods (The Free Helpers 🎁 - Java 8+)
Ee methods ki implementation `Iterator` interface lone untadi.
*   `void remove()` `(default)`: Last `next()` call lo return chesina element ni collection nunchi remove chestundi. **Important:** By default, idi `UnsupportedOperationException` throw chestundi. [`ArrayList`](../../02-List-Interface/2-ArrayList/README.md) lanti classes yokka iterator ee method ni correctly override chesi removal logic ni provide chestayi.
    ```java
    // Example: Remove elements containing 'a' from a list
    List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
    Iterator<String> it = fruits.iterator();
    while (it.hasNext()) {
        String fruit = it.next();
        if (fruit.contains("a")) {
            it.remove(); // Correct way to remove during iteration
        }
    }
    // fruits is now ["Cherry"]
    ```
*   `void forEachRemaining(Consumer<? super E> action)` `(default)`: Iterator lo migili unna anni elements meeda oka operation ni perform chestundi. Manam `while(it.hasNext())` ani loop rayalsina pani ledu.
    **Mawa, remember this:** Ee method `Iterator` interface lo `default` method ga undatam valla, [`ArrayList`](../../02-List-Interface/2-ArrayList/README.md), [`HashSet`](../../03-Set-Interface/2-HashSet/README.md), [`LinkedList`](../../02-List-Interface/3-LinkedList/README.md)... ila prathi collection yokka iterator ki idi automatically available ga untundi. That's the power of default methods!

---

## Types of Iterators

Java lo manaki konni rakala "remotes" unnayi.

| Feature | Iterator | ListIterator | Enumeration (Legacy) |
| :--- | :--- | :--- | :--- |
| **Applies To** | [`List`](../../02-List-Interface/README.md), [`Set`](../../03-Set-Interface/README.md), [`Queue`](../../04-Queue-Interface/README.md) | Only [`List`](../../02-List-Interface/README.md) | [`Vector`](../../02-List-Interface/4-Vector-And-Stack/README.md), [`Hashtable`](../../05-Map-Interface/README.md) (Old) |
| **Traversal** | Forward only | **Bidirectional** (Forward & Backward) | Forward only |
| **Operation** | `remove()` | `add()`, `set()`, `remove()` | Read-only |
| **Thread Safe** | ❌ No | ❌ No | ✅ **Yes** |
| **Fail-Fast** | ✅ **Yes** | ✅ **Yes** | ❌ No (Fail-Safe nature) |

**Bottom Line:** 99% of the time, nuvvu `Iterator` vaadathav. `List` tho specifically work chestu, వెనక్కి కూడా vellali anukunte `ListIterator` vaadu. `Enumeration` ni marchipovadam better, it's from the old days.

---

## 💥 Fail-Fast vs. Fail-Safe: The Guard Analogy

Idi chala important interview question. Let's make it simple.

### Fail-Fast Iterator (The Strict Guard)

Imagine oka room lo entha mandhi unnaro count cheyadaniki oka strict guard unnadu.
*   Vaadu counting start chesadu.
*   Counting madhyalo, evaraina lopaliki vachina or bayataki vellina, vaadiki kopam vastundi.
*   Ventane counting aapestadu, "Hey! Nenu count chestunte disturb chesav!" ani arustadu.

Ee "aravadam" ye `ConcurrentModificationException`.

[`ArrayList`](../../02-List-Interface/2-ArrayList/README.md), [`HashSet`](../../03-Set-Interface/2-HashSet/README.md), [`HashMap`](../../05-Map-Interface/2-HashMap/README.md) lanti collections yokka iterators **fail-fast**. Ante, nuvvu collection ni iterate chestunnapudu, vere thread (or same thread) aa collection ni modify (add/remove) cheste, iterator ventane ee exception ni isthundi. It fails *fast* to prevent future problems.

**Exception:** `iterator.remove()` method use cheste matram exception raadu. Endukante, akkada iterator ki telusu em jarugutundo.

### Fail-Safe Iterator (The Smart Guard)

Ippudu oka smart guard ni imagine chesko.
*   Vaadu counting start cheyadaniki mundu, room ni oka photo teeskuntadu (creates a copy/clone).
*   Ippudu, vaadu aa **photo** lo unna vallani count chestadu.
*   Ee time lo, actual room loki evaraina vachina, vellina, vaadiki sambandam ledu. Vaadi counting disturb avvadu.

Collections like [`ConcurrentHashMap`](../../05-Map-Interface/5-ConcurrentHashMap/README.md) and `CopyOnWriteArrayList` use **fail-safe** iterators. Ivi original collection meeda కాకుండా, daani clone meeda iterate chestayi. So, iteration time lo original collection modify ayina, exception raadu.

**Trade-off:** Fail-safe iterators ki memory ekkuva kavali (clone kosam) and data antha up-to-date undakapovachu.

---

### 🎯 Interview Focus

*   **Q: Difference between `Iterator` and `ListIterator`?**
    *   **A:** Iterator forward-only, ListIterator is bidirectional. ListIterator allows modification (`add`, `set`) while Iterator only allows `remove`.

*   **Q: What is `ConcurrentModificationException`? How do you avoid it?**
    *   **A:** Iteration jarugutunnapudu base collection ni modify cheste ee exception vastundi. To avoid it, use `iterator.remove()` to remove elements during iteration. Or use concurrent collections like [`ConcurrentHashMap`](../../05-Map-Interface/5-ConcurrentHashMap/README.md).

*   **Q: Fail-Fast vs. Fail-Safe?**
    *   **A:** (Use the Guard Analogy). Fail-fast works on the original collection and throws `ConcurrentModificationException` if modified. Fail-safe works on a clone and doesn't.

*   **Q: How do you remove elements while iterating?**
    *   **A:** The *only* safe way is to use the `iterator.remove()` method. For-each loop lo `list.remove()` use cheste `ConcurrentModificationException` vastundi.

### 💼 Office Scenarios

*   **Processing DB records:** Database nunchi vachina records (`List<Employee>`) ni iterate chesi, prathi employee ki bonus calculate cheyadam.
*   **Filtering data:** A `List` of products nunchi, out-of-stock items ni `iterator.remove()` use chesi teeseyadam.
*   **Validating user input:** User enter chesina tags (`Set<String>`) ni iterate chesi, invalid tags ni remove cheyadam.
