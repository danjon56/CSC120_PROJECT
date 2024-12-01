import java.io.Serializable;
import java.util.ArrayList;

    /**
    * Adds, removes, and updates boat data.
    * @author  Daniel Jonas
    * @version 22.0.2
    */

public class BoatConfig implements Serializable {

//----Defines and initializes variables
    ArrayList<Boats> boatArray;
    double totalExpenses = 0;
    double totalPrice = 0;

    public BoatConfig() {

        boatArray = new ArrayList<>();

    }

    /**
     * Adds Boats object to ArrayList boatArray.
     * @param boat Boats object containing data read from file or entered by user.
     */

    public void addBoat(Boats boat) {

//----Adds new boat to ArrayList boatArray
        boatArray.add(boat);

    }

    /**
     * Checks that the boat name entered by the user matches the name of a boat on file.
     * @param userResponse Input from user when prompted to name a boat listed in the program.
     */

    public boolean nameCheck (String userResponse) {

        for (int increment = 0; increment < boatArray.size(); increment++) {

            Boats boats = boatArray.get(increment);

            if (userResponse.equalsIgnoreCase(boats.getName())) {

//------------Program identifies boat within boatArray corresponding to the user's input
                return true;

            }

        }

//----Program does not identify boat within boatArray corresponding to the user's input
        return false;

    }

    /**
     * Checks that the user's expense is authorized for a specific boat, then updates the boat's expenses.
     * @param upkeepExpenses New expense to be authorized and stored within the program.
     * @param nameCheck Stores whether the name listed by the user is within boatArray.
     * @param userResponseExpenses Input from user when prompted to name a boat listed in the program.
     */

    public String setUpkeepExpenses(double upkeepExpenses, boolean nameCheck, String userResponseExpenses){

        if (nameCheck) {

            for (int increment = 0; increment < boatArray.size(); increment++) {

                Boats boats = boatArray.get(increment);

                if (((upkeepExpenses + boats.getUpkeepExpenses()) <= boats.getPurchasePrice()) && userResponseExpenses.equalsIgnoreCase(boats.getName())) {

//----------------Calls setExpenses() when the expense is authorized
                    return (boats.setExpenses(upkeepExpenses));

                } else if (((upkeepExpenses + boats.getUpkeepExpenses()) > boats.getPurchasePrice() && userResponseExpenses.equalsIgnoreCase(boats.getName()))) {

//----------------Returns error message if the expense is not authorized
                    return String.format("Expense not permitted, only $%.2f left to spend.", (boats.getPurchasePrice() - boats.getUpkeepExpenses()));

                }

            }

        } else {

//--------Returns error message if the boat is not listed in the file
            return ("Couldn't find boat " + userResponseExpenses);

        }

        return null;

    }

    /**
     * Checks that the user's expense is authorized for a specific boat, then updates the boat's expenses.
     * @param userResponseRemove Input from user when prompted to name which boat they would like to remove.
     */

    public void removeBoat(String userResponseRemove) {

        boolean removedBoat = false;
        int increment;

//----Iterates in reverse to prevent errors regarding the size of the ArrayList
        for (increment = (boatArray.size()-1); increment > -1; increment--) {

            Boats boats = boatArray.get(increment);

            if (userResponseRemove.equalsIgnoreCase(boats.getName())) {

//------------Removes undesired boat from boatArray
                boatArray.remove(increment);
                removedBoat = true;

            }

        }

//----Outputs error message if no boat is removed from boatArray
        if (!removedBoat) {

            System.out.println("Couldn't find boat " + userResponseRemove);

        }


    }

    /**
     * Prints the list of boats from boatArray, as well as their properties. Totals the expenses and prices of all boats.
     */

    public void display() {

        int increment;

        System.out.println("Fleet report:");

//----Resets upkeep expense and price totals every time display() is called
        totalExpenses = 0;
        totalPrice = 0;

        for (increment = 0; increment < boatArray.size(); increment++) {

            Boats boats = boatArray.get(increment);

//--------Calls toString() method
            System.out.println(boats);

//--------Totals upkeep expenses and prices from each boat
            totalExpenses += boats.getUpkeepExpenses();
            totalPrice += boats.getPurchasePrice();

        }

//----Outputs total upkeep expense and price
        System.out.printf("Total                                             : Paid $ %12.2f : Spent $ %10.2f \n", totalPrice, totalExpenses);

    }

}