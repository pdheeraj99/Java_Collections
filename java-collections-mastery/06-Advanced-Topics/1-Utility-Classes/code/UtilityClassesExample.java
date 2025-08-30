import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UtilityClassesExample {

    public static void main(String[] args) {
        System.out.println("====== Demonstrating java.util.Collections ======");
        demonstrateCollections();

        System.out.println("\n====== Demonstrating java.util.Arrays ======");
        demonstrateArrays();
    }

    public static void demonstrateCollections() {
        System.out.println("\n--- 1. Sorting & Ordering ---");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 1, -4, 2, 3, 0));
        System.out.println("Original list: " + numbers); // Output: Original list: [5, 1, -4, 2, 3, 0]

        Collections.sort(numbers);
        System.out.println("sort(): " + numbers); // Output: sort(): [-4, 0, 1, 2, 3, 5]

        Collections.reverse(numbers);
        System.out.println("reverse(): " + numbers); // Output: reverse(): [5, 3, 2, 1, 0, -4]

        Collections.shuffle(numbers);
        System.out.println("shuffle(): " + numbers); // Example Output: shuffle(): [2, 5, 0, 1, -4, 3] (Order is random)

        Collections.rotate(numbers, 2);
        System.out.println("rotate(2): " + numbers); // Example Output: rotate(2): [0, 1, 2, 5, -4, 3] (Depends on shuffle)

        Collections.swap(numbers, 0, 1);
        System.out.println("swap(0, 1): " + numbers); // Example Output: swap(0, 1): [1, 0, 2, 5, -4, 3] (Depends on shuffle)

        System.out.println("\n--- 2. Searching & Finding Extremes ---");
        Collections.sort(numbers); // Sort again for predictable results
        System.out.println("Sorted for binarySearch: " + numbers); // Output: Sorted for binarySearch: [-4, 0, 1, 2, 3, 5]
        System.out.println("binarySearch(3): index " + Collections.binarySearch(numbers, 3)); // Output: binarySearch(3): index 4
        System.out.println("max(): " + Collections.max(numbers)); // Output: max(): 5
        System.out.println("min(): " + Collections.min(numbers)); // Output: min(): -4
        System.out.println("frequency(of 3): " + Collections.frequency(numbers, 3)); // Output: frequency(of 3): 1

        List<Integer> otherList = List.of(10, 20, 30);
        System.out.println("Are " + numbers + " and " + otherList + " disjoint? " + Collections.disjoint(numbers, otherList)); // Output: Are [-4, 0, 1, 2, 3, 5] and [10, 20, 30] disjoint? true

        System.out.println("\n--- 3. Data Manipulation ---");
        List<Integer> copyList = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0));
        Collections.copy(copyList, numbers);
        System.out.println("copy(dest, src): " + copyList); // Output: copy(dest, src): [-4, 0, 1, 2, 3, 5]

        Collections.fill(copyList, 7);
        System.out.println("fill(with 7): " + copyList); // Output: fill(with 7): [7, 7, 7, 7, 7, 7]

        Collections.replaceAll(numbers, 3, 33);
        System.out.println("replaceAll(3 with 33): " + numbers); // Output: replaceAll(3 with 33): [-4, 0, 1, 2, 33, 5]

        System.out.println("\n--- 4. Wrappers ---");
        List<Integer> unmodifiable = Collections.unmodifiableList(numbers);
        try {
            unmodifiable.add(100);
        } catch (UnsupportedOperationException e) {
            System.out.println("unmodifiableList() works! Caught: " + e.getClass().getName()); // Output: unmodifiableList() works! Caught: java.lang.UnsupportedOperationException
        }

        List<Integer> synchronizedList = Collections.synchronizedList(numbers);
        System.out.println("synchronizedList() created (thread-safe operations can now be done on it)."); // Output: synchronizedList() created (thread-safe operations can now be done on it).

        System.out.println("\n--- 5. Empty/Singleton Collections ---");
        List<Object> empty = Collections.emptyList();
        Set<Object> singleton = Collections.singleton("OnlyOne");
        System.out.println("emptyList(): " + empty); // Output: emptyList(): []
        System.out.println("singleton(): " + singleton); // Output: singleton(): [OnlyOne]
    }

    public static void demonstrateArrays() {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        System.out.println("Original array: " + Arrays.toString(colors)); // Output: Original array: [Red, Green, Blue, Yellow]

        System.out.println("\n--- 1. Sorting & Searching ---");
        Arrays.sort(colors);
        System.out.println("sort(): " + Arrays.toString(colors)); // Output: sort(): [Blue, Green, Red, Yellow]
        System.out.println("binarySearch('Green'): index " + Arrays.binarySearch(colors, "Green")); // Output: binarySearch('Green'): index 1

        System.out.println("\n--- 2. Copying ---");
        String[] fullCopy = Arrays.copyOf(colors, colors.length);
        System.out.println("copyOf(full): " + Arrays.toString(fullCopy)); // Output: copyOf(full): [Blue, Green, Red, Yellow]
        String[] truncatedCopy = Arrays.copyOf(colors, 2);
        System.out.println("copyOf(truncated): " + Arrays.toString(truncatedCopy)); // Output: copyOf(truncated): [Blue, Green]

        System.out.println("\n--- 3. Manipulation ---");
        String[] filledArray = new String[4];
        Arrays.fill(filledArray, "Default");
        System.out.println("fill(): " + Arrays.toString(filledArray)); // Output: fill(): [Default, Default, Default, Default]

        System.out.println("\n--- 4. Conversion ---");
        List<String> listFromArray = Arrays.asList(colors);
        System.out.println("asList(): " + listFromArray); // Output: asList(): [Blue, Green, Red, Yellow]
        System.out.println("NOTE: The list from asList() is fixed-size and backed by the original array!");
    }
}
