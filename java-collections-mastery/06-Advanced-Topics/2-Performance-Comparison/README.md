# 6.2 The Big O Cheatsheet: Performance Comparison 📊

Mawa, correct collection ni choose cheskovadam anedi daani performance characteristics meeda depend ayi untundi. Ee table manam nerchukunna main collections yokka time complexity (Big O notation) ni summarize chestundi.

This is one of the most important tables for interview preparation!

| Operation | `ArrayList` | `LinkedList` | `HashSet` | `TreeSet` | `HashMap` | `TreeMap` |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Add / Put** | **O(1)*** | **O(1)** | **O(1)*** | O(log n) | **O(1)*** | O(log n) |
| **Remove** | O(n) | **O(1)*** | **O(1)*** | O(log n) | **O(1)*** | O(log n) |
| **Get / Contains**| **O(1)** | O(n) | **O(1)*** | O(log n) | **O(1)*** | O(log n) |
| **Next (Iteration)**| O(1) | O(1) | O(h/n)* | O(log n) | O(h/n)* | O(log n) |

---
### Notes and Explanations:

*   **`ArrayList` `add` is Amortized O(1):** `ArrayList` ki add cheyadam chala fast, kani appudappudu array resize avvalsina time lo adi O(n) avtundi. Average ga chuste, adi O(1) eh.
*   **`ArrayList` `get` is O(1):** Direct ga index tho array lo memory location ni access cheyagalam kabatti, idi chala fast.
*   **`ArrayList` `remove` is O(n):** Element ni remove chesaka, daani tarvata unna elements anni shift avvali.
*   **`LinkedList` `add`/`remove` is O(1):** Idi kevalam head or tail daggara matrame. Madhyalo add/remove cheyalante, first aa position ki reach avvadaniki O(n) time padutundi.
*   **`HashSet` / `HashMap` are Amortized O(1):** Hashing valla, operations anni average ga constant time lo aipotayi. Kani, worst case (anni keys oke bucket lo padithe), adi O(n) avvochu.
*   **`HashSet` / `HashMap` Iteration is O(h/n):** Where `h` is the table capacity and `n` is the number of elements. Simple ga, iteration time anedi capacity meeda depend avtundi.
*   **`TreeSet` / `TreeMap` are O(log n):** Ee collections balanced binary tree (Red-Black Tree) use chestayi. So, prathi operation (add, remove, contains) ki tree lo correct position kanipettadaniki O(log n) time padutundi. Idi O(1) kanna slow, kani O(n) kanna chala fast.
