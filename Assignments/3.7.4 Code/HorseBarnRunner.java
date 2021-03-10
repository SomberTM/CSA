import java.util.ArrayList;

/*
 * Activity 3.7.3
 */
public class HorseBarnRunner
{
    static double averageHorseWeight(ArrayList<Horse> horses) {
        double avg = horses.get(0).getWeight();
        for (int i = 1; i < horses.size(); i++)
          avg += horses.get(i).getWeight();
        return avg / horses.size();
    }

    static void displayLargeHorses(ArrayList<Horse> horses) {
        double averageWeight = averageHorseWeight(horses);
        for (Horse horse : horses) {
            if (horse.getWeight() > averageWeight)
                System.out.println(String.format("%s (%d) weights more than the average weight.", horse.getName(), horse.getWeight()));      
        }
    }

    static Horse heaviestHorse(ArrayList<Horse> horses) {
        Horse heaviest = horses.get(0);
        Horse current;
        for (int i = 1; i < horses.size(); i++) {
            current = horses.get(i);
            if (current.getWeight() > heaviest.getWeight())
              heaviest = current;
        }
        return heaviest;
    }

    static Horse lightestHorse(ArrayList<Horse> horses) {
        Horse lightest = horses.get(0);
        Horse current;
        for (int i = 1; i < horses.size(); i++) {
            current = horses.get(i);
            if (current.getWeight() < lightest.getWeight())
                lightest = current;
        }
        return lightest;
    }

    public static void main(String[] args)
    {
        HorseBarn barn = new HorseBarn();
        ArrayList<Horse> spaces = barn.getSpaces();
        
        System.out.println(spaces);

        System.out.println(averageHorseWeight(spaces));
        displayLargeHorses(spaces);
        System.out.println("Heaviest horse: " + heaviestHorse(spaces));
        System.out.println("Lightest horse: " + lightestHorse(spaces));
        
        Horse current;
        for (int i = 0; i < spaces.size(); i++) {
            current = spaces.get(i);
            if (current.getName().equals("Duke"))
                spaces.set(i, new Horse("Princess", 1445));     

            if (current.getName().equals("Silver"))
                spaces.add(i+1, new Horse("Chief", 1505));

            if (current.getName().equals("Buddy")) {
                spaces.add(i, new Horse("Gypsy", 1335));
                spaces.add(++i + 1, new Horse("Magic", 1425));
                i++;
            }
        }

        System.out.println(spaces);

    }
} 