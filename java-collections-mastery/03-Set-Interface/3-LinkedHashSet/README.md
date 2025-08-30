# 3.3 LinkedHashSet 🔗 - Uniqueness with Order

Mawa, [`HashSet`](../2-HashSet/README.md) chala fast, kani order gurinchi pattinchukodu. [`List`](../../02-List-Interface/README.md) order ni maintain chestundi, kani duplicates ni allow chestundi.

What if we want the best of both worlds? **Unique elements** AND **insertion order**.
Enter `LinkedHashSet`.

### The "Queue at a Ticket Counter" Analogy 🎟️

Imagine you are managing a queue for a special movie premiere.

*   **Uniqueness (`HashSet` property):** A person can only be in the queue once. If "Mawa" is in the queue, he can't join again at the end. The system prevents duplicates.
*   **Order (`LinkedList` property):** The queue maintains the exact order of arrival. The person who came first is at the front, the second person is behind them, and so on.

`LinkedHashSet` is exactly this queue. It gives you:
1.  The **O(1) fast performance** of a [`HashSet`](../2-HashSet/README.md) for `add()`, `contains()`, and `remove()`.
2.  The **predictable insertion order** of a [`LinkedList`](../../02-List-Interface/3-LinkedList/README.md).

---

### How It Works Internally

Idi ela possible? `LinkedHashSet` anedi [`HashSet`](../2-HashSet/README.md) ni extend chestundi, so uniqueness and fast performance akkada nunchi vastayi. But, internally, it also maintains a **doubly-linked list** that connects all the elements in the order they were inserted.

![LinkedHashSet Internals](https://i.imgur.com/8x704K8.png)
*(Image shows buckets like a HashSet, with a linked list running through the elements to maintain order)*

Ee extra linked list valla, `LinkedHashSet` anedi `HashSet` kanna konchem ekkuva memory teeskuntundi.

---

### `HashSet` vs. `LinkedHashSet` vs. `TreeSet`

Ee 3 `Set` implementations ki madhya unna main difference order eh.

| Feature | [`HashSet`](../2-HashSet/README.md) | `LinkedHashSet` | [`TreeSet`](../4-TreeSet/README.md) |
| :--- | :--- | :--- | :--- |
| **Order** |  chaotic (No order) | ✅ **Insertion Order** | Sorted Order |
| **Performance** | **Fastest (O(1))** | Slightly Slower (but still O(1)) | Slowest (O(log n)) |
| **Nulls Allowed?** | Yes (one) | Yes (one) | ❌ **No** |
| **Internal Structure**| HashMap | HashMap + Doubly-Linked List | Red-Black Tree |

---

### 📖 Method Cheatsheet

`LinkedHashSet` does not add any new public methods; it inherits all of its methods from [`HashSet`](../2-HashSet/README.md). The only difference is the behavior of the `iterator()`, which respects insertion order.

| Method | Description |
| :--- | :--- |
| `boolean add(E e)`* | Adds the element if it's not already present. |
| `boolean remove(Object o)`* | Removes the specified element if it is present. |
| `boolean contains(Object o)`* | Returns `true` if the set contains the specified element. (Very Fast: O(1)) |
| `int size()`* | Returns the number of elements in the set. |
| `boolean isEmpty()`* | Returns `true` if the set contains no elements. |
| `void clear()`* | Removes all of the elements from the set. |
| `Iterator<E> iterator()`* | Returns an iterator over the elements in the set **in their insertion order**. |

---

### 💼 Office Scenarios

Nuvvu add chesina order lo unique elements kavali anukunnapudu idi perfect choice.

> 💡 **The #1 Use Case: De-duplicating a `List` while Preserving Order**
>
> This is the most common real-world scenario for `LinkedHashSet`. If you have an [`ArrayList`](../../02-List-Interface/2-ArrayList/README.md) with duplicate values, but you need to remove the duplicates while keeping the original sequence, `LinkedHashSet` is the perfect tool.
>
> The code for this is a classic one-liner:
> `List<T> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(originalList));`

*   **Caching:** Caching a list of unique items from an API response in the exact order the API sent them.
*   **UI Elements:** Displaying a list of unique categories or tags to a user in the order they were originally fetched.

---

### 🎯 Interview Focus

*   **Q: When would you use `LinkedHashSet` over `HashSet`?**
    *   **A:** When I need the O(1) performance of [`HashSet`](../2-HashSet/README.md) for lookups, but I also need to maintain the order in which elements were added. For example, to de-duplicate a list while preserving its order.

*   **Q: How does `LinkedHashSet` maintain order?**
    *   **A:** It uses a standard [`HashSet`](../2-HashSet/README.md) for uniqueness and fast lookups, but it also maintains a separate doubly-linked list that connects the elements in their insertion order.
