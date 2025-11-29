import java.util.Scanner;
import java.util.Stack;

public class Library {

    private Stack<String> borrowedBooks;

    // Constructor
    public Library() {
        borrowedBooks = new Stack<>();
    }

    // Called from MainMenu
    public void run() {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- LIBRARY SYSTEM ---");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Last Borrowed Book");
            System.out.println("4. Display All Borrowed Books");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println(" Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title to Borrow: ");
                    String title = input.nextLine().trim();
                    borrowBook(title);
                    break;

                case 2:
                    returnBook();
                    break;

                case 3:
                    checkLastBorrowed();
                    break;

                case 4:
                    displayBorrowedBooks();
                    break;

                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }

    // Push a book onto the stack (borrow)
    public void borrowBook(String title) {
        if (title.isEmpty()) {
            System.out.println("Invalid title. Try again.");
            return;
        }
        borrowedBooks.push(title);
        System.out.println("Book borrowed: " + title);
    }

    // Pop a book from the stack (return)
    public void returnBook() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books to return.");
        } else {
            String returned = borrowedBooks.pop();
            System.out.println("Book returned: " + returned);
        }
    }

    // Peek at the top of the stack
    public void checkLastBorrowed() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed yet.");
        } else {
            System.out.println("Last borrowed book: " + borrowedBooks.peek());
        }
    }

    // Display all borrowed books
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books at the moment.");
            return;
        }

        System.out.println("\n Borrowed Books (Most recent first):");
        int index = borrowedBooks.size();
        for (int i = borrowedBooks.size() - 1; i >= 0; i--) {
            System.out.println(index - i + ". " + borrowedBooks.get(i));
        }
    }
}
