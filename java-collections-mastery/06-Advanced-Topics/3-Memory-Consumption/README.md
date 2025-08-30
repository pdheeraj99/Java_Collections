# 6.3 Memory Consumption Comparison 💾

Mawa, performance (time) tho paatu, memory usage kuda chala important factor, especially pedda datasets tho pani chesetappudu. Prathi collection ki kontha "overhead" untundi.

Ee table different collections yokka relative memory overhead ni summarize chestundi.

| Collection | Memory Overhead | Why? | Best For... |
| :--- | :--- | :--- | :--- |
| `ArrayList` | **Low** | Just a wrapper over a simple array. Kontha extra space undochu due to capacity. | Storing lots of data compactly. |
| `LinkedList` | **High** | Prathi element ki, data tho paatu, next and previous elements ki pointers store cheyali. Ee `Node` object overhead ekkuva. | When insertion/deletion performance is more critical than memory. |
| `HashSet` | **Medium** | Internally `HashMap` use chestundi. Prathi element ki oka `HashMap.Entry` object create avtundi. | Fast lookups where memory is not the primary concern. |
| `TreeSet` | **High** | Internally `TreeMap` use chestundi. Prathi element ki oka `TreeMap.Entry` (Node) object create avtundi, and aa tree structure ni maintain cheyadaniki extra pointers untayi. | Sorted data where memory is less important than sorted access. |
| `HashMap` | **Medium** | `HashSet` laage, prathi key-value pair ki oka `HashMap.Entry` object create avtundi. | Fast key-value lookups. |
| `TreeMap` | **High** | `TreeSet` laage, prathi entry ki tree node object create avtundi, adi memory intensive. | Sorted key-value pairs. |

---

### Key Takeaways:

*   **Array-based collections (`ArrayList`, `ArrayDeque`) are generally the most memory-efficient.** They store data compactly in a single block of memory.
*   **Node-based collections (`LinkedList`, `TreeSet`, `TreeMap`) have higher memory overhead.** Each element requires a separate `Node` object to be created, which includes not just the data but also one or more pointers to other nodes.
*   Always consider the trade-off: `LinkedList` might be faster for certain additions, but it will use significantly more memory than an `ArrayList` for the same number of elements.
