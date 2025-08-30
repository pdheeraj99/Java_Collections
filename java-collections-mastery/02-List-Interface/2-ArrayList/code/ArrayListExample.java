import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ArrayListExample {

    public static void main(String[] args) {
        System.out.println("====== ArrayList Comprehensive Guide ======");

        demonstrateConstructors();
        demonstrateBasicOperations();
        demonstrateBulkOperations();
        demonstrateSearchOperations();
        demonstrateConversionAndIteration();
        demonstrateViewOperations();
        demonstrateCapacityManagement();
        demonstrateObjectMethods();

        System.out.println("\n====== End of Guide ======");
    }

    public static void demonstrateConstructors() {
        System.out.println("\n--- 1. Constructors ---");
        // 1. Empty constructor - initial capacity 10 (on first add)
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("First");
        System.out.println("1. Empty Constructor: " + list1); // Output: 1. Empty Constructor: [First]

        // 2. Constructor with initial capacity
        ArrayList<Integer> list2 = new ArrayList<>(5);
        System.out.println("2. With Initial Capacity (size is " + list2.size() + ", but capacity is 5)"); // Output: 2. With Initial Capacity (size is 0, but capacity is 5)

        // 3. Constructor with another collection
        List<String> initialData = Arrays.asList("Java", "Python", "Go");
        ArrayList<String> list3 = new ArrayList<>(initialData);
        System.out.println("3. From another collection: " + list3); // Output: 3. From another collection: [Java, Python, Go]
    }

    public static void demonstrateBasicOperations() {
        System.out.println("\n--- 2. Basic Operations ---");
        ArrayList<String> languages = new ArrayList<>();

        languages.add("Java");
        languages.add("Python");
        System.out.println("After add(E): " + languages); // Output: After add(E): [Java, Python]

        languages.add(1, "JavaScript");
        System.out.println("After add(index, E): " + languages); // Output: After add(index, E): [Java, JavaScript, Python]

        String lang = languages.get(0);
        System.out.println("get(0): " + lang); // Output: get(0): Java

        languages.set(2, "Go");
        System.out.println("After set(2, 'Go'): " + languages); // Output: After set(2, 'Go'): [Java, JavaScript, Go]

        String removedLang = languages.remove(1);
        System.out.println("Removed with remove(1): " + removedLang + ", List is now: " + languages); // Output: Removed with remove(1): JavaScript, List is now: [Java, Go]

        boolean isRemoved = languages.remove("Go");
        System.out.println("Was 'Go' removed? " + isRemoved + ", List is now: " + languages); // Output: Was 'Go' removed? true, List is now: [Java]

        System.out.println("size(): " + languages.size()); // Output: size(): 1

        System.out.println("isEmpty(): " + languages.isEmpty()); // Output: isEmpty(): false
    }

    public static void demonstrateBulkOperations() {
        System.out.println("\n--- 3. Bulk Operations ---");
        ArrayList<String> mainList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> otherList = Arrays.asList("D", "E");

        mainList.addAll(otherList);
        System.out.println("After addAll(Collection): " + mainList); // Output: After addAll(Collection): [A, B, C, D, E]

        mainList.addAll(1, Arrays.asList("X", "Y"));
        System.out.println("After addAll(index, Collection): " + mainList); // Output: After addAll(index, Collection): [A, X, Y, B, C, D, E]

        mainList.removeAll(Arrays.asList("X", "Y", "A"));
        System.out.println("After removeAll(X, Y, A): " + mainList); // Output: After removeAll(X, Y, A): [B, C, D, E]

        mainList.retainAll(Arrays.asList("B", "D", "Z"));
        System.out.println("After retainAll(B, D, Z): " + mainList); // Output: After retainAll(B, D, Z): [B, D]

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        numbers.removeIf(num -> num % 2 == 0);
        System.out.println("After removeIf(even numbers): " + numbers); // Output: After removeIf(even numbers): [1, 3, 5]

        mainList.clear();
        System.out.println("After clear(): size is " + mainList.size() + ", isEmpty? " + mainList.isEmpty()); // Output: After clear(): size is 0, isEmpty? true
    }

    public static void demonstrateSearchOperations() {
        System.out.println("\n--- 4. Search Operations ---");
        ArrayList<String> tools = new ArrayList<>(Arrays.asList("Git", "Docker", "Kubernetes", "Docker"));

        boolean hasDocker = tools.contains("Docker");
        System.out.println("contains('Docker'): " + hasDocker); // Output: contains('Docker'): true

        int firstIndex = tools.indexOf("Docker");
        System.out.println("indexOf('Docker'): " + firstIndex); // Output: indexOf('Docker'): 1

        int lastIndex = tools.lastIndexOf("Docker");
        System.out.println("lastIndexOf('Docker'): " + lastIndex); // Output: lastIndexOf('Docker'): 3
    }

    public static void demonstrateConversionAndIteration() {
        System.out.println("\n--- 5. Conversion & Iteration ---");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("One", "Two", "Three"));

        Object[] objArray = list.toArray();
        System.out.println("toArray() -> objArray[0]: " + objArray[0]); // Output: toArray() -> objArray[0]: One

        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        System.out.println("toArray(T[] a) -> strArray[1]: " + strArray[1]); // Output: toArray(T[] a) -> strArray[1]: Two
    }

    public static void demonstrateViewOperations() {
        System.out.println("\n--- 6. View Operations ---");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));

        List<Integer> sub = numbers.subList(1, 4);
        System.out.println("Original list: " + numbers); // Output: Original list: [10, 20, 30, 40, 50]
        System.out.println("Sublist from index 1 to 4: " + sub); // Output: Sublist from index 1 to 4: [20, 30, 40]

        sub.set(0, 25);
        System.out.println("After changing sublist: " + numbers); // Output: After changing sublist: [10, 25, 30, 40, 50]
    }

    public static void demonstrateCapacityManagement() {
        System.out.println("\n--- 7. Capacity Management ---");
        ArrayList<Integer> list = new ArrayList<>();
        list.ensureCapacity(1_000_000);
        System.out.println("1. ensureCapacity(1_000_000) called. (No visible output, but internally array is large)");
        list.add(1);
        list.add(2);
        list.trimToSize();
        System.out.println("2. trimToSize() called. (Capacity is now trimmed to current size: 2)");
    }

    public static void demonstrateObjectMethods() {
        System.out.println("\n--- 8. Object Methods ---");
        ArrayList<String> original = new ArrayList<>(Arrays.asList("X", "Y"));

        ArrayList<String> cloned = (ArrayList<String>) original.clone();

        System.out.println("Original list: " + original); // Output: Original list: [X, Y]
        System.out.println("Cloned list: " + cloned); // Output: Cloned list: [X, Y]

        System.out.println("Are original and cloned the same object? " + (original == cloned)); // Output: Are original and cloned the same object? false
        System.out.println("Do they have the same content? " + original.equals(cloned)); // Output: Do they have the same content? true
    }
}
