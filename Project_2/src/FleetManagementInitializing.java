import java.io.*;
import java.util.Scanner;

/**
 * Managing the Expenses of a Fleet of Boats
 * @author  Daniel Jonas
 * @version 22.0.2
 */

public class FleetManagementInitializing implements Serializable {

    //-------------------------------------------------------------------------------------------------

    /**
     * Global scanner object to use keyboard
     */

    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * Provides the maximum number of categories (year, name, price, etc.) that can be imported through CSV data
     */

    private static final int NUMBER_OF_CSV_BOAT_SPLITS = 6;

    /**
     * Provides the character used to separate the CSV data into separate variables via the split() function
     */

    private static final String CSV_SPLIT_CHAR = ",";

    //-------------------------------------------------------------------------------------------------

    /**
     * Declares and initializes variables needed to create arrays and begin receiving inputs from the user; also houses the menu through which the user can manipulate data.
     * @param args Command line argument used to enter the CSV file for the initial run.
     */

    public static void main(String[] args) {

        BoatConfig boats = new BoatConfig();

//----Declares and initializes variables
        char userResponse;
        boolean displayMenu = true;
        double upkeepExpenses;
        boolean nameCheckExpenses;
        String userResponseRemove;
        String userResponseExpenses;

//----Reads file from args, stores values in BoatArray in Class BoatConfig
        openFile(args, boats);

//----Displays welcome message
        System.out.println("Welcome to the Fleet Management System");
        System.out.println("--------------------------------------");

//----Menu for user to operate program
        do {

//--------Toggles instructions on/off in case of an input error (see line 129)
            if (displayMenu) {

                System.out.print("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");

            }

            displayMenu = true;

//--------User inputs next command as prompted, which is stored in userResponse
            userResponse = keyboard.nextLine().charAt(0);

            switch (userResponse) {

                case 'p':
                case 'P':

                    boats.display();

                    break;

                case 'a':
                case 'A':

                    addBoat(boats);

                    break;

                case 'r':
                case 'R':

//----------------Assigns user input to userResponseRemove
                    System.out.print("Which boat do you want to remove?           :");
                    userResponseRemove = keyboard.nextLine();

                    boats.removeBoat(userResponseRemove);

                    break;

                case 'e':
                case 'E':

//----------------Assigns user input to userResponseExpenses
                    System.out.print("Which boat do you want to spend on?         : ");
                    userResponseExpenses = keyboard.nextLine();

                    nameCheckExpenses = boats.nameCheck(userResponseExpenses);

//----------------Resets upkeepExpenses so that previously entered expenses aren't counted twice in the case of user error (!nameCheckExpenses)
                    upkeepExpenses = 0;

                    if (nameCheckExpenses) {

//--------------------Assigns user input to upkeepExpenses
                        System.out.print("How much do you want to spend?              : ");
                        upkeepExpenses = keyboard.nextDouble();
                        keyboard.nextLine();

                    }

//----------------Displays message returned by setUpkeepExpenses()
                        System.out.println(boats.setUpkeepExpenses(upkeepExpenses, nameCheckExpenses, userResponseExpenses));

                    break;

//------------Displays exit message and calls exitFileWrite to store serialized data for next run
                case 'x':
                case 'X':

                    System.out.println("Exiting the Fleet Management System");

                    exitFileWrite(boats);

                    break;

//------------In case of an input error, an error message is displayed and user is immediately asked for another input
                default:

                    System.out.print("Invalid menu option, try again              : ");
                    displayMenu = false;

                    break;

            }

        } while (userResponse != 'X' && userResponse != 'x');

    }

    /**
     * Opens the file storing the boat data.
     * @param args Contains either no data or the name of the CSV file; used by the program to determine whether to parse CSVs or deserialize.
     * @param boats BoatConfig object, houses the ArrayList central to the program's operation.
     */

