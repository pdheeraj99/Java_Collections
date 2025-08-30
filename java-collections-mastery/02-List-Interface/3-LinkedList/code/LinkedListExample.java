import java.util.LinkedList;

public class LinkedListExample {

    public static void main(String[] args) {
        System.out.println("====== LinkedList as a List ======");
        demonstrateListBehavior();

        System.out.println("\n====== LinkedList as a Queue (FIFO) ======");
        demonstrateQueueBehavior();

        System.out.println("\n====== LinkedList as a Stack (LIFO) ======");
        demonstrateStackBehavior();
    }

    public static void demonstrateListBehavior() {
        LinkedList<String> musicPlaylist = new LinkedList<>();

        musicPlaylist.add("Song A");
        musicPlaylist.add("Song B");
        musicPlaylist.add("Song C");
        System.out.println("Initial Playlist: " + musicPlaylist); // Output: Initial Playlist: [Song A, Song B, Song C]

        musicPlaylist.add(1, "New Song");
        System.out.println("After adding in middle: " + musicPlaylist); // Output: After adding in middle: [Song A, New Song, Song B, Song C]

        musicPlaylist.remove(2);
        System.out.println("After removing from middle: " + musicPlaylist); // Output: After removing from middle: [Song A, New Song, Song C]

        System.out.println("Getting second song: " + musicPlaylist.get(1)); // Output: Getting second song: New Song
    }

    public static void demonstrateQueueBehavior() {
        LinkedList<String> customerQueue = new LinkedList<>();

        customerQueue.offer("Customer 1");
        customerQueue.offer("Customer 2");
        customerQueue.offer("Customer 3");
        System.out.println("Customers in queue: " + customerQueue); // Output: Customers in queue: [Customer 1, Customer 2, Customer 3]

        System.out.println("Next customer to be served: " + customerQueue.peek()); // Output: Next customer to be served: Customer 1

        String servedCustomer1 = customerQueue.poll();
        System.out.println("Served: " + servedCustomer1); // Output: Served: Customer 1
        System.out.println("Remaining customers: " + customerQueue); // Output: Remaining customers: [Customer 2, Customer 3]

        String servedCustomer2 = customerQueue.poll();
        System.out.println("Served: " + servedCustomer2); // Output: Served: Customer 2
        System.out.println("Remaining customers: " + customerQueue); // Output: Remaining customers: [Customer 3]
    }

    public static void demonstrateStackBehavior() {
        LinkedList<String> browserHistory = new LinkedList<>();

        browserHistory.push("google.com");
        browserHistory.push("github.com");
        browserHistory.push("our-repo.com");
        System.out.println("Browser History (Stack): " + browserHistory); // Output: Browser History (Stack): [our-repo.com, github.com, google.com]

        System.out.println("Current page is: " + browserHistory.peek()); // Output: Current page is: our-repo.com

        String previousPage1 = browserHistory.pop();
        System.out.println("Clicked Back. Went from " + previousPage1 + " to " + browserHistory.peek()); // Output: Clicked Back. Went from our-repo.com to github.com

        String previousPage2 = browserHistory.pop();
        System.out.println("Clicked Back. Went from " + previousPage2 + " to " + browserHistory.peek()); // Output: Clicked Back. Went from github.com to google.com
        System.out.println("Final History: " + browserHistory); // Output: Final History: [google.com]
    }
}
