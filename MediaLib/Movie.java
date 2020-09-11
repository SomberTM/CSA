public class Movie {

    public int rating;
    public int duration;
    private String title;

    public Movie() { }

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

    public int getDuration() { return this.duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
}