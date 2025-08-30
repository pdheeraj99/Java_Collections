import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// --- The Player Class ---
// It implements Comparable to define its "natural order" as sorting by rank.
class Player implements Comparable<Player> {
    private final String name;
    private final int rank;
    private final int score;

    public Player(String name, int rank, int score) {
        this.name = name;
        this.rank = rank;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', rank=" + rank + ", score=" + score + '}';
    }

    // This is the "natural ordering" for a Player.
    // We've decided that players are naturally sorted by their rank.
    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.rank, other.rank);
    }
}

// --- A separate Comparator for a custom order ---
// This class defines a rule to sort players by their score in descending order.
class PlayerScoreComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        // We want higher score to come first, so we reverse the comparison
        return Integer.compare(p2.getScore(), p1.getScore());
    }
}


public class ComparableComparatorExample {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Mawa", 3, 1500));
        players.add(new Player("Jules", 1, 2200));
        players.add(new Player("Alex", 2, 1800));

        System.out.println("Original list of players:");
        players.forEach(System.out::println);

        // --- 1. Sorting using Comparable (Natural Order) ---
        System.out.println("\n--- Sorting by Rank (Natural Order using Comparable) ---");
        Collections.sort(players); // This uses the compareTo() method inside the Player class
        players.forEach(System.out::println);

        // --- 2. Sorting using a separate Comparator class ---
        System.out.println("\n--- Sorting by Score (Custom Order using Comparator Class) ---");
        Collections.sort(players, new PlayerScoreComparator());
        players.forEach(System.out::println);

        // --- 3. Sorting using a lambda expression (Modern & Concise Comparator) ---
        System.out.println("\n--- Sorting by Name (Custom Order using Lambda) ---");
        // We don't need to create a whole new class. We can define the rule on the fly.
        players.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        players.forEach(System.out::println);
    }
}
