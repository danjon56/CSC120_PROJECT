import java.io.Serializable;

    /**
    * Represents the properties of the boats entered via FleetManagementInitializing.
    * @author  Daniel Jonas
    * @version 22.0.2
    */


public class Boats implements Serializable {

    enum boatType {POWER, SAILING}
    private String name;
    boatType powerOrSailing;
    private int year;
    private double purchasePrice;
    private String model;
    private double upkeepExpenses;
    private byte lengthOfBoat;

    /**
     * Constructs a new Boat object with the specified attributes.
     * @param name The name of the boat.
     * @param powerOrSailingString A string that indicates if the boat is "power" or "sailing".
     * @param year The year the boat was manufactured.
     * @param purchasePrice The price of the boat.
     * @param model The model name of the boat.
     * @param lengthOfBoat The length of the boat in feet.
     * @param initialUpkeep The initial upkeep expenses for the boat.
     */

    public Boats(String name, String powerOrSailingString, int year, double purchasePrice, String model, byte lengthOfBoat, double initialUpkeep) {

        this.name = name;

        if (powerOrSailingString.equalsIgnoreCase("power")) {

            powerOrSailing = boatType.POWER;

        } else {

            powerOrSailing = boatType.SAILING;

        }

        this.year = year;
        this.purchasePrice = purchasePrice;
        this.model = model;
        this.lengthOfBoat = lengthOfBoat;
        this.upkeepExpenses = initialUpkeep;

    }

    /**
    * Updates the upkeep expenses of the boat, then outputs the total spent on the boat.
    * @param upkeepExpenses Amount of money to be added to the boat's total expenses.
    */

    public String setExpenses(double upkeepExpenses) {

        this.upkeepExpenses += upkeepExpenses;

        return String.format("Expense authorized, $%.2f spent.", this.upkeepExpenses);

    }

    /**
    * Returns boatType.
    */

    private boatType getPowerOrSailing() {

        return powerOrSailing;

    }

    /**
    * Returns boat model.
    */

    public String getModel(){

        return model;

    }

    /**
    * Returns boat name.
    */

    public String getName() {

        return name;

    }

    /**
    * Returns year of manufacture.
    */

    public int getYear() {

        return year;

    }

    /**
    * Returns boat purchase price.
    */

    public double getPurchasePrice() {

        return purchasePrice;

    }

    /**
    * Returns boat upkeep expenses.
    */

    public double getUpkeepExpenses() {

        return upkeepExpenses;

    }

    /**
    * Returns boat length, in feet.
    */

    public byte getLengthOfBoat() {

        return lengthOfBoat;

    }

    /**
    * Used to list the properties of each boat via display().
    */

    @Override
    public String toString() {

        return String.format("%-10s %-15s %-4d %-12s %-4s : Paid $ %12.2f : Spent $ %10.2f",
                getPowerOrSailing(), getName(), getYear(), getModel(),
                getLengthOfBoat() + "'", getPurchasePrice(), getUpkeepExpenses());

    }

}
