
//=============================================================================

import java.util.Scanner;

public class TaxTime {

    //=============================================================================
    private static final Scanner keyboard = new Scanner(System.in);
    private static final double S_GROUP_INCOME = 500000;
    private static final double Q_GROUP_INCOME = 200000;
    private static final double M_GROUP_INCOME = 100000;
    private static final double A_GROUP_INCOME = 50000;
    private static final double R_GROUP_INCOME = 20000;
    private static final double SQ_GROUP_RATE = 0.25;
    private static final double M_GROUP_RATE = 0.1;
    private static final double AR_GROUP_RATE = 0.03;
    private static final double P_GROUP_RATE = 0;
    //=============================================================================
    public static void main(String[] args) {

        //Defines variables for main

        double responseInput;
        double totalIncome = 0;
        double deductibleIncome = 0;
        double incomeToBeTaxed;
        char taxGroup;
        double totalTax;

        // Registers user's income and deductions

        do {
            System.out.print("Enter income or deduction (Enter 0 when finished): ");
            responseInput = keyboard.nextDouble();
            if (responseInput > 0) {
                totalIncome += responseInput;
            } else if (responseInput < 0) {
                deductibleIncome -= responseInput;
            }
        } while (responseInput != 0);

        // Calculates taxable income using responses

        incomeToBeTaxed = computeTaxableIncome(totalIncome, deductibleIncome);

        // Calculates tax group using taxGroup

        taxGroup = chooseTaxGroup(incomeToBeTaxed);

        // Calculates total tax using computeTax

        totalTax = computeTax(taxGroup, incomeToBeTaxed);

        // Displays all calculated information using displayTaxInformation

        displayTaxInformation(totalTax, totalIncome, deductibleIncome, taxGroup, incomeToBeTaxed);

    } // End of Main Method

    private static double computeTaxableIncome(double totalIncome, double deductibleIncome) {
        double taxableIncome;

        // Finds taxable income

        if (totalIncome >= deductibleIncome) {
            taxableIncome = totalIncome - deductibleIncome;
        } else {
            taxableIncome = 0;
        }

        // Returns value to main

        return taxableIncome;
    } // End of Method computeTaxableIncome

    private static char chooseTaxGroup(double taxableIncome) {
        char taxGroup;

        // Identifies tax group

        if (taxableIncome >= S_GROUP_INCOME) {
            taxGroup = 'S';
        } else if (taxableIncome >= Q_GROUP_INCOME) {
            taxGroup = 'Q';
        } else if (taxableIncome >= M_GROUP_INCOME) {
            taxGroup = 'M';
        } else if (taxableIncome >= A_GROUP_INCOME) {
            taxGroup = 'A';
        } else if (taxableIncome >= R_GROUP_INCOME) {
            taxGroup = 'R';
        } else {
            taxGroup = 'P';
        }

        // Returns to main

        return taxGroup;
    } // End of Method taxGroup

    private static double computeTax(char taxGroup, double incomeToBeTaxed) {


        // Finds taxation rate using tax group

        switch (taxGroup) {
            case 'S':
            case 'Q':
                return (incomeToBeTaxed * SQ_GROUP_RATE);
            case ('M'):
                return (incomeToBeTaxed * M_GROUP_RATE);
            case ('A'):
            case ('R'):
                return (incomeToBeTaxed * AR_GROUP_RATE);
            case ('P'):
                return (incomeToBeTaxed * P_GROUP_RATE);
            default:
                System.out.println("ERROR: Please Try Again.");
                return 0.0;
        }


    } // End of Method computeTax

    private static void displayTaxInformation(double totalTax, double totalIncome, double deductibleIncome, char taxGroup, double incomeToBeTaxed) {

        // Displays values

        System.out.println("Income: $" + totalIncome);
        System.out.println("Deductions: $" + deductibleIncome);
        System.out.println("Taxable Income: " + incomeToBeTaxed);
        System.out.println("Tax Group: " + taxGroup);
        System.out.println("Total Tax: $" + totalTax);
    } // End of Method displayTaxInformation
 } // End of Class TaxTime