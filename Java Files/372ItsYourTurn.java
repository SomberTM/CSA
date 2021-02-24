import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Program {

    @SuppressWarnings("all")
    public static void main(String[] args) {

        ArrayList<String> animals = new ArrayList<String>() {{
            add("Dog");
            add("Cat");
            add("Rabbit");
        }};

        Scanner in = new Scanner(System.in);
        
        String actionQuery = "Would you like to (a)dd, (i)nsert, (r)emove, Re(p)lace or (q)uit";
        String animalQuery = "Enter an animal: ";
        String positionQuery = "Enter a position: ";

        String action;
        String animal;
        int position;

        Function<Integer, Boolean> isBadPosition = (pos) -> {
            return (pos <= 0 || pos > animals.size());
        };

        do {
            System.out.println(animals);
            System.out.println(actionQuery);
            
            action = in.next().toLowerCase();

            switch(action) {
                case "a":
                    System.out.println(animalQuery);
                    animal = in.next();
                    animals.add(animal);
                    break;

                case "i":
                    System.out.println(animalQuery);
                    animal = in.next();

                    System.out.println(positionQuery);
                    position = in.nextInt();

                    if (isBadPosition.apply(position)) {
                        System.out.println("Bad position!");
                        break;
                    }

                    animals.add(position - 1, animal);
                    break;

                case "r":
                    System.out.println(positionQuery);
                    position = in.nextInt();

                    if (isBadPosition.apply(position)) {
                        System.out.println("Bad position!");
                        break;
                    }

                    animals.remove(position - 1);
                    break;

                case "p":
                    System.out.println(animalQuery);
                    animal = in.next();

                    System.out.println(positionQuery);
                    position = in.nextInt();

                    if (isBadPosition.apply(position)) {
                        System.out.println("Bad position!");
                        break;
                    }

                    animals.set(position - 1, animal);
                    break;

                default:
                    System.out.println("Invalid Entry!");
                    break;
            }

        } while(!action.equals("q"));

    }

}
