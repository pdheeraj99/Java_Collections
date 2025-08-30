# Phase 2: The List Interface 📝

Welcome to Phase 2! Ikkada manam `List` interface gurinchi deep dive cheddam.

A `List` is an **ordered** collection (also known as a sequence). Ante, elements ni manam ye order lo add chestamo, ave order lo store avtayi. Most importantly, `List` allows **duplicate** elements.

Ee phase lo manam kindi implementations gurinchi nerchukunnam:
*   [**2.2 ArrayList**](./2-ArrayList/README.md) 📊
*   [**2.3 LinkedList**](./3-LinkedList/README.md) 🔗
*   [**2.4 Vector & Stack**](./4-Vector-And-Stack/README.md) 📚 (Legacy Collections)

---

### ✨ Methods of the `List` Interface: The Contract and the Helpers

`List` anedi `Collection` ni extend chestundi, so daanilo unna methods anni `List` ki kuda vastayi. Kani, `List` konni specific methods ni add chestundi, especially index-based operations kosam.

#### 1. Abstract Methods (The Contract `List` Promises 📜)
`List` interface ni implement chese prathi class (`ArrayList`, `LinkedList`) ee methods ni **kacchitanga implement cheyyali**.
*   `E get(int index)`: Index lo unna element ni istundi.
*   `E set(int index, E element)`: Index lo unna element ni kottha element tho replace chestundi.
*   `void add(int index, E element)`: Index lo kottha element ni add chestundi.
*   `E remove(int index)`: Index lo unna element ni remove chestundi.
*   `int indexOf(Object o)`: Element yokka first index ni istundi.
*   `int lastIndexOf(Object o)`: Element yokka last index ni istundi.

**Mawa, ide magic-u:** [`ArrayList`](./2-ArrayList/README.md) veetini array operations tho implement chestundi (so `get` is fast). [`LinkedList`](./3-LinkedList/README.md) veetini node traversal tho implement chestundi (so `get` is slow). The *contract* is the same, but the *performance* is different.

#### 2. Default Methods (The Free Helpers 🎁 - Java 8+)
Ee methods `List` interface lone implement chesi untayi. `ArrayList` or `LinkedList` veetini rayalsina avasaram ledu.
*   `void sort(Comparator<? super E> c)` `(default)`: List ni in-place (original list ne) sort chestundi. Internally, idi `Arrays.sort` ni use cheskuntundi, which is highly optimized. (Sorting gurinchi full details kosam mana [`Comparable` vs `Comparator` guide](../00-Key-Concepts/2-Comparable-vs-Comparator/README.md) chudu).
    ```java
    // Example: Sort a list of numbers
    List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 1, 4, 2, 3));
    // Natural order (ascending)
    numbers.sort(Comparator.naturalOrder());
    System.out.println(numbers); // [1, 2, 3, 4, 5]
    // Reverse order
    numbers.sort(Comparator.reverseOrder());
    System.out.println(numbers); // [5, 4, 3, 2, 1]
    ```
*   `void replaceAll(UnaryOperator<E> operator)` `(default)`: List lo unna prathi element meeda oka operation perform chesi, daanne replace chestundi.
    ```java
    // Example: Convert all names to uppercase
    List<String> names = new ArrayList<>(Arrays.asList("mawa", "jules"));
    names.replaceAll(name -> name.toUpperCase());
    System.out.println(names); // ["MAWA", "JULES"]
    ```

#### 3. Static Methods (The Utilities 🛠️ - Java 9+)
Ee methods ni manam direct ga `List` interface meeda call chestam.
*   `static <E> List<E> of(E... elements)` `(static)`: Oka **immutable** (change cheyaleni) list ni create chestundi. Idi chala convenient.
    ```java
    // Example: Create a fixed, unchangeable list of roles
    List<String> roles = List.of("ADMIN", "USER", "GUEST");
    System.out.println(roles);
    // roles.add("SUPER_ADMIN"); // Throws UnsupportedOperationException!
    ```
*   `static <E> List<E> copyOf(Collection<? extends E> coll)` `(static)`: Vere collection nunchi oka immutable copy ni create chestundi.
    ```java
    // Example: Create a safe, immutable copy of a mutable list
    List<String> mutableList = new ArrayList<>();
    mutableList.add("A");
    mutableList.add("B");
    List<String> immutableCopy = List.copyOf(mutableList);
    System.out.println(immutableCopy); // [A, B]
    // immutableCopy.add("C"); // Throws UnsupportedOperationException!
    ```

---

## When to Use Which List? (The Ultimate Summary) 🤔

Prathi `List` implementation ki oka specific purpose undi. Ee kindi table, "Eppudu edi vaadali?" aney question ki final answer istundi.

| Scenario | Recommendation | Why? (The Simple Reason) |
| :--- | :--- | :--- |
| **Reading data is the main job.** (e.g., display records from DB) | [`ArrayList`](./2-ArrayList/README.md) | **Fastest `get(index)` access (O(1))**. Direct ga index tho element ni teskovachu. |
| **Adding/removing elements at the start or end is the main job.** | [`LinkedList`](./3-LinkedList/README.md) | **Fastest add/remove at ends (O(1))**. Deque functionality kosam perfect. |
| **Implementing a Stack (LIFO).** (e.g., Undo button) | [`ArrayDeque`](../04-Queue-Interface/2-ArrayDeque/README.md) | `Stack` class kanna clean API and better performance. `LinkedList` kuda use cheyochu. |
| **Implementing a Queue (FIFO).** (e.g., Task scheduler) | [`ArrayDeque`](../04-Queue-Interface/2-ArrayDeque/README.md) | `LinkedList` kanna memory efficient and generally faster for queue operations. |
| **I have a small, fixed set of items.** (e.g., config values) | `List.of(...)` | **Immutable and Safe.** Create chesaka evaru modify cheyaleru. Concise. |
| **I need a thread-safe list.** (Multiple threads will modify it) | `Collections.synchronizedList(new ArrayList<>())` | [`Vector`](./4-Vector-And-Stack/README.md) kanna better performance and modern approach. Fine-grained control kavali ante, concurrent collections vaadali. |
| **I have a huge list and will add/remove from the middle a lot.** | [`LinkedList`](./3-LinkedList/README.md) | **Theoretically `O(1)`** for add/remove. Kani, first aa position ki reach avvadaniki `O(n)` time padutundi. So, idi chala rare use case. |

> 💡 **The Golden Rule:** Neeku em vaadalo a specific reason teliyanapudu, **start with [`ArrayList`](./2-ArrayList/README.md)**. It's the most common, general-purpose list and is usually the right choice.
