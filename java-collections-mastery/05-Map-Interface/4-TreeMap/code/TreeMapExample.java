import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class ExamResult implements Comparable<ExamResult> {
    private final String studentName;
    private final int score;

    public ExamResult(String studentName, int score) {
        this.studentName = studentName;
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return studentName + " (" + score + "%)";
    }

    @Override
    public int compareTo(ExamResult other) {
        return this.studentName.compareTo(other.studentName);
    }
}

class ScoreComparator implements Comparator<ExamResult> {
    @Override
    public int compare(ExamResult r1, ExamResult r2) {
        return Integer.compare(r2.getScore(), r1.getScore());
    }
}

public class TreeMapExample {

    public static void main(String[] args) {
        System.out.println("--- 1. TreeMap with Natural Ordering (by Student Name) ---");
        demonstrateNaturalOrdering();

        System.out.println("\n--- 2. TreeMap with Custom Ordering (by Score Desc) ---");
        demonstrateCustomOrdering();
    }

    public static void demonstrateNaturalOrdering() {
        Map<ExamResult, String> studentGrades = new TreeMap<>();

        studentGrades.put(new ExamResult("Mawa", 95), "A+");
        studentGrades.put(new ExamResult("Jules", 88), "A");
        studentGrades.put(new ExamResult("Alex", 92), "A+");

        System.out.println("Student grades sorted by name (Natural Order):");
        studentGrades.forEach((result, grade) ->
            System.out.println(result + " -> Grade: " + grade)
        );
        // Output:
        // Alex (92%) -> Grade: A+
        // Jules (88%) -> Grade: A
        // Mawa (95%) -> Grade: A+
    }

    public static void demonstrateCustomOrdering() {
        TreeMap<ExamResult, String> studentGrades = new TreeMap<>(new ScoreComparator());

        studentGrades.put(new ExamResult("Mawa", 95), "A+");
        studentGrades.put(new ExamResult("Jules", 88), "A");
        studentGrades.put(new ExamResult("Alex", 92), "A+");

        System.out.println("Student grades sorted by score (Custom Order):");
        studentGrades.forEach((result, grade) ->
            System.out.println(result + " -> Grade: " + grade)
        );
        // Output:
        // Mawa (95%) -> Grade: A+
        // Alex (92%) -> Grade: A+
        // Jules (88%) -> Grade: A

        System.out.println("\nHighest scoring student (firstEntry): " + studentGrades.firstEntry()); // Output: Highest scoring student (firstEntry): Mawa (95%)=A+
    }
}
