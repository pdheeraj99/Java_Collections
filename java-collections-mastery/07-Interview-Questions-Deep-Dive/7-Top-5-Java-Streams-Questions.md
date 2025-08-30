# 🏅 Interview Questions Part 7: Java Streams & Functional

Mawa, Java 8 tho vachina Streams API anedi collections tho pani cheyadaniki oka modern and powerful way. Interviews lo, collections tho paatu streams meeda kuda questions adagadam chala common. Ee section lo, aa questions ni chuddam.

---

### 1. How would you use Streams to convert a `List<String>` to a `Map<String, Integer>`?

**Answer:**

This is a very common task. The goal is to create a `Map` where the key is the string from the list, and the value is its length. The `collect` terminal operation with `Collectors.toMap()` is the perfect tool for this.

**The Code:**
```java
List<String> names = List.of("Jules", "Mawa", "CollectionsOG");

Map<String, Integer> nameLengths = names.stream()
    .collect(Collectors.toMap(
        name -> name,         // KeyMapper: The string itself is the key
        name -> name.length() // ValueMapper: The length of the string is the value
    ));

System.out.println(nameLengths);
// Output: {Mawa=4, Jules=5, CollectionsOG=13}
```

**What if there are duplicate keys?**
The simple `toMap()` collector will throw an `IllegalStateException` if it encounters duplicate keys. To handle this, we need to provide a "merge function" to tell the collector how to resolve the conflict.

```java
List<String> namesWithDuplicates = List.of("Jules", "Mawa", "Jules");

// Merge function: (oldValue, newValue) -> oldValue. Take the first one seen.
Map<String, Integer> nameLengthsWithMerge = namesWithDuplicates.stream()
    .collect(Collectors.toMap(
        name -> name,
        name -> name.length(),
        (existingValue, newValue) -> existingValue
    ));

System.out.println(nameLengthsWithMerge); // {Mawa=4, Jules=5}
```

---

### 2. Explain the difference between intermediate and terminal stream operations.

**Answer:**

Stream operations ni rendu categories ga divide cheyochu:

*   **Intermediate Operations:**
    *   **What they do:** Ee operations oka stream ni teeskuni, daani meeda edo oka transformation chesi, **inkoka kottha stream ni return** chestayi.
    *   **Execution:** Avi **lazy**. Ante, nuvvu intermediate operation ni call chesinantha matrana, adi run avvadu. Terminal operation call chese varaku wait chestundi.
    *   **Examples:** `map()`, `filter()`, `sorted()`, `distinct()`.

*   **Terminal Operations:**
    *   **What they do:** Ee operations stream pipeline ni **kick-off** chestayi and oka final result ni produce chestayi. Ee result stream kaadu; adi oka primitive value (`count()`), oka `Optional` (`findFirst()`), or oka collection (`collect()`).
    *   **Execution:** Idi call cheyagane, all the previous lazy intermediate operations will execute.
    *   **Examples:** `forEach()`, `collect()`, `reduce()`, `count()`, `anyMatch()`.

**Analogy: A Car Assembly Line**
*   `filter()`, `map()`: These are like stations on the assembly line (e.g., "add wheels", "paint the car"). They just prepare the car for the next step.
*   `collect()`: This is the final station that takes the finished car off the line and puts it in the parking lot. Ee station run aithe ne, mundu stations anni pani chestayi.

---

### 3. How do you find the first duplicate element in a `List` using Streams?

**Answer:**

This is a clever question that combines stream logic with a `Set`.

The logic is: Manam stream lo elements ni iterate chestu, vaatini oka `HashSet` lo add cheyadaniki try cheddam. `Set.add()` method, element already unte `false` return chestundi. Aa `false` return ayina first element eh mana duplicate.

**The Code:**
```java
List<String> list = List.of("A", "B", "C", "D", "B", "E", "C");
Set<String> seenElements = new HashSet<>();

Optional<String> firstDuplicate = list.stream()
    .filter(element -> !seenElements.add(element)) // add() returns false if element already exists
    .findFirst();

firstDuplicate.ifPresent(System.out::println); // Output: B
```

