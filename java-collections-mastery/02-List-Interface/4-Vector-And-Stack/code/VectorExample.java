import java.util.Vector;

public class VectorExample {

    public static void main(String[] args) {
        // Vector create cheyadam ArrayList laane untundi.
        // All of its methods are synchronized.
        Vector<String> legacyVector = new Vector<>();

        // 1. Adding elements (using both modern and legacy method names)
        legacyVector.add("Apple");
        legacyVector.addElement("Banana"); // Legacy method
        legacyVector.add("Cherry");

        System.out.println("Vector contents: " + legacyVector); // Output: Vector contents: [Apple, Banana, Cherry]

        // 2. Accessing elements
        System.out.println("Element at index 1: " + legacyVector.get(1)); // Output: Element at index 1: Banana
        System.out.println("Element at index 2 (legacy method): " + legacyVector.elementAt(2)); // Output: Element at index 2 (legacy method): Cherry

        // 3. Size and Capacity
        // Vector has a 'capacity' concept, which is the size of its internal array.
        // Default initial capacity is 10.
        System.out.println("Size: " + legacyVector.size()); // Output: Size: 3
        System.out.println("Capacity: " + legacyVector.capacity()); // Output: Capacity: 10

        // 4. Removing an element
        legacyVector.remove("Banana");
        System.out.println("After removing 'Banana': " + legacyVector); // Output: After removing 'Banana': [Apple, Cherry]

        System.out.println("\nNOTE: While Vector works, it is a legacy class.");
        System.out.println("For non-thread-safe operations, ArrayList is much faster.");
        System.out.println("For thread-safe operations, it's better to use Collections.synchronizedList(new ArrayList<>()).");
    }
}
