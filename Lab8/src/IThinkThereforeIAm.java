import java.util.Scanner;

public class IThinkThereforeIAm {

    /**
     * Finding Qualities from User Inputs Beginning with "I am"
     * @author Daniel Jonas
     */

    //=============================================================================
    private static final Scanner keyboard = new Scanner(System.in);
    //=============================================================================
    public static void main(String[] args) {

        String finalSentence;
        // Defines object that will store qualities
        finalSentence = enterSentences();
        // Assigns qualities from user inputs in enterSentences to finalSentence
        displaySentence(finalSentence);
        // Displays finalSentence to user

    } // End of Main Method

    public static String enterSentences() {

        String testSentence = "I am";
        String compoundingSentences = "The qualities are";
        // Defining and Initializing Objects
        System.out.println("Please enter sentences, . to end.");
        String response = keyboard.nextLine();
        // Receives First response from User

        while (!response.equals(".")) {

            if (response.startsWith(testSentence)){

                compoundingSentences +=  " " + response.substring(5) + ",";
                // Concatenates Qualities into compoundingSentences

            }
            response = keyboard.nextLine();
            // Receives Next Response from User

        } // End of While Loop

        return compoundingSentences;

    } // End of Method enterSentences

    public static void displaySentence(String finalSentence) {

        System.out.println(finalSentence);

    } // End of Method displaySentence
} // End of Class IThinkThereforeIAm
