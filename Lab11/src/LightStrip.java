import java.util.ArrayList;

public class LightStrip {
    private ArrayList<Light> lightsArray;
    int numFl;
    double totalLumens = 0;
    double totalHeat = 0;
    int increment;
    // Defines and initializes variables

    public LightStrip() {
        lightsArray = new ArrayList<>();
    }

    public void addLight(Light light) {
        lightsArray.add(light);
        // Adds new light to ArrayList lightsArray
    }

    public void removeLight(int index) {
        if (index >= 0 && index < lightsArray.size()) {
            lightsArray.remove(index);
            // If user input is within range, the light at that input in lightsArray is removed
        } else {
            System.out.println("ERROR: No light at that index");
            // If user input is not within range, an error is displayed
        }
    }

    public void display() {


            for (increment = 0; increment < lightsArray.size(); increment++) {
                Light light = lightsArray.get(increment);

                if (light.isFlourescent()) {
                    numFl++;
                }
                // Totals flourescents

                totalLumens += light.getLumens();
                // Totals lumens

                totalHeat += light.heatOutput();
                // Totals heat output

                System.out.print(increment + ": ");

                if (light.isFlourescent()) {
                    System.out.print("Flourescent");
                } else {
                    System.out.print("Incandescent");
                }


                System.out.println(" light of " + light.getLumens() + " lumens");
                // Displays bulb type and lumens for given increment

            }

        System.out.println("Total Lumens = " + totalLumens);
        System.out.println("Flourescent  = " + numFl);
        System.out.println("Heat output  = " + totalHeat);
        // Displays results from previous loop

        totalLumens = 0;
        totalHeat = 0;
        numFl = 0;
        // Resets for next input
    }
}
