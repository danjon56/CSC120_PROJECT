/* Design:
    1. Gather Data
        1.1 Side of Square (km)
        1.2 Road Length (km)
        1.3 Number of Kangaroos
    2. Perform Calculations
        2.1 Number of Kills
            2.1.1 (Number of Kangaroos) / (Square km)
            2.1.2 (Kangaroo Density) * (Road Surface Area) * (Kangaroo Constant)
        2.2 Number of Injuries
            2.2.1 Determine if Remainder is Present
    3. Display Results
        3.1 Number of Kills
        3.2 Number of Injuries

 */
import java.util.Scanner;
//=============================================================================
public class KillingRoos {
    //-----------------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    private static final double KANGAROO_CONSTANT = 1.47;
    // Road width is 10 meters, rest of program is in km
    private static final double ROAD_WIDTH = .01;
    //-----------------------------------------------------------------------------
    public static void main(String[] args) {

        // Declare Variables
        double kangarooCount;
        double sideOfSquare;
        double roadLength;
        double numOfKills;
        int trueKillCount = 0;
        double squareKm;
        double kangarooDensity;
        double roadSurfaceArea;

        // Program Gathers Data
        System.out.print("Enter side of square in km:  ");
        sideOfSquare = keyboard.nextDouble();
        System.out.print("Enter road length in km:     ");
        roadLength = keyboard.nextDouble();
        System.out.print("Enter number of Kangaroos:   ");
        kangarooCount = keyboard.nextDouble();

        // Kangaroo Density
        squareKm = sideOfSquare * sideOfSquare;
        kangarooDensity = kangarooCount / squareKm;

        // Road Surface Area
        roadSurfaceArea = roadLength * ROAD_WIDTH;

        // Number of Kangaroos Killed
        numOfKills = kangarooDensity * roadSurfaceArea * KANGAROO_CONSTANT;

        trueKillCount = (int) numOfKills;

        // Isolates Injured from Killed
        if (numOfKills % 1 != 0) {
            System.out.println("Number of kills: " + trueKillCount);
            System.out.println("Number of injuries: 1");
        }
        else {
            System.out.println("Number of kills: " + trueKillCount);
            System.out.println("Number of injuries: 0");
        }

    } // End of Main Method
}
// End of Class KillingRoos