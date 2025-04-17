/*Nathan lyons
 * software dev ca1
 */

import java.util.*; // importing all classes from the java.util package 


public class LotteryApp { // making the public class named LotteryApp

    
    private static Scanner scanner = new Scanner(System.in); // declaring a Scanner object for user input

 
    private static int[][] previousTickets = new int[100][6];    // A 2D array to store up to 100 lottery tickets, each with 6 numbers

    private static int ticketCount = 0; // counter to keep track of how many tickets have been purchased

   
    private static int[] winningNumbers = new int[6];  // array to store the 6 winning numbers once generated

    public static void main(String[] args) { // main method - entry point 
      
        while (true) {   //  loop that continues until user chooses to exit
            // Displaying the main menu
            System.out.println("\nLottery Application");
            System.out.println("1 - Purchase a Lottery Ticket");
            System.out.println("2 - Generate the Winning Numbers");
            System.out.println("3 - Check to see if you Won");
            System.out.println("4 - View older enteries");
            System.out.println("5 - Exit the app");
            System.out.print("Enter your choice: ");

          
            int choice = scanner.nextInt();   // reading user menu choice
            scanner.nextLine(); // consumes the leftover newline

            // Handling each menu option
            if (choice == 1) {
                buyLotteryTicket(); // Calls method to buy a ticket
            } else if (choice == 2) {
                generateWinningNumbers(); // Calls method to generate winning numbers
            } else if (choice == 3) {
                checkResults(); // Calls method to check if user won
            } else if (choice == 4) {
                viewPreviousTickets(); // Calls method to view all tickets
            } else if (choice == 5) {
                // Calls exitApplication() method and if it returns false exit loop
                if (!exitApplication()) {
                    break; // Exit the while loop
                }
            } else {
               
                System.out.println("Invalid option. Please enter a number between 1 and 5.");  // handles invalid input
            }
        }

       
        System.out.println("Thank you for playing!");  // Final goodbye message
    }


    private static void buyLotteryTicket() {     // method to allow the user to buy a lottery ticket
        // If maximum ticket limit reached, exit the method
        if (ticketCount >= 100) {
            System.out.println("The Maximum ticket number has been reached!");
            return;
        }

        // Create an array to store 6 user-selected numbers
        int[] userNumbers = new int[6];
        System.out.println("Please enter 6 unique numbers between 1 and 50:");


        for (int i = 0; i < 6; i++) {         // Loop 6 times to get valid user input
            int number;        // Temporary variable for input
            boolean valid;     // Flag for a valid input

            // Repeat until valid number entered
            do {
                System.out.print("Enter number " + (i + 1) + ": ");
                number = scanner.nextInt(); // Take number input
                
                valid = number >= 1 && number <= 50 && !contains(userNumbers, number, i); // Validate range and uniqueness

                if (!valid) {
                    System.out.println("Invalid input! the numbers must be unique between 1 and 50.");
                }
            } while (!valid);

            userNumbers[i] = number; // save the valid number
        }

        
        for (int j = 0; j < 6; j++) { // Store the ticket in the 2D array
            previousTickets[ticketCount][j] = userNumbers[j];
        }

        ticketCount++; // Increase the number of tickets stored
        System.out.println("The Lottery ticket has been saved!");
    }

    // Method to randomly generate 6 unique winning numbers
    private static void generateWinningNumbers() {
        int[] generated = new int[6]; // temp array for generated numbers
        int count = 0; // counter for valid numbers

        // Repeat until 6 unique numbers are generated
        while (count < 6) {
            int num = (int)(Math.random() * 50) + 1; // Random number between 1–50 using math.random
            boolean duplicate = false; // Flag to check for duplicates

            // Check if number is already in array
            for (int i = 0; i < count; i++) {
                if (generated[i] == num) {
                    duplicate = true; // Found a duplicate
                    break;
                }
            }

            // If not a duplicate, save it that number
            if (!duplicate) {
                generated[count] = num;
                count++;
            }
        }

    
        for (int i = 0; i < 6; i++) {   // copy generated numbers into global winningNumbers array
            winningNumbers[i] = generated[i];
        }

        System.out.println("The Winning numbers have been generated!");
    }

    // Method to check user tickets against winning numbers
    private static void checkResults() {
        // If no tickets exist, show this message
        if (ticketCount == 0) {
            System.out.println("No tickets have been purchased yet!");
            return;
        }

        // If winning numbers haven't been generated, show message
        if (winningNumbers[0] == 0) {
            System.out.println("The Winning numbers have not been generated!");
            return;
        }

       
        for (int i = 0; i < ticketCount; i++) {  // Loop through all saved tickets
            // Create a LotteryTicket object from the saved ticket
            LotteryTicket ticket = new LotteryTicket(previousTickets[i]);

            // Set winning numbers inside the ticket object
            ticket.setWinningNumbers(winningNumbers);

            // Print user ticket, winning numbers, and the result
            System.out.println("Your ticket: " + Arrays.toString(ticket.getUserNumbers()));
            System.out.println("Winning numbers: " + Arrays.toString(winningNumbers));
            System.out.println("Result: " + ticket.computeResult()); // Call method to compare and get result
        }
    }

    // Method to show all previously entered tickets
    private static void viewPreviousTickets() {
        // If no tickets show message! 
        if (ticketCount == 0) {
            System.out.println("No previous tickets have been found.");
            return;
        }

        System.out.println("Previous Lottery Entries:");
        // Loop through all tickets
        for (int i = 0; i < ticketCount; i++) {
            System.out.print("Ticket " + (i + 1) + ": ");
            // print each number of the ticket
            for (int j = 0; j < 6; j++) {
                System.out.print(previousTickets[i][j] + " ");
            }
            System.out.println(); // move to the next line
        }
    }

   
    private static boolean exitApplication() {  // Method to handle exiting the application
        System.out.print("Would you like to begin a new lottery draw? (yes/no): ");
        // If user says "yes", return true (continue loop), else false (exit)
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    //  method to check if an array contains a specific number 
    private static boolean contains(int[] arr, int num, int length) {
        for (int i = 0; i < length; i++) {
            if (arr[i] == num) {
                return true; // Number already exists in the array
            }
        }
        return false; // Number not found, it's valid
    }
}
