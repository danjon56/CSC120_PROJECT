import java.util.Scanner;

/**
 * Storing and Manipulating Familial Dental Records
 * @author  Daniel Jonas
 * @version 22.0.2
 */

public class DentalRecords {

    //=============================================================================

    /**
     * Global scanner object to use keyboard
     */

    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * Provides the maximum amount of family member inputs for the program
     */

    private static final int MAX_NUMBER_OF_FAMILY_MEMBERS = 6;

    /**
     * Provides the maximum amount of teeth inputs for the program
     */

    private static final int MAX_NUMBER_OF_TEETH = 8;
    //=============================================================================

    /**
     * Declares and initializes variables needed to create arrays and begin receiving inputs from the user.
     * After the teeth have been entered into the program, it allows the user to manipulate and display the data.
     */

    public static void main(String[] args) {

        int familyTotal;
//----Stores number of family members in familyTotal
        familyTotal = getNumberOfFamilyMembers();

//----Declares and initializes variables
        String[] names = new String[familyTotal];
        char[][][] toothInfo = new char[familyTotal][2][MAX_NUMBER_OF_TEETH]; // [Person Number 1,2,3...] [Top/Bottom] [Tooth 1,2,3...]
        char userResponse;
        boolean displayMenu = true;

//----Receives family member names from user via getFamilyMemberNames
        getFamilyMemberNames(toothInfo, names);

//----Menu for user to operate program
        do {

//--------Toggles instructions on/off in case of an input error (see line 90)
            if (displayMenu) {

                System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it          :");

            }

            displayMenu = true;

//--------User inputs next command as prompted, which is stored in userResponse
            userResponse = keyboard.nextLine().charAt(0);

            switch (userResponse) {

                case 'p':
                case 'P':
                    printTeeth(names, toothInfo);
                    break;

                case 'e':
                case 'E':
                    toothInfo = extractTooth(toothInfo, names);
                    break;

                case 'r':
                case 'R':
                    getRootIndex(toothInfo, names);
                    break;

//------------Displays exit message and terminates program
                case 'x':
                case 'X':
                    System.out.println("\nExiting the Floridian Tooth Records :-)");
                    break;

//------------In case of an input error, an error message is displayed and user is immediately asked for another input
                default:
                    System.out.print("Invalid menu option, try again              :");
                    displayMenu = false;
                    break;

            }

        } while (userResponse != 'X');

    }

    /**
     * Asks the user to input the number of family members.
     * @return Number of family members.
     */

    private static int getNumberOfFamilyMembers() {

        int numberOfFamilyMembers;

//----Displays welcome message
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");
        System.out.print("Please enter number of people in the family :");

//----Assigns user input to numberOfFamilyMembers
        numberOfFamilyMembers = keyboard.nextInt();

//----Identifies possible errors in input and corrects for them
        if (numberOfFamilyMembers < 0 || numberOfFamilyMembers > MAX_NUMBER_OF_FAMILY_MEMBERS) {

            do {

                System.out.print("Invalid number of people, try again         :");
                numberOfFamilyMembers = keyboard.nextInt();

            } while (numberOfFamilyMembers < 0 || numberOfFamilyMembers > MAX_NUMBER_OF_FAMILY_MEMBERS);

        }

//----Returns number of family members to main
        return numberOfFamilyMembers;

    }

    /**
     * Asks the user to enter the names and corresponding upper/lower teeth.
     * @param toothInfo 3-Dimensional array made to store each person's upper and lower teeth.
     * @param names Names of each family member.
     */

