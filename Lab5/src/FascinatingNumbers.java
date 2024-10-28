import java.util.Scanner;

public class FascinatingNumbers {
    /**
    * Finding Fibonacci and Prime Numbers from an Inputted Sequence
    * @author Daniel Jonas
    */

    //=============================================================================

    /**
    * Keyboard allows for user input through the keyboard
    * NUMBER_OF_INTS
     */

    private static final Scanner keyboard = new Scanner(System.in);
    private static final int NUMBER_OF_INTS = 10;
    //=============================================================================

    public static void main(String[] args) {

        /**
         * Method description.
         * @param One-for-each-parameter-name Parameter-description
         * @return Return value description
         * @see
         */

        // Declares and Initializes Variables and Arrays
        int[] inputs = new int[NUMBER_OF_INTS];
        boolean fibonacciSequence;
        boolean primeNumber;
        int listNumber;
        int index;

        // Finds Total Number of Integers using Method enterIntegers
        listNumber = enterIntegers(inputs);

        for (index = 0; index < (listNumber-1); index++) {

            // Tests whether Inputs are Fibonacci Numbers or Prime using Methods testFibonacci and testPrime, Respectively
            fibonacciSequence = testFibonacci(inputs[index]);
            primeNumber = testPrime(inputs[index]);

            // Displays Results for Each as Determined by testFibonacci and testPrime
            System.out.print(inputs[index] + " is ");
            if (fibonacciSequence) {
                System.out.print("Fibonacci ");
            }
            else {
                System.out.print("not Fibonacci ");
            }
            System.out.print("and ");
            if (primeNumber) {
                System.out.println("prime.");
            }
            else {
                System.out.println("not prime.");
            }
        }
    }

    private static int enterIntegers(int[] inputs) {

        /**
        * Stores the Integers and Array Length
        * @param inputs Used to Store Values Inputted by User
        * @return numCount Number of Inputs Given by User
         */

        // Declares Variables
        int numCount = 0;
        int userInput;

        System.out.print("Enter next number (0 to stop): ");
        do {
            // Receives Input from User
            userInput = keyboard.nextInt();

            // Assigns Values to Array Inputs
                inputs[numCount] = userInput;
                numCount++;

        } while (numCount < inputs.length && userInput != 0);

        return numCount; // Returns Number of Inputs
    } // End of Method enterIntegers

    private static boolean testFibonacci(int input) {

        /**
         * Identifies if each Number in the Array is a Fibonacci Number
         * @param inputs Used to Recall User's Inputted Values
         * @return (testTwo == input) Checks if the greatest Fibonacci Value beneath Input is equal to Input, Input is Fibonacci; else, Input is Not Fibonacci
         */

        // Declares Variables
        int testOne = 0;
        int testTwo = 1;
        int testThree;

        if (input == testTwo) {
            return true; // Covers Situations where Input Number is Smaller than 2
        }

        while (testTwo < input) { // While Loop Generates the Fibonacci Sequence within Variable testTwo (1,2,3,5,8,13...)
            testThree = testOne + testTwo;
            testOne = testTwo;
            testTwo = testThree;
        }

        return (testTwo == input);
    } // End of Method testFibonacci

    private static boolean testPrime(int input) {

        /**
         * Identifies if each Number in the Array is a Prime Number
         * @param (inputs) Used to Recall User's Inputted Values
         * @return (false) The Value Recalled by input is either 1 (Not Prime) or has a value between 1 and input that is evenly divided
         * @return (true) There is no Integer testingNumbers from 1-input that Produces a Modulus of 0; QED Prime
         */

        if (input == 1) {
            return false; // Covers Situations where Input is Smaller than 2
        }

        for (int testingNumbers = 2; testingNumbers < input; testingNumbers++) {

            if (input % testingNumbers == 0) {
                return false; // If Modulus Equals Zero, input is cleanly divisible by testingNumbers; QED not Prime
            }

        }

        return true; // There is no Integer testingNumbers from 1-input that Produces a Modulus of 0; QED Prime
    } // End of Method testPrime
} // End of Class FascinatingNumbers
