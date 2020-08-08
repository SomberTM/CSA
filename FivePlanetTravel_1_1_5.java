import java.util.ArrayList;

/**
 * Activity 1.1.5
 */
public class FivePlanetTravel_1_1_5 {

    public ArrayList<Integer> planets;
    public static final int lightSpeed = 670616629 / 10;

    public FivePlanetTravel_1_1_5(int... planets) {
        this.planets = new ArrayList<Integer>();
        for (int i : planets) {
            this.planets.add(i);
        }
    }

    public FivePlanetTravel_1_1_5() {
        this.planets = new ArrayList<Integer>();
    }

    public void addPlanet(int dist) {
        this.planets.add(dist);
    }

    public double time(Version version) {
        double total = 0.0;
        double time = 0.0;
        switch (version) {
            case WIDENING:
                for (int d : this.planets) {
                    time = d;
                    time /= lightSpeed;
                    total += time;
                }
                return total;

            case CASTING:
                for (int d : this.planets) {
                    time = (double)d / lightSpeed;
                    total += time;
                }
                return total;

            default: 
                return 0;
        }
    }

    public double time() {
        return this.time(Version.CASTING);
    }

    public int averageTime() {
        double average = this.time() / this.planets.size();
        return (int)Math.floor(average);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Travel Time: " + this.time());
        sb.append("\nAverage Travel Time To Each Planet: " + this.averageTime());
        return sb.toString();
    }

    /**
     * Enum to decide how to calculate values for time
     */
    public static enum Version {
        WIDENING,
        CASTING
    }

    public static void main(String[] args)
    {
        // theplanets.org average distance from earth to the planets
        int mercury = 56974146;
        int venus = 25724767;
        int mars = 48678219;
        int jupiter = 390674710;
        int saturn = 792248270;
    
        FivePlanetTravel_1_1_5 travel = new FivePlanetTravel_1_1_5(mercury, venus, mars, jupiter, saturn);
        System.out.println(travel.toString());
    }

}