    public static void openFile(String[] args, BoatConfig boats) {

//----Declares and initializes variables
        String fileName;
        FileInputStream fbStream;
        Scanner inFS;
        String csvLine;
        String[] csvParts;
        BoatConfig loadedBoats;

//----Reads from serialized file if command line arguments is blank
        if (args.length == 0) {

            ObjectInputStream fromStream = null;

            try {

//------------Deserialize from file
                fromStream = new ObjectInputStream(new FileInputStream("FleetData.db"));
                loadedBoats = (BoatConfig) fromStream.readObject();

//------------Explicitly copy the loaded BoatArray to the current object
                boats.boatArray = loadedBoats.boatArray;

            } catch (IOException | ClassNotFoundException e) {

                System.out.println("Error loading data: " + e.getMessage());

            } finally {

                try {

                    if (fromStream != null) {

//--------------------Closes the ObjectInputStream
                        fromStream.close();

                    }

                } catch (IOException e) {

                    System.out.println("Error closing file: " + e.getMessage());

                }

            }

//----Reading from CSV if a file is passed as an argument
        } else {

            fileName = args[0];

            try {

                fbStream = new FileInputStream(fileName);
                inFS = new Scanner(fbStream);

//------------Read each line in the input file
                while (inFS.hasNextLine()) {

                    csvLine = inFS.nextLine();

                    csvParts = csvLine.split(CSV_SPLIT_CHAR);

                    if (csvParts.length != NUMBER_OF_CSV_BOAT_SPLITS) {

                        System.out.println("Invalid line format: " + csvLine);
                        continue;

                    }

//----------------Add the boat from CSV data
                    parseBoatData(boats, csvParts);

                }

//------------Closes the FileInputStream
                fbStream.close();

            } catch (FileNotFoundException e) {

                System.out.println("File not found: " + args[0]);

            } catch (IOException e) {

                System.out.println("IO Exception");

            }

        }

    }

    /**
     * Prepares the new boat data entered by the user during operation so that it may be correctly added to the ArrayList via parseBoatData.
     * @param boats BoatConfig object, houses the ArrayList central to the program's operation.
     */

    public static void addBoat(BoatConfig boats) {

        String addBoatResponse;
        String[] csvParts;

//----Receives new boat data in CSV format and stores it in addBoatResponse
        System.out.print("Please enter the new boat CSV data          : ");
        addBoatResponse = keyboard.nextLine();

//----Splits addBoatResponse by commas, stored within parts
        csvParts = addBoatResponse.split(CSV_SPLIT_CHAR);

//----Calls parseBoatData to store data within ArrayList in boats
        parseBoatData(boats, csvParts);

    }

    /**
     * Receives CSV data from either addBoat or openFile; stores data in the ArrayList within boats.
     * @param boats BoatConfig object, houses the ArrayList central to the program's operation.
     * @param csvParts Array comprised of the CSV data after it has been split at each comma; each category (name, price, etc.) is stored in its own element.
     */

    public static void parseBoatData(BoatConfig boats, String[] csvParts) {

        String name;
        String model;
        int year;
        double purchasePrice;
        byte lengthOfBoat;
        double initialExpenses;
        String powerSailing;

//----Assigns variables from parts
        powerSailing = csvParts[0].trim(); // Power or Sailing
        name = csvParts[1].trim();
        year = Integer.parseInt(csvParts[2].trim());
        model = csvParts[3].trim();
        lengthOfBoat = Byte.parseByte(csvParts[4].trim());
        purchasePrice = Double.parseDouble(csvParts[5].trim());

//----Boats have no expenses when they are first entered
        initialExpenses = 0;

//----Stores parsed data within new element of ArrayList BoatArray
        boats.addBoat(new Boats(name, powerSailing, year, purchasePrice, model, lengthOfBoat, initialExpenses));

    }

    /**
     * Saves the data from the run within a serialized file named "FleetData.db".
     * @param boats BoatConfig object, houses the ArrayList central to the program's operation.
     */

    public static void exitFileWrite(BoatConfig boats){

        ObjectOutputStream toStream = null;

        try {

            toStream = new ObjectOutputStream(new FileOutputStream("FleetData.db"));
            toStream.writeObject(boats);

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        } catch (IOException e) {
            System.out.println("IO Exception in Method exitFileWrite");
        }

        try {

            if (toStream != null) {

                toStream.close();

            }

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}
