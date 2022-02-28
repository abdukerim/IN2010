import java.util.ArrayList;

public class Actor implements Comparable<Actor>{
    String nm;
    String name;
    ArrayList<Edge> coActors;
    boolean visited;
    double distance;
    Actor parent;
    ArrayList<String> moviesActorPlayed;

    Actor(String n,String name) {
        nm = n;
        this.name = name;
        coActors = new ArrayList<>();
        visited = false;
        parent = null;
        moviesActorPlayed = new ArrayList<>();
        distance = 0;
    }

    void addEdge(Edge e) {
        coActors.add(e);
    }
    @Override
    public int compareTo(Actor b) {
        return Double.compare(this.distance, b.distance);
    }
}
