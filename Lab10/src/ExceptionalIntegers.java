import java.util.ArrayList;

public class ExceptionalIntegers {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();

        int increment;
        Integer receptionInteger;

        for (increment =0; increment < args.length; increment++) {

            try {

                receptionInteger = converterMethod(args[increment]);
                // Passes Argument to Converter Method

                arrayList.add(receptionInteger);
                System.out.println("Converter method says integer OK - " + receptionInteger);
                // Lists out accepted integers and adds them to arrayList

            } catch (NumberFormatException e) {

                System.out.println("Catch block says the argument " + args[increment] + " is ignored because " + args[increment]);
                // Displays Error Message

            }
        }

        System.out.println("\nThe ArrayList contents are:");

        for (increment =0; increment < arrayList.size(); increment++) {

            System.out.println("Item " + increment + " is " + arrayList.get(increment));
            // Displays Contents of arrayList

        }

    }

    public static Integer converterMethod (String args) throws NumberFormatException {

        int responseInteger;

        responseInteger = Integer.parseInt(args);
        // Attempts to convert arguments to integer

        return responseInteger;
        // Returns Integer responseInteger

    }

}
