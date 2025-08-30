# 2.4 Vector & Stack 📚 - The Legacy Collections

Mawa, ippudu manam `ArrayList` ki relative aina `Vector`, and daani sub-class aina `Stack` gurinchi matladukundam. Ee rendu "legacy" collections, ante paatha rojula nunchi unnayi.

**Important Note:** Ippudu 99% of the time, manam veetini **vaadakudadu**. Kani, interviews lo adugutaru and paatha codebases lo kanipistayi, so vella gurinchi teluskovadam avasaram.

---

### Vector: The Thread-Safe `ArrayList`

`Vector` anedi [`ArrayList`](../2-ArrayList/README.md) laantide. Internal ga adi kuda resizable array ne use chestundi. The only major difference is:

**`Vector` is thread-safe. `ArrayList` is not.**

Ante, `Vector` lo prathi method (`add`, `get`, `remove`) `synchronized` aney keyword tho protect cheyabadi untadi. Ante, multiple threads okesaari `Vector` ni modify cheyalevu.

**So, why don't we use it? (Enduku vaadamu?)**

| Reason | `Vector` (The Old Way) | Modern Approach |
| :--- | :--- | :--- |
| **Performance** | **Very Slow.** Prathi operation ki lock teeskuni, release chestundi. Single thread lo kuda ee overhead untundi. | **Much Faster.** Use [`ArrayList`](../2-ArrayList/README.md). Thread safety kavali anukunte, `Collections.synchronizedList(new ArrayList<>())` tho wrap cheyyi. |
| **Flexibility** | Lock mechanism meeda manaki control undadu. | `synchronized` blocks tho, manam critical sections matrame lock cheyochu, giving us more control and better performance. |
| **Growth** | Doubles its size (100% growth) by default. Chala memory waste avvochu. | [`ArrayList`](../2-ArrayList/README.md) grows by 50%. More memory efficient. |

**Verdict:** `Vector` is outdated. Avoid it.

---

### Stack: The Problematic Stack

`Stack` class LIFO (Last-In, First-Out) behavior ni represent chestundi. (Pelli bhojanam lo plates example gurtunda?).

> ⚠️ **The #1 Problem with `Stack`: Bad Inheritance**
> `Stack` class `Vector` ni extend chestundi. **And ide daani pedda problem.** A `Stack` should only allow LIFO operations (`push`, `pop`, `peek`). Kani, `Vector` nunchi `get(index)`, `add(int index, E element)`, `removeElementAt(int index)` lanti `List` methods anni inherit cheskuntundi.
>
> Ante, nuvvu `Stack` lo madhyalo nunchi element ni `get` cheyochu or `remove` cheyochu. **This completely breaks the LIFO principle of a Stack!** Ee design flaw valla, `Stack` class ni modern Java development lo asalu vaadakudadu.

> 😂 **Mawa Joke:** Using the legacy `Stack` class is like hiring a security guard for your house who is also a thief. He might protect you, but he also knows how to break in from the back door (`get(index)`)!

**The Modern Alternative: Use the `Deque` Interface**

Stack behavior kavali anukunte, eppudaina [`Deque`](../../04-Queue-Interface/README.md) interface ni use cheyyali. [`ArrayDeque`](../../04-Queue-Interface/2-ArrayDeque/README.md) is the best choice.

| Feature | `Stack` (The Old Way) | [`ArrayDeque`](../../04-Queue-Interface/2-ArrayDeque/README.md) (The Modern Way) |
| :--- | :--- | :--- |
| **Inheritance** | Extends `Vector`. Problematic. | Implements [`Deque`](../../04-Queue-Interface/README.md) interface. Clean design. |
| **API** | Only `Stack` methods undali, kani `List` methods kuda vachayi. | `push`, `pop`, `peek` lanti clear stack methods unnayi. No extra baggage. |
| **Performance** | Slower, because it's built on `Vector`. | Much faster. No synchronization overhead. |
| **Thread Safety**| Yes (because of `Vector`) | No. (If needed, use `ConcurrentLinkedDeque`). |

**Verdict:** Never use `Stack`. Always use an [`ArrayDeque`](../../04-Queue-Interface/2-ArrayDeque/README.md) when you need a Stack.

---

### 📖 Method Cheatsheets

#### `Vector` - Exhaustive Methods
`Vector` has its own legacy methods and also implements all `List` methods. **All of these are synchronized.**

| Method | Description |
| :--- | :--- |
| **Core List Methods** | |
| `boolean add(E e)`* | Appends the element to the end of the Vector. |
| `E get(int index)`* | Returns the element at the specified position. |
| `E set(int index, E element)`* | Replaces the element at the specified position. |
| `boolean remove(Object o)`*| Removes the first occurrence of the specified element. |
| `int size()`* | Returns the number of elements in the Vector. |
| **Legacy `Vector` Methods** | |
| `void addElement(E obj)` | Same as `add(E e)`. |
| `E elementAt(int index)` | Same as `get(int index)`. |
| `E firstElement()` | Returns the first component (at index 0). |
| `E lastElement()` | Returns the last component. |
| `void insertElementAt(E obj, int index)` | Same as `add(int index, E element)`. |
| `void removeElementAt(int index)` | Same as `remove(int index)`. |
| `int capacity()` | Returns the current capacity of this vector. |
| `void trimToSize()` | Trims the capacity to be the vector's current size. |
| `java.util.Enumeration<E> elements()`| Returns an [`Enumeration`](../../01-Foundation-Concepts/2-Iterator-Pattern/README.md#types-of-iterators) of the components. (Legacy Iterator) |


#### `Stack` - Exhaustive Methods
| Method | Description |
| :--- | :--- |
| **Core `Stack` Methods** | |
| `E push(E item)`* | Pushes an item onto the top of this stack. |
| `E pop()`* | Removes the object at the top of this stack and returns it. |
| `E peek()`* | Looks at the object at the top of this stack without removing it. |
| `boolean empty()` | Tests if this stack is empty. |
| `int search(Object o)`| Returns the 1-based position where an object is on this stack. |

**Important Reminder:** `Stack` also inherits **all** of `Vector`'s methods, including `get(index)`, `addElement`, etc. Using these methods on a `Stack` instance breaks the LIFO principle and is why `Stack` should be avoided.

---

### 🎯 Interview Focus

*   **Q: Why is `Vector` slower than `ArrayList`?**
    *   **A:** Because every public method in `Vector` is `synchronized`, which adds locking overhead even in single-threaded environments. `ArrayList` has no such overhead.

*   **Q: What is the main problem with the `Stack` class?**
    *   **A:** It extends `Vector`. This means it inherits all list-based methods like `get(index)` and `add(at: index)`, which violates the LIFO principle of a true stack.

*   **Q: What is the modern alternative to `Stack`?**
    *   **A:** The [`Deque`](../../04-Queue-Interface/README.md) interface, with [`ArrayDeque`](../../04-Queue-Interface/2-ArrayDeque/README.md) being the recommended implementation. It provides a cleaner API and better performance.
