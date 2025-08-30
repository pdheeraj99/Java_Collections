# 2.3 LinkedList 🔗 - The Chain of Nodes

Mawa, [`ArrayList`](../2-ArrayList/README.md) tarvata, manam [`List`](../README.md) interface yokka inkoka powerful implementation gurinchi nerchukundam: `LinkedList`. Deeni superpower entante, elements ni add cheyadam and remove cheyadam chala fast.

### The "Treasure Hunt" Analogy 🗺️

Manam [`ArrayList`](../2-ArrayList/README.md) ni oka Train la anukunnam, anni compartments pakkana pakkana untayi. `LinkedList` konchem different.

`LinkedList` ni oka **Treasure Hunt** la oohinchuko:
*   **Node (Clue + Next Clue's Address):** Treasure hunt lo prathi clue daggara, answer tho paatu next clue ekkada undho address (map) untadi. `LinkedList` lo, ee "clue + address" combination ye oka **Node**. Prathi Node lo data (`element`) and next Node yokka reference (`pointer`) untadi.
*   **Head & Tail:** Neeku kevalam first clue (`head`) and last clue (`tail`) matrame istaru.
*   **`get(index)` is SLOW:** Nuvvu 5th clue kavali ante, direct ga vellalem. First clue daggara start chesi, 2nd address ki velli, tarvata 3rd... ila 5th varaku sequential ga vellalsinde. Anduke `get(index)` anedi `LinkedList` lo **slow (O(n))**.
*   **`add/remove` is FAST:** Treasure hunt lo 2nd and 3rd clue madhyalo oka kottha clue ni add cheyadam chala easy.
    1.  2nd clue daggara unna "next address" ni kottha clue ki point cheyyi.
    2.  Kottha clue daggara "next address" ni 3rd clue ki point cheyyi.
    Anthe! Vere clues ni em kadapadam avasaram ledu. Anduke `add`/`remove` operations (at start/middle/end) `LinkedList` lo chala **fast (O(1))**.

---

### More than just a List: The `Deque` Superpower

`LinkedList` [`List`](../README.md) interface tho paatu [`Deque`](../../04-Queue-Interface/README.md) (Double Ended Queue) aney super-powerful interface ni kuda implement chestundi. `Deque` anedi [`Queue`](../../04-Queue-Interface/README.md) ki sub-interface.

Ee implementation valla, `LinkedList` list la matrame kakunda, **Stack** and **Queue** la kuda work chestundi.

*   **As a Queue (FIFO):** `offer()` (add at end), `poll()` (remove from start).
*   **As a Stack (LIFO):** `push()` (add at start), `pop()` (remove from start).

> 💡 **But Should You Use It for Stacks & Queues?**
> `LinkedList` loni ee methods chala flexible ga unna, general ga Stack or Queue kosam manam [`ArrayDeque`](../../04-Queue-Interface/2-ArrayDeque/README.md) ni prefer chestaam. Endukante `ArrayDeque` memory usage and performance lo `LinkedList` kanna better ga untundi. `null` elements store cheyyali anukunte matram `LinkedList` vaadali.

---

### `ArrayList` vs. `LinkedList`: The Ultimate Showdown 🥊

Idi prathi Java interview lo adige classic question. Ee table tho full clarity aipoddi.

| Feature | [`ArrayList`](../2-ArrayList/README.md) | `LinkedList` | Winner 🏆 |
| :--- | :--- | :--- | :--- |
| **Internal Structure** | Dynamic Array | Doubly-Linked List (Nodes) | - |
| **`get(index)` (Random Access)** | **Fast - `O(1)`** | Slow - `O(n)` | `ArrayList` |
| **`add/remove` (at start/middle)** | Slow - `O(n)` | **Fast - `O(1)`** | `LinkedList` |
| **`add/remove` (at end)** | Amortized `O(1)` | `O(1)` | Both are good |
| **Memory Usage** | Lower Overhead | **Higher Overhead** (pointers kosam) | `ArrayList` |
| **Best For** | Read-heavy access | Write-heavy modifications | Depends on use case |

**Mawa, simple ga cheppalante:** Data ni ekkuvaga read chesi, takkuvaga modify cheste [`ArrayList`](../2-ArrayList/README.md) vaadu. Data ni ekkuvaga add or remove cheste `LinkedList` vaadu.

---

### 📖 Method Cheatsheet

`LinkedList` `List` and `Deque` interfaces renditini implement chestundi, so deeniki chala methods untayi.

#### 1. General `Collection` & `List` Methods
| Method | Description |
| :--- | :--- |
| `boolean add(E e)`* | List venaka element ni add chestundi. |
| `E get(int index)`* | Index lo unna element ni istundi (slow O(n)). |
| `E set(int index, E element)`* | Index lo unna element ni replace chestundi (slow O(n)). |
| `boolean remove(Object o)`*| Object first occurrence ni remove chestundi. |
| `int size()`* | List lo enni elements unnayo cheptundi. |
| `boolean contains(Object o)`*| Element unda leda ani check chestundi (slow O(n)). |

#### 2. `Deque` Methods: Head & Tail Operations
| Head Operation | Tail Operation | Description |
| :--- | :--- | :--- |
| `void addFirst(E e)`* | `void addLast(E e)`* | List ki mundu/venaka element ni add chestayi. |
| `boolean offerFirst(E e)` | `boolean offerLast(E e)`*| `addFirst/Last` laantive, kani capacity-restricted deques lo useful. |
| `E getFirst()`* | `E getLast()`* | Mundu/venaka element ni istayi (remove cheyyavu). Empty unte exception. |
| `E peekFirst()` | `E peekLast()` | `getFirst/Last` laantive, kani empty unte `null` istayi. |
| `E removeFirst()`*| `E removeLast()`*| Mundu/venaka element ni remove chesi istayi. Empty unte exception. |
| `E pollFirst()` | `E pollLast()` | `removeFirst/Last` laantive, kani empty unte `null` istayi. |

#### 3. `Stack` (LIFO) Methods
Ee methods `Deque` methods ki convenient names. `push` ante `addFirst`, `pop` ante `removeFirst`.
| Method | Description |
| :--- | :--- |
| `void push(E e)`* | Element ni stack paina (list mundu) add chestundi. |
| `E pop()`* | Stack paina unna element ni remove chesi istundi. |
| `E peek()`* | Stack paina unna element ni chustundi (remove cheyyadu). |

#### 4. `Queue` (FIFO) Methods
Ee methods kuda `Deque` methods ki convenient names. `offer` ante `offerLast`, `poll` ante `pollFirst`.
| Method | Description |
| :--- | :--- |
| `boolean offer(E e)`* | Element ni queue venaka add chestundi. |
| `E poll()`* | Queue mundu unna element ni remove chesi istundi. |
| `E peek()`* | Queue mundu unna element ni chustundi (remove cheyyadu). |

---

### 💼 Office Scenarios: When to use LinkedList?

*   **Implementing a Queue:** Task scheduler lo tasks ni okati tarvata okati process cheyadaniki.
*   **Implementing a Stack:** Text editor lo "Undo/Redo" functionality kosam. Last chesina change ni `push` chestaru, undo cheste `pop` chestaru.
*   **Music Playlist:** User paatalani easy ga add cheyadaniki, remove cheyadaniki, or reorder cheyadaniki.
*   **Browser History:** Back and forward buttons functionality implement cheyadaniki.

---

### 🎯 Interview Focus

*   **Q: When to use `ArrayList` vs. `LinkedList`?**
    *   **A:** (Use the table above). Use `ArrayList` for read-heavy scenarios (fast `get`). Use `LinkedList` for write-heavy scenarios, especially insertions/deletions at the beginning or middle (fast `add`/`remove`).

*   **Q: Why is random access slow in a `LinkedList`?**
    *   **A:** Because it has to traverse sequentially from the beginning (head) node by node to reach the desired index. There's no direct way to jump to an index like in an array.

*   **Q: What other interfaces does `LinkedList` implement?**
    *   **A:** [`List`](../README.md), [`Queue`](../../04-Queue-Interface/README.md), and [`Deque`](../../04-Queue-Interface/README.md). This makes it very versatile. It can act as a list, a queue (FIFO), or a stack (LIFO).
