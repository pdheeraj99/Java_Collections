# 3.2 HashSet 🏃‍♂️ - The Speed Demon for Uniqueness

Mawa, [`Set`](../README.md) implementations lo `HashSet` eh king. Order mukhyam kaanapudu, and neeku lightning-fast speed kavali anukunnapudu, this is your go-to choice.

### The "VIP Guest List" Analogy 📋

Imagine you are a bouncer at a big party. You have a special clipboard (`HashSet`) to manage the VIP guest list.

*   **Super Fast Check (`contains`):** Guest vachi, "Naa peru list lo unda?" ani adigithe, nuvvu list antha vetakakkarledu. You have a magical system (hashing) that instantly tells you which page the name *should* be on. You just check that single page. This is why `contains()`, `add()`, and `remove()` are **extremely fast (O(1) on average)**.
*   **No Duplicates (`add`):** Guest peru already list lo unte, nuvvu malli add cheyyavu. The `add()` method will just return `false`.
*   **No Order:** Ee clipboard alphabetical order lo ledu, entry time prakaram ledu. It's organized by your magical (and seemingly random) hashing system for maximum speed. So, **`HashSet` does NOT guarantee any order**.

---

### The Magic Behind `HashSet`: `hashCode()` and `equals()`

Idi `HashSet` ki heart and soul, and a **critical interview question**. (Ee concept meeda full clarity kosam, mana [`Comparable` vs. `Comparator` guide](../../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) chudu, andulo `equals`/`hashCode` contract kuda cover chesam). How does the "magical system" work? It uses a two-step process.

Let's say you want to add a custom object, `Employee("Jules", 101)`, to a `HashSet`.

*   **Step 1: Find the "Bucket" using `hashCode()`**
    Java first calls `employee.hashCode()`. Idi oka number (hash code) ni istundi. `HashSet` aa number ni use chesi, daani internal array lo oka "bucket" (index) ni select cheskuntundi. For example, hash code `12345` vachindi anuko, adi bucket #5 ki map avvochu.

*   **Step 2: Check for Duplicates using `equals()`**
    Java ippudu aa bucket #5 ki veltundi.
    *   **Case A: Bucket is empty.** Perfect! New employee object ni akkada petteyali. Done.
    *   **Case B: Bucket already has one or more objects.** (This is called a **hash collision**). Ippudu Java, aa bucket lo unna prathi object tho, mana kottha object ni `.equals()` method use chesi compare chestundi. If `equals()` returns `true` for any of them, it's a duplicate, and the new object is **not added**. Leda, adi duplicate kaadu, so daanini ade bucket lo add chestundi.

### The Golden Rule (The Contract)

For this system to work, you **must** follow this rule when creating your own objects:

> 💡 **The `hashCode()` and `equals()` Contract**
> **If `obj1.equals(obj2)` is true, then `obj1.hashCode()` MUST be equal to `obj2.hashCode()`.**
>
> The reverse is not required: If two objects have the same hash code, they do not need to be equal. This is a hash collision, and `equals()` is used to resolve it.

> ⚠️ **What happens if you break the rule?**
> If two objects are `equal` but have different hash codes, they might be placed in different "buckets." The `HashSet` will then fail to find the duplicate and **will store both objects**. This violates the core principle of a `Set` and is a very common source of bugs.

---

### Performance

| Operation | Average Case | Worst Case | Why? |
| :--- | :--- | :--- | :--- |
| `add` | **O(1)** | O(n) | Super fast, unless all hash codes collide. |
| `remove` | **O(1)** | O(n) | Same as above. |
| `contains` | **O(1)** | O(n) | Same as above. |

---

### 📖 Method Cheatsheet

`HashSet` doesn't add any new methods; it uses the methods from the [`Set`](../README.md) and [`Collection`](../../01-Foundation-Concepts/1-Collection-Framework-Architecture/README.md#methods-of-the-collection-interface) interfaces.

#### Core `Set` Methods
| Method | Description |
| :--- | :--- |
| `boolean add(E e)`* | Adds the element if it's not already present. Returns `true` if added. |
| `boolean remove(Object o)`* | Removes the specified element if it is present. |
| `boolean contains(Object o)`* | Returns `true` if the set contains the specified element. (Very Fast: O(1)) |
| `int size()`* | Returns the number of elements in the set. |
| `boolean isEmpty()`* | Returns `true` if the set contains no elements. |
| `void clear()`* | Removes all of the elements from the set. |
| `Iterator<E> iterator()`* | Returns an iterator over the elements in the set (in no particular order). |

#### Bulk Operations
| Method | Description |
| :--- | :--- |
| `boolean containsAll(Collection<?> c)` | Returns `true` if this set contains all of the elements of the specified collection. |
| `boolean addAll(Collection<? extends E> c)`* | Adds all elements from the given collection to this set. |
| `boolean removeAll(Collection<?> c)`* | Removes all elements from this set that are also contained in the specified collection. |
| `boolean retainAll(Collection<?> c)`* | Retains only the elements in this set that are contained in the specified collection. |

#### Conversion Methods
| Method | Description |
| :--- | :--- |
| `Object[] toArray()` | Returns an array containing all of the elements in this set. |
| `<T> T[] toArray(T[] a)` | Returns an array containing all of the elements in this set; the runtime type is that of the specified array. |

---

### 💼 Office Scenarios

*   **De-duplication:** Database nunchi 1 million records vachayi. Andulo duplicates remove cheyali. Just add them all to a `HashSet`. Fast and easy.
*   **Membership Checking:** "Has this user already seen this ad?" or "Has this product ID already been processed?" `HashSet<String> processedIds` create chesi, `contains()` check cheyadam chala efficient.
*   **Finding unique elements:** Two lists lo unna common elements ni kanipettadaniki.

---

### 🎯 Interview Focus

*   **Q: How does `HashSet` work internally?**
    *   **A:** The secret is that `HashSet` is just a wrapper around a [`HashMap`](../../05-Map-Interface/2-HashMap/README.md). The element you add to the set becomes the *key* in the internal map. For a full explanation of this relationship, check out our **[HashSet Internals Deep-Dive Guide](./deep-dive/README.md)**.

*   **Q: What is the contract between `hashCode()` and `equals()`?**
    *   **A:** If two objects are equal by `.equals()`, they MUST have the same hash code. If two objects have the same hash code, they DON'T have to be equal (this is a collision).

*   **Q: What happens if you add a mutable object (like an [`ArrayList`](../../02-List-Interface/2-ArrayList/README.md)) to a `HashSet` and then change it?**
    *   **A:**
        > ⚠️ **Never use mutable objects as `Set` elements!**
        > If you change an object's state *after* adding it to a `HashSet`, its `hashCode()` might change. The object is now in the **wrong bucket** inside the `HashSet`.
        >
        > When you then try `set.contains(myObject)`, Java will calculate the *new* hash code and look in the *new* bucket. It won't find the object because it's still sitting in the *old* bucket. You effectively "lose" the object, and `remove()` will also fail.
        >
        > **This is why it's a best practice to use immutable objects (like `String`, `Integer`, or your own custom immutable classes) as elements in a `HashSet`.**
