public class Song {
 
    public int rating;
    public double price;
    public boolean favorite;
    private String title;

    public Song() { 
        this.price = 0.0;
        this.favorite = false;
    }

    public Song(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Song(String title, double price, int rating) {
        this.title = title;
        this.price = price;
        this.rating = rating;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getPrice() { return this.price; }
    public void setPrice(double price) { this.price = price; }

    public void addToFavorites() { this.favorite = true; }

}