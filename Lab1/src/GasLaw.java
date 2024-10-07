import java.util.Scanner;
//=============================================================================
public class GasLaw {
    //-----------------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    //----The gas constant in Joules/mole/K
    private static final double GAS_CONSTANT = 8.3143;

    //-----------------------------------------------------------------------------
    public static void main(String[] args) {

//----Variables to hold system values
        double volume;
        double moles;
        double temp;
        double pressure;

//----User assigns values to variables volume, moles, and temp
        System.out.print("Enter volume, moles, temperature : ");
        volume = keyboard.nextDouble();
        moles = keyboard.nextDouble();
        temp = keyboard.nextDouble();

//----Calculations
        pressure = moles * GAS_CONSTANT * temp / volume;

//----Display Result
        System.out.println("Pressure is " + pressure);
    }
//----End of main method
}
//----End of class GasLaw
//-----------------------------------------------------------------------------
//=============================================================================