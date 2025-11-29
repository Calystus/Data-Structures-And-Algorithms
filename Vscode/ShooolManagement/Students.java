import java.util.Scanner;

public class Students {

    // Node class for linked list
    private static class Node {
        String id;
        String name;
        String course;

        Node next;

        Node(String id, String name, String course) {
            this.id = id;
            this.name = name;
            this.course = course;
            this.next = null;
        }
    }4
    

    private Node head; // start of the linked list

    // public run() called from MainMenu
    public void run() {
        Scanner input = new Scanner(System.in); // do NOT close this to avoid closing System.in for MainMenu
        int choice = 0;
        do {
            System.out.println("\n--- STUDENT MANAGEMENT ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student by ID");
            System.out.println("3. Delete Student by ID");
            System.out.println("4. Display All Students");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            // guard against non-int input
            if (!input.hasNextInt()) {
                System.out.println("Please enter a number.");
                input.nextLine(); // consume invalid token
                continue;
            }

            choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = input.nextLine().trim();
                    System.out.print("Enter Name: ");
                    String name = input.nextLine().trim();
                    System.out.print("Enter Course: ");
                    String course = input.nextLine().trim();
                    addStudent(id, name, course);
                    break;
                case 2:
                    System.out.print("Enter Student ID to search: ");
                    searchStudent(input.nextLine().trim());
                    break;
                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    deleteStudent(input.nextLine().trim());
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        // NOTE: not closing 'input' to avoid closing System.in used by MainMenu
    }

    // Adds student at end of linked list
    public void addStudent(String id, String name, String course) {
        if (id == null || id.isEmpty()) {
            System.out.println("Invalid ID. Student not added.");
            return;
        }

        Node newNode = new Node(id, name, course);
        if (head == null) {
            head = newNode;
        } else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newNode;
        }
        System.out.println("Student added: " + id + " | " + name + " | " + course);
    }

    // Search and print student
    public void searchStudent(String id) {
        if (head == null) {
            System.out.println("No students registered yet.");
            return;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.id.equals(id)) {
                System.out.println("Found -> ID: " + cur.id + " | Name: " + cur.name + " | Course: " + cur.course);
                return;
            }
            cur = cur.next;
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    // Delete student by ID
    public void deleteStudent(String id) {
        if (head == null) {
            System.out.println("No students to delete.");
            return;
        }

        // if head is the one to remove
        if (head.id.equals(id)) {
            head = head.next;
            System.out.println("Student " + id + " deleted.");
            return;
        }

        Node cur = head;
        while (cur.next != null && !cur.next.id.equals(id)) {
            cur = cur.next;
        }

        if (cur.next == null) {
            System.out.println("Student with ID " + id + " not found.");
        } else {
            cur.next = cur.next.next;
            System.out.println("Student " + id + " deleted.");
        }
    }

    // Display all students
    public void displayAllStudents() {
        if (head == null) {
            System.out.println(" No student data available.");
            return;
        }

        System.out.println("\n Registered Students:");
        Node cur = head;
        int idx = 1;
        while (cur != null) {
            System.out.println(idx + ". ID: " + cur.id + " | Name: " + cur.name + " | Course: " + cur.course);
            idx++;
            cur = cur.next;
        }
    }
}