import java.util.Scanner;

public class Fees {

    // Node class for BST
    private static class FeeNode {
        String studentID;
        double amount;
        FeeNode left, right;

        FeeNode(String studentID, double amount) {
            this.studentID = studentID;
            this.amount = amount;
            this.left = this.right = null;
        }
    }

    private FeeNode root; // Root of the tree

    // Public run method called from MainMenu
    public void run() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- FEE TRACKING ---");
            System.out.println("1. Add Payment");
            System.out.println("2. Search Payment");
            System.out.println("3. Display All Payments");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice = 0) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = input.nextLine().trim();
                    System.out.print("Enter Amount Paid: ");
                    if (!input.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        input.nextLine();
                        break;
                    }
                    double amount = input.nextDouble();
                    input.nextLine();
                    addPayment(id, amount);
                    break;

                case 2:
                    System.out.print("Enter Student ID to Search: ");
                    searchPayment(input.nextLine().trim());
                    break;

                case 3:
                    displayAllPayments();
                    break;

                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println(" Invalid choice! Try again.");
            }

        } while (choice != 4);
    }

    // Add new payment record
    public void addPayment(String studentID, double amount) {
        root = insertRecursive(root, studentID, amount);
        System.out.println("Payment recorded: " + studentID + " | Amount: " + amount);
    }

    private FeeNode insertRecursive(FeeNode root, String studentID, double amount) {
        if (root == null) {
            return new FeeNode(studentID, amount);
        }
        if (studentID.compareTo(root.studentID) < 0) {
            root.left = insertRecursive(root.left, studentID, amount);
        } else if (studentID.compareTo(root.studentID) > 0) {
            root.right = insertRecursive(root.right, studentID, amount);
        } else {
            System.out.println("Payment already exists for " + studentID + ". Updating amount.");
            root.amount = amount;
        }
        return root;
    }

    // Search payment by Student ID
    public void searchPayment(String studentID) {
        FeeNode result = searchRecursive(root, studentID);
        if (result != null) {
            System.out.println("Payment Found: " + result.studentID + " | Amount: " + result.amount);
        } else {
            System.out.println("No payment record found for " + studentID);
        }
    }

    private FeeNode searchRecursive(FeeNode root, String studentID) {
        if (root == null || root.studentID.equals(studentID)) {
            return root;
        }
        if (studentID.compareTo(root.studentID) < 0) {
            return searchRecursive(root.left, studentID);
        } else {
            return searchRecursive(root.right, studentID);
        }
    }

    // Display all payments sorted by Student ID (in-order traversal)
    public void displayAllPayments() {
        if (root == null) {
            System.out.println("No payment records available.");
            return;
        }
        System.out.println("\nFee Payment Records (Sorted):");
        inOrderTraversal(root);
    }

    private void inOrderTraversal(FeeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("Student ID: " + node.studentID + " | Amount: " + node.amount);
            inOrderTraversal(node.right);
        }
    }
}
