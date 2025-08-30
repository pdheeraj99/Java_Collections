import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class LinkedHashMapExample {

    public static void main(String[] args) {
        System.out.println("--- 1. Demonstrating Insertion Order ---");
        demonstrateInsertionOrder();

        System.out.println("\n--- 2. Demonstrating an LRU Cache ---");
        demonstrateLRUCache();
    }

    public static void demonstrateInsertionOrder() {
        Map<Integer, String> userMap = new LinkedHashMap<>();

        userMap.put(101, "Mawa");
        userMap.put(102, "Jules");
        userMap.put(1, "Alex");
        userMap.put(205, "Sarah");

        System.out.println("LinkedHashMap maintains the order of insertion:");
        userMap.forEach((id, name) -> System.out.println("ID: " + id + ", Name: " + name));
        // Output:
        // ID: 101, Name: Mawa
        // ID: 102, Name: Jules
        // ID: 1, Name: Alex
        // ID: 205, Name: Sarah
    }

    public static void demonstrateLRUCache() {
        LRUCache<String, String> cache = new LRUCache<>(3);

        System.out.println("Cache initial capacity: 3");
        cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.put("C", "Cherry");
        System.out.println("Cache after 3 puts: " + cache); // Output: Cache after 3 puts: {A=Apple, B=Banana, C=Cherry}

        System.out.println("Accessing key 'A': " + cache.get("A")); // Output: Accessing key 'A': Apple
        System.out.println("Cache after accessing 'A': " + cache); // Output: Cache after accessing 'A': {B=Banana, C=Cherry, A=Apple}
        System.out.println("...'B' is now the least recently used.");

        System.out.println("\nAdding a 4th item 'D'...");
        cache.put("D", "Dragonfruit");
        System.out.println("Cache after adding 'D': " + cache); // Output: Cache after adding 'D': {C=Cherry, A=Apple, D=Dragonfruit}
        System.out.println("...'B' has been evicted automatically!");
    }
}
