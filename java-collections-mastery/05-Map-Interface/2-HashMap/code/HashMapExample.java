import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapExample {

    public static void main(String[] args) {
        System.out.println("--- 1. Basic HashMap Operations ---");
        demonstrateBasicOperations();

        System.out.println("\n--- 2. Iterating over a HashMap ---");
        demonstrateIteration();
    }

    public static void demonstrateBasicOperations() {
        Map<Integer, String> userMap = new HashMap<>();

        userMap.put(101, "Mawa");
        userMap.put(102, "Jules");
        userMap.put(103, "Alex");
        System.out.println("Initial Map: " + userMap); // Example Output: Initial Map: {101=Mawa, 102=Jules, 103=Alex}

        String user102 = userMap.get(102);
        System.out.println("User with ID 102: " + user102); // Output: User with ID 102: Jules

        String oldUser = userMap.put(101, "Mawa-Updated");
        System.out.println("Replaced old value for key 101: " + oldUser); // Output: Replaced old value for key 101: Mawa
        System.out.println("Map after update: " + userMap); // Example Output: Map after update: {101=Mawa-Updated, 102=Jules, 103=Alex}

        boolean hasKey104 = userMap.containsKey(104);
        System.out.println("Does map contain key 104? " + hasKey104); // Output: Does map contain key 104? false

        userMap.remove(103);
        System.out.println("Map after removing key 103: " + userMap); // Example Output: Map after removing key 103: {101=Mawa-Updated, 102=Jules}

        String user105 = userMap.getOrDefault(105, "Guest User");
        System.out.println("Getting user 105 (not present): " + user105); // Output: Getting user 105 (not present): Guest User
    }

    public static void demonstrateIteration() {
        Map<String, String> capitals = new HashMap<>();
        capitals.put("India", "New Delhi");
        capitals.put("USA", "Washington D.C.");
        capitals.put("Japan", "Tokyo");

        System.out.println("Original Map: " + capitals); // Example Output: Original Map: {USA=Washington D.C., Japan=Tokyo, India=New Delhi}

        System.out.println("\nIterating over keys:");
        Set<String> countries = capitals.keySet();
        for (String country : countries) {
            System.out.println("Country: " + country + ", Capital: " + capitals.get(country));
        }
        // Example Output (order not guaranteed):
        // Country: USA, Capital: Washington D.C.
        // Country: Japan, Capital: Tokyo
        // Country: India, Capital: New Delhi

        System.out.println("\nIterating over values:");
        Collection<String> capitalCities = capitals.values();
        for (String city : capitalCities) {
            System.out.println("Capital: " + city);
        }
        // Example Output (order not guaranteed):
        // Capital: Washington D.C.
        // Capital: Tokyo
        // Capital: New Delhi

        System.out.println("\nIterating over entries (most efficient):");
        Set<Map.Entry<String, String>> entries = capitals.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        // Example Output (order not guaranteed):
        // Key: USA, Value: Washington D.C.
        // Key: Japan, Value: Tokyo
        // Key: India, Value: New Delhi
    }
}
