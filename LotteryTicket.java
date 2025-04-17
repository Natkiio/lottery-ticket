// Nathan Lyons
// Software Dev CA1

public class LotteryTicket {  // Public class to represent a lottery ticket

    // array to store the user's selected numbers
    private int[] userNumbers;

    // array to store the winning numbers to compare against
    private int[] winningNumbers;

    // Constructor - initialises the ticket with the user's chosen numbers
    public LotteryTicket(int[] numbers) {
        // Create a new array and copy values manually
        userNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            userNumbers[i] = numbers[i];
        }
    }

    // setter method to assign the winning numbers to this ticket
    public void setWinningNumbers(int[] numbers) {
        
        winningNumbers = new int[numbers.length]; // copy winning numbers to a new array
        for (int i = 0; i < numbers.length; i++) {
            winningNumbers[i] = numbers[i];
        }
    }

    // getter method to retrieve the user's ticket numbers
    public int[] getUserNumbers() {
        return userNumbers;
    }

    //Compute
    // method to compute the result of the ticket:
    // compares userNumbers with winningNumbers and returns a result string
    public String computeResult() {
        int matches = 0; // Counter to track how many numbers match

        // loop through each number in the user's ticket
        for (int i = 0; i < userNumbers.length; i++) {
         
            for (int j = 0; j < winningNumbers.length; j++) {    // for each user number, check if it exists in the winning numbers
                if (userNumbers[i] == winningNumbers[j]) {
                    matches++; // increase match count
                    break; // stop looking after a match
                }
            }
        }

        // Return result string depending on how many numbers matched
        if (matches == 6) {
            return "Jackpot! All 6 numbers matched! Congrats!";
        } else if (matches >= 3) {
            return "You matched " + matches + " numbers!";
        } else {
            return "Sorry, only " + matches + " match(es). Better luck next time!";
        }
    }
}