    private static void getFamilyMemberNames(char[][][] toothInfo, String[] names) {

//----Defines variables
        int familyMemberIncrement;
        int upperLowerIncrement;
        int toothIncrement;
        String position;
        String teethInput;
        boolean containsImproperInput = true;
        int teethCheckIncrement;

//----Absorbs \n from getNumberOfFamilyMembers
        keyboard.nextLine();

        for (familyMemberIncrement = 0; familyMemberIncrement < names.length; familyMemberIncrement++) {

//--------Assigns each family member's name to a position in the array names
            System.out.print("Please enter the name for family member " + (familyMemberIncrement +1) + "   :");
            names[familyMemberIncrement] = keyboard.nextLine();

            for (upperLowerIncrement = 0; upperLowerIncrement < 2; upperLowerIncrement++) { // upperLowerIncrement = 0 for uppers, upperLowerIncrement = 1 for lowers

                if (upperLowerIncrement == 0) {

                    position = "uppers";

                }else {

                    position = "lowers";

                }

//------------User inputs the teeth corresponding to the printed instructions
                System.out.print("Please enter the " + position + " for " + names[familyMemberIncrement] + "       :");
                teethInput = keyboard.nextLine().toUpperCase();

//------------User input is tested for improper numbers and types of teeth
                while (containsImproperInput) {

                    teethCheckIncrement = 0;

                    while (teethCheckIncrement < teethInput.length() && (teethInput.charAt(teethCheckIncrement) == 'I' || teethInput.charAt(teethCheckIncrement) == 'B' || teethInput.charAt(teethCheckIncrement) == 'M' || teethInput.charAt(teethCheckIncrement) == ' ')) {

                        teethCheckIncrement++;

                    }

                    if (teethCheckIncrement == teethInput.length() && teethInput.length() <= MAX_NUMBER_OF_TEETH) {

                        containsImproperInput = false;

                    } else if (teethInput.length() > MAX_NUMBER_OF_TEETH) {

                        System.out.print("Too many teeth, try again                   :");
                        teethInput = keyboard.nextLine().toUpperCase();

                    } else {

                        System.out.print("Invalid teeth types, try again              :");
                        teethInput = keyboard.nextLine().toUpperCase();

                    }

                }

//------------Teeth are assigned to their positions in the array toothInfo
                for (toothIncrement = 0; toothIncrement < MAX_NUMBER_OF_TEETH; toothIncrement++) {

                    if (toothIncrement < teethInput.length()) {

                        toothInfo[familyMemberIncrement][upperLowerIncrement][toothIncrement] = teethInput.charAt(toothIncrement);

                    } else {

                        toothInfo[familyMemberIncrement][upperLowerIncrement][toothIncrement] = ' '; // Fill with space if not enough input

                    }

                }

            }

        }

    }


    /**
     * Prints the names and teeth data provided by the user.
     * @param names Names of every family member.
     * @param toothInfo Values for the upper and lower teeth of every person.
     */

    private static void printTeeth (String[] names, char[][][] toothInfo) {

        int teethIncrement = 0;
        int nameIncrement;
        int upperLowerIncrement;
        String position;

        System.out.print("\n");

        for (nameIncrement = 0; nameIncrement < names.length; nameIncrement++) {

//--------Displays family member's name
            System.out.println(names[nameIncrement] + ":");

            for (upperLowerIncrement = 0; upperLowerIncrement < 2; upperLowerIncrement++) {

                if (upperLowerIncrement == 0) {

                    position = "Uppers";

                }else {

                    position = "Lowers";

                }

//------------Displays whether following teeth are uppers or lowers
                System.out.print("    " + position + ": ");

//------------Lists out each tooth in array toothInfo corresponding to that family member and layer
                while ((teethIncrement < MAX_NUMBER_OF_TEETH && teethIncrement < toothInfo[nameIncrement][upperLowerIncrement].length && toothInfo[nameIncrement][upperLowerIncrement][teethIncrement] != ' ')) {

                    System.out.print("  " + (teethIncrement+1) + ": " + toothInfo[nameIncrement][upperLowerIncrement][teethIncrement]);
                    teethIncrement++;

                }

//------------Resets teethIncrement in preparation for next layer/family member
                teethIncrement = 0;
                System.out.print("\n");

            }

        }

        System.out.print("\n");

    }

    /**
     * Modifies the data within toothInfo to reflect a tooth extraction (replacing a "B" or "I" with "M").
     * @param toothInfo Tooth data being edited to include an extra missing tooth.
     * @param names Names of all family members, used to identify who has had an extraction.
     * @return Teeth values, now including an extra missing tooth.
     */

