# 6.1 Utility Classes: Collections & Arrays 🛠️

Mawa, Java provides some extremely useful "utility classes" that are full of `static` helper methods. These classes help us perform common, powerful operations on collections and arrays without writing lots of boilerplate code. The two most important ones are `Collections` and `Arrays`.

---

## 1. `java.util.Collections` - The Swiss Army Knife

This class consists exclusively of static methods that operate on or return collections.

### Sorting & Ordering
| Method | Description |
| :--- | :--- |
| `static <T extends Comparable> void sort(List<T> list)`* | Sorts the list based on the elements' natural ordering. |
| `static <T> void sort(List<T> list, Comparator<? super T> c)`* | Sorts the list using a custom `Comparator`. |
| `static void reverse(List<?> list)`* | Reverses the order of elements in the list. |
| `static void shuffle(List<?> list)`* | Randomly shuffles the elements in the list. |
| `static void rotate(List<?> list, int distance)` | Rotates the elements in the list by a specified distance. |
| `static void swap(List<?> list, int i, int j)` | Swaps the elements at the specified positions. |

### Searching & Finding Extremes
| Method | Description |
| :--- | :--- |
| `static <T> int binarySearch(List<? extends T> list, T key, ...)`* | Searches for an element in a **sorted** list using binary search. |
| `static <T> T max(Collection<T> coll, ...)`* | Returns the maximum element of the given collection. |
| `static <T> T min(Collection<T> coll, ...)`* | Returns the minimum element of the given collection. |
| `static int frequency(Collection<?> c, Object o)`| Returns the number of times an element appears in the collection. |
| `static boolean disjoint(Collection<?> c1, Collection<?> c2)`| Returns `true` if the two collections have no elements in common. |

### Data Manipulation
| Method | Description |
| :--- | :--- |
| `static <T> void fill(List<? super T> list, T obj)` | Replaces all of the elements of the specified list with the specified element. |
| `static <T> boolean replaceAll(List<T> list, T oldVal, T newVal)`| Replaces all occurrences of one specified value in a list with another. |
| `static <T> void copy(List<? super T> dest, List<? extends T> src)`| Copies all of the elements from one list into another. |

### Wrapper Methods (Very Important!)
These methods return a "view" of the collection with a specific behavior.

#### Synchronization Wrappers
Returns a thread-safe view of the collection. Every method call is synchronized on the wrapper object.
| Method |
| :--- |
| `static <T> Collection<T> synchronizedCollection(Collection<T> c)` |
| `static <T> List<T> synchronizedList(List<T> list)`* |
| `static <T> Set<T> synchronizedSet(Set<T> s)`* |
| `static <K,V> Map<K,V> synchronizedMap(Map<K,V> m)`* |
| `static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s)` |
| `static <K,V> SortedMap<K,V> synchronizedSortedMap(SortedMap<K,V> m)`|

#### Unmodifiable Wrappers
Returns a read-only view of the collection. Throws `UnsupportedOperationException` on any attempt to modify.
| Method |
| :--- |
| `static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c)`|
| `static <T> List<T> unmodifiableList(List<? extends T> list)`* |
| `static <T> Set<T> unmodifiableSet(Set<? extends T> s)`* |
| `static <K,V> Map<K,V> unmodifiableMap(Map<? extends K,? extends V> m)`*|
| `static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s)` |
| `static <K,V> SortedMap<K,V> unmodifiableSortedMap(SortedMap<K,V> m)`|

---

## 2. `java.util.Arrays` - The Helper for Arrays

This class contains various static methods for manipulating arrays. Here are the most relevant ones for Collections.

| Method | Description |
| :--- | :--- |
| `static <T> List<T> asList(T... a)`* | Returns a **fixed-size list** backed by the specified array. **Warning:** You cannot `add` or `remove` from this list. |
| `static <T> void sort(T[] a, ...)`* | Sorts the specified array into ascending order. |
| `static <T> int binarySearch(T[] a, T key, ...)`* | Searches the specified array for the specified object using the binary search algorithm. The array must be sorted. |
| `static <T> T[] copyOf(T[] original, int newLength)` | Copies the specified array, truncating or padding with nulls (if necessary) so the copy has the specified length. |
| `static <T> Stream<T> stream(T[] array)` | Returns a sequential `Stream` with the specified array as its source. |
| `static boolean equals(Object[] a, Object[] a2)` | Returns `true` if the two specified arrays of Objects are equal to one another. |
| `static String toString(Object[] a)`* | Returns a string representation of the contents of the specified array. |
| `static void fill(Object[] a, Object val)` | Assigns the specified Object reference to each element of the specified array of Objects. |
