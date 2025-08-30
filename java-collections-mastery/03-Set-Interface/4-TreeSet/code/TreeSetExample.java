import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

// --- Custom Book Class that implements Comparable ---
// This gives Book a "natural ordering" based on its ID.
class Book implements Comparable<Book> {
    private final String title;
    private final int id;

    public Book(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', id=" + id + '}';
    }

    // This is the natural ordering for Books (by ID)
    @Override
    public int compareTo(Book other) {
        return Integer.compare(this.id, other.id);
    }
}

// --- A separate Comparator class for a custom ordering ---
// This allows us to sort Books by title, ignoring their natural order.
class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }
}


public class TreeSetExample {

    public static void main(String[] args) {
        demonstrateNaturalOrdering();
        demonstrateCustomOrderingWithComparator();
        demonstrateNullRejection();
    }

    public static void demonstrateNaturalOrdering() {
        System.out.println("--- 1. Natural Ordering (using Comparable) ---");
        Set<Book> libraryById = new TreeSet<>();

        libraryById.add(new Book("The Lord of the Rings", 102));
        libraryById.add(new Book("Dune", 101));
        libraryById.add(new Book("A Game of Thrones", 103));

        System.out.println("Library sorted by Book ID (Natural Order):");
        libraryById.forEach(System.out::println);
        // Output:
        // Book{title='Dune', id=101}
        // Book{title='The Lord of the Rings', id=102}
        // Book{title='A Game of Thrones', id=103}
    }

    public static void demonstrateCustomOrderingWithComparator() {
        System.out.println("\n--- 2. Custom Ordering (using Comparator) ---");
        Set<Book> libraryByTitle = new TreeSet<>(new BookTitleComparator());

        libraryByTitle.add(new Book("The Lord of the Rings", 102));
        libraryByTitle.add(new Book("Dune", 101));
        libraryByTitle.add(new Book("A Game of Thrones", 103));

        System.out.println("Library sorted by Book Title (Custom Order):");
        libraryByTitle.forEach(System.out::println);
        // Output:
        // Book{title='A Game of Thrones', id=103}
        // Book{title='Dune', id=101}
        // Book{title='The Lord of the Rings', id=102}
    }

    public static void demonstrateNullRejection() {
        System.out.println("\n--- 3. Trying to add null ---");
        Set<String> set = new TreeSet<>();
        try {
            set.add(null);
        } catch (NullPointerException e) {
            System.out.println("Successfully caught expected exception!"); // Output: Successfully caught expected exception!
            System.out.println(e.getClass().getName() + ": TreeSet does not permit null elements."); // Output: java.lang.NullPointerException: TreeSet does not permit null elements.
        }
    }
}