    private static char[][][] extractTooth(char[][][] toothInfo, String[] names) {

        String selectName;
        int nameCheckIncrement = 0;
        boolean nameCheckFailed = true;
        int upperLowerChoice = 0;
        int toothSelection;
        char upperLowerResponse;
        boolean validResponse;

        System.out.print("Which family member                         :");

//----Identifies whether the family member selected was entered in previous method getFamilyMemberNames
        while (nameCheckFailed) {

            nameCheckIncrement = 0;

//--------Assigns selected family member's name to selectName
            selectName = keyboard.nextLine();

            while (nameCheckIncrement != names.length && !selectName.equalsIgnoreCase(names[nameCheckIncrement])) {

                nameCheckIncrement++;

            }

            if (nameCheckIncrement == names.length) {

                System.out.print("Invalid family member, try again            :");

            } else {

                nameCheckFailed = false;

            }

        }

//----Assigns user's choice of tooth layer to upperLowerResponse
        System.out.print("Which tooth layer (U)pper or (L)ower        :");
        upperLowerResponse = keyboard.nextLine().charAt(0);

//----Assigns upperLowerChoice based off of upperLowerResponse
        do {

            switch (upperLowerResponse) {
                case 'u':
                case 'U':
                    validResponse = true;
                    break;

                case 'l':
                case 'L':
                    upperLowerChoice = 1;
                    validResponse = true;
                    break;

//------------Makes user repeat previous step if upper or lower is not selected
                default:
                    validResponse = false;
                    System.out.print("Invalid layer, try again                    :");
                    upperLowerResponse = keyboard.nextLine().charAt(0);
                    break;

            }

        } while (!validResponse);

        System.out.print("Which tooth number                          :");

        do {

//--------Assigns selected tooth to toothSelection
            toothSelection = keyboard.nextInt();
            keyboard.nextLine();

            if (toothSelection > toothInfo[nameCheckIncrement][upperLowerChoice].length || toothSelection <= 0) {

//------------Provides an error if the tooth selected is zero, negative, or beyond the teeth entered for that family member in getFamilyMemberNames
                System.out.print("Invalid tooth number, try again             :");

            } else if (toothInfo[nameCheckIncrement][upperLowerChoice][toothSelection-1] == 'M') {

//------------Provides an error if the tooth selected is already missing
                System.out.print("Missing tooth, try again                    :");

            }

        } while (toothSelection <= 0 || toothSelection > toothInfo[nameCheckIncrement][upperLowerChoice].length || toothInfo[nameCheckIncrement][upperLowerChoice][toothSelection-1] == 'M');

//----Replaces current tooth type with missing
        toothInfo[nameCheckIncrement][upperLowerChoice][toothSelection-1] = 'M';

//----Returns modified array, now containing the missing tooth
        return toothInfo;

    }

    /**
     * Method Used to calculate and display the Root Index (Ix^2 + Bx - M).
     * @param toothInfo Used to calculate the number of incisors, bicuspids, and missing teeth.
     * @param names Used to identify the number of family members.
     */

    private static void getRootIndex(char[][][] toothInfo, String[] names) {

//----Defining and initializing variables
        double numberOfMs = 0;
        double numberOfIs = 0;
        double numberOfBs = 0;
        double positiveRootIndex;
        double negativeRootIndex;
        double quadAC;
        int familyMemberIncrement;
        int upperLowerIncrement;
        int toothIncrement;

//----Increments total numbers for each tooth into separate variables
        for (familyMemberIncrement = 0; familyMemberIncrement < names.length; familyMemberIncrement++) {

            for (upperLowerIncrement = 0; upperLowerIncrement < 2; upperLowerIncrement++) {

                for (toothIncrement = 0; toothIncrement < toothInfo[familyMemberIncrement][upperLowerIncrement].length; toothIncrement++) {

                    if (toothInfo[familyMemberIncrement][upperLowerIncrement][toothIncrement] == 'M'){

                        numberOfMs++;

                    } else if (toothInfo[familyMemberIncrement][upperLowerIncrement][toothIncrement] == 'I') {

                        numberOfIs++;

                    } else if (toothInfo[familyMemberIncrement][upperLowerIncrement][toothIncrement] == 'B') {

                        numberOfBs++;

                    }

                }

            }

        }

//----Multiplies numberOfIs by -1, as I has a negative value in the quadratic
        numberOfIs = -numberOfIs;

//----Finds discriminant of the quadratic
        quadAC = (numberOfBs * numberOfBs) - (4 * (numberOfIs * numberOfMs));

//----Calculates positive and negative root indices
        positiveRootIndex = -((-numberOfBs + Math.sqrt(quadAC)) / (2 * numberOfIs));
        negativeRootIndex = -((-numberOfBs - Math.sqrt(quadAC)) / (2 * numberOfIs));

//----Displays positive and negative root indices
        System.out.printf("One root canal at     %.2f\n", positiveRootIndex);
        System.out.printf("Another root canal at %.2f\n\n", negativeRootIndex);

    }

}