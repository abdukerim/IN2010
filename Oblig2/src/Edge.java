public class Edge {
    Actor start, end;
    Movie movie;

    public Edge(Actor start, Actor end, Movie movie) {
        this.start = start;
        this.end = end;
        this.movie = movie;
    }
}
