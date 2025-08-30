import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// --- Custom Employee Class ---
// Note: To be used correctly in a HashSet, a class must override hashCode() and equals().
class Employee {
    String name;
    int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", id=" + id + '}';
    }

    // If you comment out this method, duplicates will be allowed in the HashSet!
    @Override
    public boolean equals(Object o) {
        System.out.println("... equals() called for " + this.name);
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    // If you comment out this method, performance will be poor and duplicates
    // might still get in, depending on the default hashCode() implementation.
    @Override
    public int hashCode() {
        System.out.println("... hashCode() called for " + this.name);
        return Objects.hash(name, id);
    }
}


public class HashSetExample {

    public static void main(String[] args) {
        demonstrateBasicHashSet();
        demonstrateCustomObjectInSet();
    }

    public static void demonstrateBasicHashSet() {
        System.out.println("--- 1. Basic HashSet Operations ---");
        Set<String> uniqueLanguages = new HashSet<>();

        uniqueLanguages.add("Java");
        uniqueLanguages.add("Python");
        uniqueLanguages.add("Go");
        // Note: Output order is not guaranteed! It depends on the hash codes.
        System.out.println("Set after adding elements: " + uniqueLanguages); // Example Output: Set after adding elements: [Java, Go, Python]

        boolean isAdded = uniqueLanguages.add("Java");
        System.out.println("Was duplicate 'Java' added? " + isAdded); // Output: Was duplicate 'Java' added? false
        System.out.println("Set remains unchanged: " + uniqueLanguages); // Example Output: Set remains unchanged: [Java, Go, Python]

        boolean hasGo = uniqueLanguages.contains("Go");
        System.out.println("Does the set contain 'Go'? " + hasGo); // Output: Does the set contain 'Go'? true

        uniqueLanguages.remove("Go");
        System.out.println("Set after removing 'Go': " + uniqueLanguages); // Example Output: Set after removing 'Go': [Java, Python]
    }

    public static void demonstrateCustomObjectInSet() {
        System.out.println("\n--- 2. Custom Objects in HashSet (The CORRECT Way) ---");

        Set<Employee> employees = new HashSet<>();
        System.out.println("Adding employees...");
        employees.add(new Employee("Mawa", 101));
        employees.add(new Employee("Jules", 102));

        System.out.println("\nAdding a duplicate employee...");
        employees.add(new Employee("Mawa", 101));

        System.out.println("\nFinal Set size: " + employees.size()); // Output: Final Set size: 2
        System.out.println("Final Set contents:");
        employees.forEach(System.out::println);

        System.out.println("\nResult: Duplicate 'Mawa' was NOT added!");
        System.out.println("Because our custom hashCode() and equals() methods allow HashSet to correctly identify the duplicate.");
    }
}
