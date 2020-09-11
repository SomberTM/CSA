import java.util.Random;

public class MediaLib {

    static Random r = new Random();

    public static void main(String[] args) {
        double totalCost = 0.0;
        int numSongs = 0;
        int totalRatings = 0;
        for (int i = 0; i < 10; i++) {
            Song song = new Song(randomName(), toFixed(((double)i)+r.nextDouble(), 2), r.nextInt(10));
            System.out.println(song.getTitle());
            System.out.println(song.getPrice());
            System.out.println(song.getRating());
            totalCost += song.getPrice();
            totalRatings += song.getRating();
            numSongs++;
        }

        System.out.println(numSongs + " @ " + totalRatings + " : " + toFixed(totalCost, 2) + " | avg: " + toFixed(totalCost / numSongs, 2));
        
        /*
        Song song = new Song();
        song.setTitle("Johnny B. Goode"); 
        song.setRating(5); 
        System.out.println(song.getPrice());
        System.out.println(song.getTitle());
        System.out.println(song.getRating());
        */

        /*
        Book book = new Book();
        book.setTitle("A book"); 
        book.setRating(2); 
        System.out.println(book.getTitle());
        System.out.println(book.getRating());

        Movie movie = new Movie();
        movie.setTitle("A movie"); 
        movie.setRating(10); 
        System.out.println(movie.getTitle());
        System.out.println(movie.getRating());
        */
    }

    public static String randomName() {
        String name = "";
        for (int i = 0; i < r.nextInt(100); i++) {
            name += Character.toString('a' + r.nextInt(26));
        }
        return name;
    }

    public static double toFixed(double value, int fixed) {
        final char[] dpoints = Double.valueOf(value).toString().toCharArray();
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
        return Double.valueOf(tofixed);
    }

}