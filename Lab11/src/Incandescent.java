public class Incandescent extends Light {
    private static final double HEAT_OUTPUT_MULTIPLIER = 87.4;


    public Incandescent(int lumens) {
        super(lumens);

    }

    @Override
    public double heatOutput() {
        return getLumens() * HEAT_OUTPUT_MULTIPLIER;
    }
    // Returns heat output according to lab instructions


    @Override
    public boolean isFlourescent() {
        return false;
    }
    // Incandescents are not flourescent



}