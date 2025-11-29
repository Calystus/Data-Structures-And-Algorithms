import java.util.*;

public class perfomance {

    // Graph data using adjacency list
    private Map<String, List<String>> graph;
    private Map<String, Integer> studentScores;

    // Constructor
    public perfomance() {
        graph = new HashMap<>();
        studentScores = new HashMap<>();
    }

    // Main run() method called from MainMenu
    public void run() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- PERFORMANCE ANALYTICS ---");
            System.out.println("1. Add Student & Score");
            System.out.println("2. Add Relation (Between Students)");
            System.out.println("3. Display Graph");
            System.out.println("4. Show Top Performer");
            System.out.println("5. Show Students Above Pass mark");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = input.nextLine().trim();
                    System.out.print("Enter Score: ");
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid score. Must be a number.");
                        input.nextLine();
                        break;
                    }
                    int score = input.nextInt();
                    input.nextLine();
                    addStudent(id, score);
                    break;

                case 2:
                    System.out.print("Enter First Student ID: ");
                    String s1 = input.nextLine().trim();
                    System.out.print("Enter Second Student ID: ");
                    String s2 = input.nextLine().trim();
                    addRelation(s1, s2);
                    break;

                case 3:
                    displayGraph();
                    break;

                case 4:
                    findTopPerformer();
                    break;

                case 5:
                    System.out.print("Enter Score Threshold: ");
                    if (!input.hasNextInt()) {
                        System.out.println("Invalid threshold.");
                        input.nextLine();
                        break;
                    }
                    int threshold = input.nextInt();
                    input.nextLine();
                    displayAboveThreshold(threshold);
                    break;

                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 6);
    }

    // ===============================
    // Core Operations
    // ===============================

    // Add a new student to the graph
    public void addStudent(String id, int score) {
        if (id.isEmpty()) {
            System.out.println("Invalid ID. Try again.");
            return;
        }
        graph.putIfAbsent(id, new ArrayList<>());
        studentScores.put(id, score);
        System.out.println("Student added: " + id + " | Score: " + score);
    }

    // Create a relation between two students
    public void addRelation(String student1, String student2) {
        if (!graph.containsKey(student1) || !graph.containsKey(student2)) {
            System.out.println("Both students must exist first.");
            return;
        }
        graph.get(student1).add(student2);
        graph.get(student2).add(student1);
        System.out.println("Linked " + student1 + " ↔ " + student2);
    }

    // Display all students and their relationships
    public void displayGraph() {
        if (graph.isEmpty()) {
            System.out.println("No student data yet.");
            return;
        }

        System.out.println("\n Student Performance Graph:");
        for (String student : graph.keySet()) {
            System.out.print(student + " (" + studentScores.get(student) + "): ");
            System.out.println(graph.get(student));
        }
    }

    // Find the student with the highest score
    public void findTopPerformer() {
        if (studentScores.isEmpty()) {
            System.out.println("No scores available.");
            return;
        }

        String topStudent = null;
        int topScore = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            if (entry.getValue() > topScore) {
                topScore = entry.getValue();
                topStudent = entry.getKey();
            }
        }

        System.out.println("\nTop Performer: " + topStudent + " | Score: " + topScore);
    }

    // Display all students above a score threshold
    public void displayAboveThreshold(int threshold) {
        if (studentScores.isEmpty()) {
            System.out.println("No score data available.");
            return;
        }

        System.out.println("\n Students Scoring Above " + threshold + ":");
        boolean found = false;

        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            if (entry.getValue() >= threshold) {
                System.out.println("" + entry.getKey() + " | Score: " + entry.getValue());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found above that pass mark.");
        }
    }
}