**Why it works:**
*   `filter(element -> !seenElements.add(element))`: Idi core logic.
    *   `seenElements.add("A")` -> returns `true`. `!true` is `false`. "A" is filtered out.
    *   `seenElements.add("B")` -> returns `true`. `!true` is `false`. "B" is filtered out.
    *   ...
    *   `seenElements.add("B")` (second time) -> returns `false`. `!false` is `true`. "B" **passes** the filter.
*   `findFirst()`: Filter pass ayina first element ("B") ni teeskuni, stream processing ni aapestundi. This makes it efficient.

---

### 4. What is the purpose of the `collect(Collectors.groupingBy(...))` method?

**Answer:**

`groupingBy` anedi chala powerful collector. It is used to group the elements of a stream into a `Map` based on a classification function.

Simple ga cheppalante, "Ee list lo unna items ni, ee property prakaram groups cheyyi".

**Example:**
Imagine you have a `List` of `Employee` objects, and you want to group them by their department.

```java
// Employee class with name and department
record Employee(String name, String department) {}

List<Employee> employees = List.of(
    new Employee("Jules", "Engineering"),
    new Employee("Mawa", "HR"),
    new Employee("Samba", "Engineering"),
    new Employee("Rocky", "HR")
);

// Group employees by department
Map<String, List<Employee>> employeesByDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::department));

System.out.println(employeesByDept);
// Output:
// {HR=[Employee[name=Mawa, department=HR], Employee[name=Rocky, department=HR]],
//  Engineering=[Employee[name=Jules, department=Engineering], Employee[name=Samba, department=Engineering]]}
```
The result is a `Map<String, List<Employee>>` where the key is the department, and the value is the list of employees in that department. It's incredibly useful for data aggregation.

---

### 5. What is the difference between `map` and `flatMap` in the context of streams?

**Answer:**

Ee rendu intermediate operations, kani vella purpose veru.

*   **`map(Function<T, R> mapper)`:**
    *   **Purpose:** To **transform** each element of a stream from one type to another (T -> R).
    *   **Result:** It produces a **one-to-one** mapping. Input stream lo `n` elements unte, output stream lo kuda `n` elements untayi.
    *   **Analogy:** "Prathi employee object nunchi, kevalam వాళ్ల name (String) matrame teeyu". `Stream<Employee>` -> `Stream<String>`.

*   **`flatMap(Function<T, Stream<R>> mapper)`:**
    *   **Purpose:** To **transform and flatten**. Idi prathi element ni teeskuni, daaniki baduluga **oka kottha stream** ni produce chestundi. Tarvata, ee resulting streams anni okate single stream la "flatten" (merge) chestundi.
    *   **Result:** It produces a **one-to-many** mapping. Input stream lo `n` elements unte, output stream lo `n` or more or less elements undochu.
    *   **Analogy:** "Prathi department (`String`) nunchi, aa department lo unna employees andari `Stream<Employee>` ni teeyu". `Stream<String>` -> `Stream<Employee>`.

**Example:**
```java
List<List<Integer>> listOfLists = List.of(List.of(1, 2), List.of(3, 4));

// Using map: Results in a Stream of Lists
List<List<Integer>> resultWithMap = listOfLists.stream()
    .map(list -> list.stream().map(n -> n * 2).collect(Collectors.toList()))
    .collect(Collectors.toList());
// Output: [[2, 4], [6, 8]] (Stream<List<Integer>>)

// Using flatMap: Results in a single Stream of Integers
List<Integer> resultWithFlatMap = listOfLists.stream()
    .flatMap(list -> list.stream()) // Flattens Stream<List<Integer>> into Stream<Integer>
    .collect(Collectors.toList());
// Output: [1, 2, 3, 4]
```
`flatMap` is used when you have a stream of collections and you want to work with the elements of all those collections in a single, flat stream.
