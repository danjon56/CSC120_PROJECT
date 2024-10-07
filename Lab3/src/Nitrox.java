/* Design:
    1. Input Data
        1.1 Dive Depth
        1.2 Oxygen Percentage
    2. Compute PPO2
        2.1 Compute Pressure
            2.1.1 Ambient = ((Depth) / (Constant)) + 1
            2.1.2 O2 Pressure = ((Ambient) * (O2 Percentage)) / 100
        2.2 Display Pressures
    3. Display Warnings
        3.1 Compute OPG
            3.1.1 (ASCII Value for A) + (10 * (O2 Pressure))
        3.2 Display OPG Status
 */

import java.util.Scanner;
//=============================================================================
public class Nitrox {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int FEET_PER_ATMOSPHERE_CONSTANT = 33;
    //=============================================================================
    public static void main(String[] args) {

        // Defines Variables

        int diveDepth;
        char oxygenGroup;
        double oxygenPressure;
        int percentageOxygenInGas;
        double ambientPressure;
        boolean maximalPressureExceeded = false;
        boolean contingencyPressureExceeded = false;

        // Input Values

        System.out.print("Enter the Dive Depth and O2 Percentage: ");
        diveDepth = keyboard.nextInt();
        percentageOxygenInGas = keyboard.nextInt();

        // Calculates Ambient and Oxygen Pressures, Oxygen Group

        ambientPressure = ((double) diveDepth / FEET_PER_ATMOSPHERE_CONSTANT) + 1;
        oxygenPressure = ambientPressure * (double) percentageOxygenInGas / 100;
        oxygenGroup = (char) ( (int) (oxygenPressure * 10) + (int) 'A');

        // Determines Maximal and Contingency Pressure


            maximalPressureExceeded = (oxygenPressure >= 1.4);
            contingencyPressureExceeded = (oxygenPressure >= 1.6);



        // Displays Results

        System.out.println("Ambient Pressure: " + ambientPressure);
        System.out.println("O2 Pressure: " + oxygenPressure);
        System.out.println("O2 Group: " + oxygenGroup);
        System.out.println("Exceeds maximal O2 Pressure: " + maximalPressureExceeded);
        System.out.println("Exceeds contingency O2 Pressure: " + contingencyPressureExceeded);

    } // End of Main Method
} //End of Class Nitrox