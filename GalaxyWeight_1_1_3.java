public class GalaxyWeight {

    private Planets planet;
    private int visited = 0;
    private double weight;
    public final double earthGravity = 9.81;

    public GalaxyWeight(Planets planet) {
        this.planet = planet;
    }

    public GalaxyWeight(Planets planet, double weight) {
        this.planet = planet;
        this.weight = weight;
    }

    public void adventure(Planets planet) {
        this.planet = planet;
    }

    public int adventures() {
        return this.visited;
    }

    public void resize(double mass) {
        this.weight = mass;
    }

    public double weight(Planets planet, double earthMass) {
        this.planet = planet;
        return this.weight(earthMass);
    }

    public double weight() {
        return this.weight(this.weight);
    }

    public double weight(double earthMass) {
        double weight;
        switch(this.planet) {
            case MERCURY:
                weight = earthMass * (3.59 / earthGravity);
                System.out.println("Your earth weight on mercury is " + toFixed(weight, 2) + " lbs.");
                visited++;
                return weight;
            
            case VENUS:
                weight = earthMass * (8.87 / earthGravity);
                System.out.println("Your earth weight on venus is " + toFixed(weight, 2) + " lbs.");
                visited++;
                return weight;

            case MARS:
                weight = earthMass * (3.711 / earthGravity);
                System.out.println("Your earth weight on mars is " + toFixed(weight, 2) + " lbs.");
                visited++;
                return weight;

            case JUPITER:
                weight = earthMass * (24.79 / earthGravity);
                System.out.println("Your earth weight on jupiter is " + toFixed(weight, 2) + " lbs.");
                visited++;
                return weight;

            case SATURN:
                weight = earthMass * (11.08 / earthGravity);
                System.out.println("Your earth weight on saturn is " + toFixed(weight, 2) + " lbs.");
                visited++;
                return weight;

            case URANUS:
                weight = earthMass * (10.67 / earthGravity);
                System.out.println("Your earth weight on uranus is " + toFixed(weight, 2) + " lbs.");
                visited++;
                return weight;

            case NEPTUNE:
                weight = earthMass * (11.15 / earthGravity);
                System.out.println("Your earth weight on neptune is " + toFixed(weight, 2) + " lbs.");
                visited++;
                return weight;

            default:
                return 0;
        }
    }

    public static enum Planets {
        MERCURY,
        VENUS,
        MARS,
        JUPITER,
        SATURN,
        URANUS,
        NEPTUNE
    }

    public static void main(String[] args) {
        /* Create GalaxyWeight instance on Planet MERCURY with weight 150 */
        GalaxyWeight galaxy = new GalaxyWeight(Planets.MERCURY, 150);
        final double mercury = galaxy.weight();

        galaxy.adventure(Planets.VENUS);
        final double venus = galaxy.weight();

        galaxy.adventure(Planets.MARS);
        final double mars = galaxy.weight();

        System.out.println("Average weight = " + GalaxyWeight.toFixed(((mercury + venus + mars) / galaxy.adventures()), 2) + " lbs.");
    }

    /**
     * returns a value fixed to the given decimal places
     * i.e. 14.515151 fixed to 2 = 14.51
     * 
     * @param <T> Primitive number
     * @param value value to fix
     * @param fixed number of decimal places
     * @return
     */
    public static <T> String toFixed(T value, int fixed) {
        final char[] dpoints = value.toString().toCharArray();
        int checkFixed = 0;
        String tofixed = "";
        boolean foundDecimal = false;
        for (int i = 0; i < dpoints.length; i++) {
            try {    
                if (dpoints[i] == '.') { foundDecimal = true; tofixed += '.'; }
                if (dpoints[i] != '.' && foundDecimal && checkFixed < fixed) { 
                    tofixed += dpoints[i]; 
                    checkFixed++;
                } else if (dpoints[i] != '.' && !foundDecimal) {
                    tofixed += dpoints[i];
                }
            } catch (Exception e) {
                //Catch index out of bounds but dont throw, just annoying
            }
        }
        return tofixed;
    }

}