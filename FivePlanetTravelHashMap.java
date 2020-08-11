import java.util.Map;
import java.util.HashMap;

/**
 * Activity 1.1.5
 */
public class FivePlanetTravelHashMap {

    public Map<String, Integer> planets;
    public static final int lightSpeed = 670616629 / 10;

    public FivePlanetTravelHashMap(Planet... planets) {
        this.planets = new HashMap<String, Integer>();
        for (Planet p : planets) {
            this.planets.put(p.name, p.dist);
        }
    }

    public FivePlanetTravelHashMap() {
        this.planets = new HashMap<String, Integer>();
    }

    public void addPlanet(Planet planet) {
        this.planets.put(planet.name, planet.dist);
    }

    public double time(Version version, boolean printing) {
        double total = 0.0;
        double time = 0.0;
        switch (version) {
            case WIDENING:
                for (Map.Entry<String, Integer> planet : this.planets.entrySet()) {
                    time = planet.getValue();
                    time /= lightSpeed;
                    if (printing)
                        System.out.println("Travel time to " + planet.getKey() + " is " + time + " hours.");
                    total += time;
                }
                return total;

            case CASTING:
                for (Map.Entry<String, Integer> planet : this.planets.entrySet()) {
                    time = (double)planet.getValue() / lightSpeed;
                    if (printing)
                        System.out.println("Travel time to " + planet.getKey() + " is " + time + " hours.");
                    total += time;
                }
                return total;

            default: 
                return Double.MIN_VALUE;
        }
    }

    public double time() {
        return this.time(Version.CASTING, true);
    }

    public double time(boolean printing) {
        return this.time(Version.CASTING, printing);
    }

    public int averageTime() {
        double average = this.time(false) / this.planets.size();
        // Round up or round down based on decimal value
        return (int)(average > 3.5 ? Math.ceil(average) : Math.floor(average)) | (int)(average + .5);
    }

    public static Planet planet(String name, int dist) {
        return new Planet(name, dist);
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
        Planet mercury = planet("mercury", 56974146);
        Planet venus = planet("venus", 25724767);
        Planet mars = planet("mars", 48678219);
        Planet jupiter = planet("jupiter", 390674710);
        Planet saturn = planet("saturn", 792248270);
    

        FivePlanetTravelHashMap travel = new FivePlanetTravelHashMap(mercury, venus, mars, jupiter, saturn);
        System.out.println(travel.toString());
    }

}

class Planet {
    String name;
    int dist;
    public Planet(String name, int dist) {
        this.name = name; this.dist = dist;
    }
}