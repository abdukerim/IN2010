import java.util.ArrayList;

public class Movie implements Comparable<Movie>{
    String ttID;
    String title;
    double rate;
    int vote;
    ArrayList<Actor> actorsOnTheMovie;

    Movie(String ttID,String title,double rate,int vote) {
        this.ttID = ttID;
        this.title = title;
        this.rate = rate;
        this.vote = vote;
        actorsOnTheMovie = new ArrayList<>();
    }
    void addActor(Actor a) {
        actorsOnTheMovie.add(a);
    }
    @Override
    public int compareTo(Movie b) {
        return Double.compare(10 - this.rate, 10 - b.rate);
    }

}