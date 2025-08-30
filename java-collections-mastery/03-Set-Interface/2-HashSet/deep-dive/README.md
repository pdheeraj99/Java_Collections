# 🚀 HashSet Internals: The Secret is HashMap! 🤫

Mawa, `HashSet` internals gurinchi adigithe, oka simple secret undi: **`HashSet` is just a wrapper around a `HashMap`!**

Interview lo ee okka mata chepthe, you've already won half the battle. Let's see how it works.

## The Big Picture

`HashSet` create chesinapudu, adi lopalana oka `HashMap` instance ni create chestundi.

```java
// Inside the HashSet constructor
public HashSet() {
    map = new HashMap<>();
}
```

Manam `HashSet` lo store chese prathi element, aa internal `HashMap` lo **key** la store avtundi.

**Mari value enti?** The value is a constant, dummy object called `PRESENT`. It's a private static final `Object` that's shared by all `HashSet` instances.

---

## How `add()` and `contains()` Work

Ee secret teliste, methods ela pani chestayo easy ga ardham aipotundi.

### `add(E e)`

Nuvvu `set.add("Jules")` ani call chesinapudu, `HashSet` lopalana idi jarugutundi:

```java
// Inside HashSet.add(E e)
public boolean add(E e) {
    // It just calls map.put().
    // If the key is new, put() returns null, so the expression is true.
    // If the key already exists, put() returns the old value, so it's false.
    return map.put(e, PRESENT) == null;
}
```
*   `e` ("Jules") anedi **key**.
*   `PRESENT` (the dummy object) anedi **value**.

`HashMap` `put` method logic antha ikkada apply avtundi. `hashCode()`, `equals()`, collision handling, treeify... antha same to same.

> 🔥 **Want to master this?** The real magic is in the `HashMap`. Read our **[Ultimate Guide to How HashMap Works Internally](../../../../05-Map-Interface/2-HashMap/deep-dive/README.md)** to understand this completely.

### `contains(Object o)`

Idi inka simple. Nuvvu `set.contains("Jules")` ani call cheste:

```java
// Inside HashSet.contains(Object o)
public boolean contains(Object o) {
    // It just calls map.containsKey()
    return map.containsKey(o);
}
```
`HashSet` lopalana unna `HashMap` meeda `containsKey()` ni call chestundi. Anthe!

### `remove(Object o)`

Same story, `remove` just calls `map.remove()`:
```java
// Inside HashSet.remove(Object o)
public boolean remove(Object o) {
    // map.remove() returns the old value if key existed, or null.
    // If it's not null, it means the key was present.
    return map.remove(o) == PRESENT;
}
```

## Conclusion

So, `HashSet` anedi `HashMap` meeda katinna oka beautiful abstraction. It leverages all the power and performance of a `HashMap` to give us a `Set` implementation. `HashSet` internals gurinchi adigithe, confidently `HashMap` gurinchi explain cheyochu!
