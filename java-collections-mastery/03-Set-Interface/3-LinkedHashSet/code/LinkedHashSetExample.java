import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LinkedHashSetExample {

    public static void main(String[] args) {
        demonstrateInsertionOrder();
        demonstrateDuplicateRemoval();
    }

    public static void demonstrateInsertionOrder() {
        System.out.println("--- 1. Demonstrating Insertion Order ---");

        Set<String> orderedSet = new LinkedHashSet<>();

        orderedSet.add("First");
        orderedSet.add("Second");
        orderedSet.add("Third");
        orderedSet.add("Second");

        System.out.println("LinkedHashSet contents:"); // Output: LinkedHashSet contents:
        orderedSet.forEach(System.out::println);
        // Output:
        // First
        // Second
        // Third
    }

    public static void demonstrateDuplicateRemoval() {
        System.out.println("\n--- 2. Practical Use: Removing duplicates from a List while preserving order ---");

        List<String> userActions = new ArrayList<>(Arrays.asList("View", "Click", "View", "Scroll", "Click", "Submit"));
        System.out.println("Original List with duplicates: " + userActions); // Output: Original List with duplicates: [View, Click, View, Scroll, Click, Submit]

        Set<String> uniqueActionsInOrder = new LinkedHashSet<>(userActions);

        List<String> uniqueActionsList = new ArrayList<>(uniqueActionsInOrder);

        System.out.println("List after removing duplicates (order preserved): " + uniqueActionsList); // Output: List after removing duplicates (order preserved): [View, Click, Scroll, Submit]
    }
}
