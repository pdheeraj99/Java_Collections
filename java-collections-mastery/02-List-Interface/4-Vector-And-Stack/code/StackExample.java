import java.util.Stack;

public class StackExample {

    public static void main(String[] args) {
        System.out.println("--- 1. Correct LIFO (Last-In, First-Out) Usage ---");
        demonstrateCorrectUsage();

        System.out.println("\n--- 2. The PROBLEM with Stack: Incorrect Usage ---");
        demonstrateProblematicUsage();
    }

    public static void demonstrateCorrectUsage() {
        Stack<String> bookStack = new Stack<>();

        System.out.println("Pushing items onto the stack...");
        bookStack.push("Book A");
        bookStack.push("Book B");
        bookStack.push("Book C");

        System.out.println("Current stack: " + bookStack); // Output: Current stack: [Book A, Book B, Book C]

        System.out.println("Top of the stack (peek): " + bookStack.peek()); // Output: Top of the stack (peek): Book C

        System.out.println("Popping the top item: " + bookStack.pop()); // Output: Popping the top item: Book C
        System.out.println("Stack after one pop: " + bookStack); // Output: Stack after one pop: [Book A, Book B]

        System.out.println("Is the stack empty? " + bookStack.empty()); // Output: Is the stack empty? false
    }

    public static void demonstrateProblematicUsage() {
        Stack<Integer> numberStack = new Stack<>();
        numberStack.push(10);
        numberStack.push(20);
        numberStack.push(30);

        System.out.println("Initial stack: " + numberStack); // Output: Initial stack: [10, 20, 30]

        System.out.println("\n*** Breaking the LIFO principle! ***");

        int bottomElement = numberStack.get(0);
        System.out.println("Getting element at index 0 (the bottom!): " + bottomElement); // Output: Getting element at index 0 (the bottom!): 10

        numberStack.add(1, 99);
        System.out.println("Stack after adding 99 in the middle: " + numberStack); // Output: Stack after adding 99 in the middle: [10, 99, 20, 30]

        System.out.println("\nThis is why we should use Deque (like ArrayDeque) instead of Stack.");
        System.out.println("An ArrayDeque used as a stack does not have these problematic methods.");
    }
}
