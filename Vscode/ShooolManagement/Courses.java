import java.util.Scanner;

public class Courses {

    private String[] queue;   // stores student IDs in course queue
    private int front;
    private int rear;
    private int size;
    private int capacity;

    // Constructor
    public Courses() {
        this.capacity = 5; // default capacity, can be changed
        queue = new String[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Main method called from MainMenu
    public void run() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- COURSE SCHEDULING ---");
            System.out.println("1. Enqueue Student");
            System.out.println("2. Dequeue Student (Allocate Course)");
            System.out.println("3. Display Queue");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID to register: ");
                    String id = input.nextLine().trim();
                    enqueue(id);
                    break;

                case 2:
                    dequeue();
                    break;

                case 3:
                    displayQueue();
                    break;

                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println(" Invalid choice! Try again.");
            }

        } while (choice != 4);
    }

    // Add student to queue (enqueue)
    public void enqueue(String studentID) {
        if (isFull()) {
            System.out.println("Course queue is full. Cannot add " + studentID);
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = studentID;
        size++;
        System.out.println("Student added to course queue: " + studentID);
    }

    // Remove student from queue (dequeue)
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("No students waiting in queue.");
            return;
        }

        String studentID = queue[front];
        front = (front + 1) % capacity;
        size--;
        System.out.println("Student allocated course: " + studentID);
    }

    // Display current queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No students currently in course queue.");
            return;
        }

        System.out.println("\nCurrent Course Queue:");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.println((i + 1) + ". " + queue[index]);
        }
    }

    // Helper methods
    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == capacity;
    }
}
