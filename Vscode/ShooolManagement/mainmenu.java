import java.util.Scanner;

public class mainmenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("SCHOOL MANAGEMENT SYSTEM");
            System.out.println("");
            System.out.println("1. Student Management");
            System.out.println("2. Courses");
            System.out.println("3. Fee Tracking");
            System.out.println("4. Library System");
            System.out.println("5. Performance Analytics");
            System.out.println("6. Exit");
            System.out.println("------------------------------");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine(); // clear the buffer

            switch (choice) {
                case 1:
                    Students studentModule = new Students();
                    studentModule.run();
                    break;

                case 2:
                    Courses courseModule = new Courses();
                    courseModule.run();
                    break;

                case 3:
                    Fees feeModule = new Fees();
                    feeModule.run();
                    break;

                case 4:
                    Library libraryModule = new Library();
                    libraryModule.run();
                    break;

                case 5:
                    perfomance performanceModule = new perfomance();
                    performanceModule.run();
                    break;

                case 6:
                    System.out.println(" Exiting System... Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice! Try again.");
            }

        } while (choice != 6);

        input.close();
    }
}
