# 🏅 Interview Questions Part 5: Generics & Wildcards

Mawa, generics anedi Java lo oka powerful feature, kani adi konchem confusing ga untundi, especially wildcards (`?`). Ee section lo, interviewers adige the most common and tricky generics questions ni break down cheddam.

---

### 1. What is the difference between `List<?>`, `List<Object>`, and a raw `List`?

**Answer:**

Ee moodu chala similar ga kanipinchina, vella madhya chala pedda theda undi, especially type safety vishayam lo.

*   **`List<Object>`:**
    *   **Meaning:** Idi "a list that can hold **only** objects of type `Object`". Ante, nuvvu deeniki `new Object()` ni add cheyochu, kani `new String()` or `new Integer()` ni direct ga add cheyalemu (without casting). More importantly, `List<String>` anedi `List<Object>` ki sub-type kaadu.
    *   **Safety:** It is type-safe.

*   **Raw `List`:**
    *   **Meaning:** Idi pre-Java 5 days lo undedi. It's a list that opts out of the generics system entirely.
    *   **Safety:** It is **not type-safe**. Nuvvu deeniki edaina object ni add cheyochu (`list.add(new String())`, `list.add(new Integer())`). Kani, element ni retrieve chesinapudu, adi `Object` la vastundi, and nuvvu daanini manually cast cheyyali, which can cause `ClassCastException` at runtime. Ee rojullo idi asalu vaadakudadu.

*   **`List<?>` (Unbounded Wildcard):**
    *   **Meaning:** Idi "a list of some **unknown** type". Adi `List<String>` avvochu, `List<Integer>` avvochu, or `List<SomeOtherType>` avvochu. Manaki aa type ento teliyadu.
    *   **Safety:** It is type-safe, but in a very restrictive way. Endukante compiler ki aa "unknown" type ento teliyadu కాబట్టి, adi `null` thappa vere ye element ni `add` cheyanivvadu. Idi kevalam list ni safely read cheyadaniki or elements ni remove cheyadaniki matrame use avtundi.

**Summary:** `List<Object>` is a list of `Object`s. Raw `List` is a dangerous, non-type-safe list. `List<?>` is a safe, read-only-style list of an unknown type.

---

### 2. Explain PECS: Producer Extends, Consumer Super.

**Answer:**

PECS stands for **"Producer Extends, Consumer Super"**. Idi generic wildcards ni eppudu ela vaadalo cheppe oka simple rule of thumb.

*   **"Producer Extends" (`? extends T`):**
    *   Oka collection nunchi nuvvu data ni **produce** (read) chestu unte, `extends` wildcard vaadali.
    *   `List<? extends Number> numbers;`
    *   Ee list `List<Integer>` or `List<Double>` ni point cheyochu. Nuvvu deeni nunchi `Number` objects ni safely read cheyochu (`Number n = numbers.get(0);`).
    *   Kani nuvvu deeniki `add` cheyalemu (except `null`), endukante compiler ki adi `List<Integer>` o or `List<Double>` o ani correct ga teliyadu.

*   **"Consumer Super" (`? super T`):**
    *   Oka collection loki nuvvu data ni **consume** (write) chestu unte, `super` wildcard vaadali.
    *   `List<? super Integer> numbers;`
    *   Ee list `List<Integer>`, `List<Number>`, or `List<Object>` ni point cheyochu. Nuvvu deeniki `Integer` objects ni safely `add` cheyochu (`numbers.add(10);`).
    *   Kani deeni nunchi element ni read cheste, adi `Object` la matrame vastundi, specific type raadu.

**Example:**
```java
// Producer Extends: We are reading from src, so it's a producer.
public static <T> void copy(List<? extends T> src, List<? super T> dest) {
    for (T item : src) {
        // Consumer Super: We are writing to dest, so it's a consumer.
        dest.add(item);
    }
}
```

---

### 3. Why can't you add elements to a `List<? extends T>` (except `null`)?

**Answer:**

This is a core concept of generics designed to ensure **type safety**.

Let's take an example: `List<? extends Number> list;`

*   Ee `list` reference anedi `List<Integer>` ni hold cheyochu, or `List<Double>` ni hold cheyochu. The compiler doesn't know which one it is at compile time.
*   Ippudu nuvvu `list.add(new Integer(10))` ani try chesav anuko.
    *   **What if `list` is actually a `List<Double>`?** Nuvvu `Integer` ni `List<Double>` ki add cheyyakudadu. Idi type-safety violation.
*   Ippudu nuvvu `list.add(new Double(10.0))` ani try chesav anuko.
    *   **What if `list` is actually a `List<Integer>`?** Nuvvu `Double` ni `List<Integer>` ki add cheyyakudadu.

Endukante compiler ee guarantee ivvaledu, adi risk teeskodu. So, to ensure 100% type safety, it **prohibits** you from adding any object at all, because it cannot verify if that object is of the correct (unknown) subtype. `null` is the only exception because it's a member of all types.

---

### 4. How do you write a generic method to find the maximum element in a `Collection`?

**Answer:**

Ee question ki answer cheyadaniki, manam "bounded type parameters" aney concept ni use cheyyali. Manam method ki cheppali, adi accept chese type `T` anedi `Comparable` ayyi undali ani.

**The Code:**
```java
public static <T extends Comparable<T>> T max(Collection<T> coll) {
    if (coll.isEmpty()) {
        throw new IllegalArgumentException("Collection is empty");
    }

    T maxElement = null;
    for (T element : coll) {
        if (maxElement == null || element.compareTo(maxElement) > 0) {
            maxElement = element;
        }
    }
    return maxElement;
}
```

**Why it works:**
*   **`<T extends Comparable<T>>`**: Idi bounded type parameter. Idi compiler ki cheptundi: "`T` anedi edo oka type kaadu, adi `Comparable` interface ni implement chese type (or its subtype) matrame."
*   Ee bound valla, manam ippudu `element.compareTo(maxElement)` ani safely call cheyochu. Ee bound lekunte, `compareTo` method `T` meeda undani compiler ki teliyadu, and adi error isthundi.

---

### 5. What is type erasure and how does it affect generic collections at runtime?

**Answer:**

Type erasure is a core concept of how generics are implemented in Java.

*   **What it is:** Generics anedi **compile-time** feature matrame. Ante, compiler code ni check chesi, type safety ni verify chesi, tarvata aa generic type information antha **erase (delete)** chestundi.
*   The generated bytecode lo, `List<String>` or `List<Integer>` lanti information undadu. Avi anni simple ga raw `List` laage kanipistayi.

**How it affects collections at runtime:**
1.  **No Runtime Type Info:** Nuvvu runtime lo `List<String>` and `List<Integer>` ni distinguish cheyalemu. `list instanceof List<String>` lanti check cheyadam possible kaadu.
2.  **Casting:** Compiler lopalana automatic casts ni insert chestundi. Nuvvu `String s = myStringList.get(0);` ani raasinapudu, compiler bytecode lo `String s = (String) myStringList.get(0);` ani raastundi.
3.  **Compatibility:** Type erasure anedi backward compatibility kosam introduce chesaru. Pre-Java 5 lo unna raw type code, Java 5 tarvata vachina generic code tho pani cheyyadaniki idi help chestundi.

So, the main takeaway is: **Generics provide compile-time safety, but that safety net is removed at runtime.**
