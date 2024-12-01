 public class Flourescent extends Light {
    public Flourescent(int lumens) {
        super(lumens);
    }

     @Override
     public double heatOutput() {
         return 0;
     }
     // Flourescents produce no heat

     @Override
     public boolean isFlourescent() {
         return true;
     }
     // Flourescents are flourescent



}