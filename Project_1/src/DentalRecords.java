import java.util.Scanner;

public class DentalRecords {

    /**
     * @author Daniel Jonas
     */

    //=============================================================================

    /**
     * @author Daniel Jonas
     */
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int MAX_NUMBER_OF_FAMILY_MEMBERS = 6;
    private static final int MAX_NUMBER_OF_TEETH = 8;
    //=============================================================================
    public static void main(String[] args) {

        int familyTotal;
        familyTotal = getNumberOfFamilyMembers();
        String[] names = new String[familyTotal];
        char[][][] toothInfo = new char[familyTotal][2][MAX_NUMBER_OF_TEETH]; // [Person Number 1,2,3...] [Top/Bottom] [Tooth 1,2,3...]
        char userResponse;
        boolean displayMenu = true;

        getFamilyMemberNames(toothInfo, names);

        do {

            if (displayMenu) {

                System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it          :");

            }

            displayMenu = true;
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

                case 'x':
                case 'X':
                    System.out.println("\nExiting the Floridian Tooth Records :-)");
                    break;

                default:
                    System.out.print("Invalid menu option, try again              :");
                    displayMenu = false;
                    break;

            }

        } while (userResponse != 'X');

    } // End of Main Method

    private static int getNumberOfFamilyMembers() {

        int numberOfFamilyMembers;

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");
        System.out.print("Please enter number of people in the family :");
        numberOfFamilyMembers = keyboard.nextInt();

        if (numberOfFamilyMembers < 0 || numberOfFamilyMembers > MAX_NUMBER_OF_FAMILY_MEMBERS) {

            do {

                System.out.print("Invalid number of people, try again         :");
                numberOfFamilyMembers = keyboard.nextInt();

            } while (numberOfFamilyMembers < 0 || numberOfFamilyMembers > MAX_NUMBER_OF_FAMILY_MEMBERS);

        }

        return numberOfFamilyMembers;

    } // End of Method getNumberOfFamilyMembers

    private static void getFamilyMemberNames(char[][][] toothInfo, String[] names) {

        int i;
        int upperLowerIncrement;
        int toothIncrement;

        keyboard.nextLine();

        for (i = 0; i < names.length; i++) {

            System.out.print("Please enter the name for family member " + (i+1) + "   :");
            names[i] = keyboard.nextLine();

            for (upperLowerIncrement = 0; upperLowerIncrement < 2; upperLowerIncrement++) { // upperLowerIncrement = 0 for uppers, upperLowerIncrement = 1 for lowers

                String position = (upperLowerIncrement == 0) ? "uppers" : "lowers";

                System.out.print("Please enter the " + position + " for " + names[i] + "       :");
                String teethInput = keyboard.nextLine().toUpperCase();

                teethInput = checkValidTeeth(teethInput);

                for (toothIncrement = 0; toothIncrement < MAX_NUMBER_OF_TEETH; toothIncrement++) {

                    if (toothIncrement < teethInput.length()) {

                        toothInfo[i][upperLowerIncrement][toothIncrement] = teethInput.charAt(toothIncrement);

                    } else {

                        toothInfo[i][upperLowerIncrement][toothIncrement] = ' '; // Fill with space if not enough input

                    }

                }

            }

        } // End of For Loop

    } // End of Method getFamilyMemberNames


    private static String checkValidTeeth (String teethInput) {

       boolean containsImproperInput = true;
        int teethCheckIncrement;

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

       return teethInput;

    } // End of Method checkValidTeeth

    private static void printTeeth (String[] names, char[][][] toothInfo) {

        int teethIncrement = 0;
        int nameIncrement;
        int upperLowerIncrement;

        System.out.print("\n");

        for (nameIncrement = 0; nameIncrement < names.length; nameIncrement++) {

            System.out.println(names[nameIncrement] + ":");

            for (upperLowerIncrement = 0; upperLowerIncrement < 2; upperLowerIncrement++) {

                String position = (upperLowerIncrement == 0) ? "Uppers" : "Lowers";
                System.out.print("    " + position + ": ");

                while ((teethIncrement < MAX_NUMBER_OF_TEETH && teethIncrement < toothInfo[nameIncrement][upperLowerIncrement].length && toothInfo[nameIncrement][upperLowerIncrement][teethIncrement] != ' ')) {

                    System.out.print("  " + (teethIncrement+1) + ": " + toothInfo[nameIncrement][upperLowerIncrement][teethIncrement]);
                    teethIncrement++;

                }

                teethIncrement = 0;
                System.out.print("\n");

            }

        }

        System.out.print("\n");

    } // End of Method printTeeth

    private static char[][][] extractTooth(char[][][] toothInfo, String[] names) {

        String selectName;
        int nameCheckIncrement = 0;
        boolean nameCheckFailed = true;
        int upperLowerChoice = 0;
        int toothSelection;
        char upperLowerResponse;
        boolean validResponse;

        System.out.print("Which family member                         :");

        while (nameCheckFailed) {

            nameCheckIncrement = 0;
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

        System.out.print("Which tooth layer (U)pper or (L)ower        :");
        upperLowerResponse = keyboard.nextLine().charAt(0);

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
                default:
                    validResponse = false;
                    System.out.print("Invalid layer, try again                    :");
                    upperLowerResponse = keyboard.nextLine().charAt(0);
                    break;

            }

        } while (!validResponse);

        System.out.print("Which tooth number                          :");

        do {

            toothSelection = keyboard.nextInt();
            keyboard.nextLine();

            if (toothSelection > toothInfo[nameCheckIncrement][upperLowerChoice].length || toothSelection <= 0) {

                System.out.print("Invalid tooth number, try again             :");

            } else if (toothInfo[nameCheckIncrement][upperLowerChoice][toothSelection-1] == 'M') {

                System.out.print("Missing tooth, try again                    :");

            }

        } while (toothSelection <= 0 || toothSelection > toothInfo[nameCheckIncrement][upperLowerChoice].length || toothInfo[nameCheckIncrement][upperLowerChoice][toothSelection-1] == 'M');

        toothInfo[nameCheckIncrement][upperLowerChoice][toothSelection-1] = 'M';

        return toothInfo;

    } // End of Method extractTooth

    private static void getRootIndex(char[][][] toothInfo, String[] names) {

        double numberOfMs = 0;
        double numberOfIs = 0;
        double numberOfBs = 0;
        double positiveRootIndex;
        double negativeRootIndex;
        double quadAC;
        int i;
        int j;
        int k;

        for (i = 0; i < names.length; i++) {

            for (j = 0; j < 2; j++) {

                for (k = 0; k < toothInfo[i][j].length; k++) {

                    if (toothInfo[i][j][k] == 'M'){

                        numberOfMs++;

                    } else if (toothInfo[i][j][k] == 'I') {

                        numberOfIs++;

                    } else if (toothInfo[i][j][k] == 'B') {

                        numberOfBs++;

                    }

                }

            }

        }

        numberOfIs = -numberOfIs;
        quadAC = (numberOfBs * numberOfBs) - (4 * (numberOfIs * numberOfMs));

        positiveRootIndex = -((-numberOfBs + Math.sqrt(quadAC)) / (2 * numberOfIs));
        negativeRootIndex = -((-numberOfBs - Math.sqrt(quadAC)) / (2 * numberOfIs));

        System.out.printf("One root canal at     %.2f\n", positiveRootIndex);
        System.out.printf("Another root canal at %.2f\n\n", negativeRootIndex);

    } // End of Method getRootIndex

} // End of Class DentalRecords
