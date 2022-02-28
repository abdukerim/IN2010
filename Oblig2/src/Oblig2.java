import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Oblig2 {
    static HashMap<String, Actor> allActors = new HashMap<>();
    static HashMap<String, Movie> allMovies = new HashMap<>();
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\abduk\\Desktop\\IN2010\\Oblig2\\movies.tsv"));

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split("\t");
                String id = line[0];
                String title = line[1];
                double rate = Double.parseDouble(line[2]);
                int vote = Integer.parseInt(line[3]);
                Movie m = new Movie(id, title, rate, vote);
                allMovies.put(m.ttID, m);
            }
        } catch (FileNotFoundException e) {
            System.out.println("finner ikke filen");
        }
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\abduk\\Desktop\\IN2010\\Oblig2\\actors.tsv"));

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split("\t");
                String id = line[0];
                String name = line[1];
                Actor actor = new Actor(id, name);
                for (int i = 2; i < line.length; i++) {
                    actor.moviesActorPlayed.add(line[i]);
                }
                allActors.put(actor.nm, actor);
            }
        } catch (FileNotFoundException e) {
            System.out.println("finner ikke filen");
        }

        for (Actor a : allActors.values()) {
            for (String movieID : a.moviesActorPlayed) {
                if (allMovies.containsKey(movieID)) {
                    allMovies.get(movieID).addActor(a);
                }
            }
        }



        for (Movie m : allMovies.values()) {
            if (m.actorsOnTheMovie.size() > 1) {
                for (int i = 0; i < m.actorsOnTheMovie.size(); i++) {
                    for (int j = i + 1; j < m.actorsOnTheMovie.size(); j++) {
                        Actor a = m.actorsOnTheMovie.get(i);
                        Actor b = m.actorsOnTheMovie.get(j);
                        if (!a.nm.equals(b.nm)) {
                            a.coActors.add(new Edge(a, b, m));
                            b.coActors.add(new Edge(b, a, m));
                        }

                    }
                }
            }
        }


        HashMap<Actor,ArrayList<Edge>> graph = new HashMap<>();
        for (Movie m : allMovies.values()) {
            if (m.actorsOnTheMovie.size() > 1 ) {
                for (int i = 0; i < m.actorsOnTheMovie.size(); i++) {
                    for (int j = i + 1; j < m.actorsOnTheMovie.size(); j++) {
                        Actor a = m.actorsOnTheMovie.get(i);
                        Actor b = m.actorsOnTheMovie.get(j);
                        graph.put(a,new ArrayList<>());
                        graph.put(b,new ArrayList<>());
                        if(!a.nm.equals(b.nm)) {
                            graph.get(a).add(new Edge(a,b,m));
                            graph.get(b).add(new Edge(b,a,m));
                        }
                    }
                }
            }
        }
        System.out.println(graph.size());



        //oppgave 1
        System.out.println("====Oppgave 1=========");
        System.out.println("Antall noder: " + allActors.size());
        int count = 0;
        for (Movie m : allMovies.values()) {
            if (m.actorsOnTheMovie.size() > 1) {
                count += m.actorsOnTheMovie.size() * (m.actorsOnTheMovie.size() - 1) / 2;
            }
        }
        System.out.println("Antall kanter: " + count);

        //oppgave 2
        System.out.println();
        System.out.println("====Opgave 2========");
        //hvis jeg kjører alle samtidig for jeg
        String[] actors = {"nm2255973 nm0000460",
                            "nm0424060 nm0000243",
                            "nm4689420 nm0000365",
                            "nm0000288 nm0001401",
                            "nm0031483 nm0931324"};
        for (int i = 0; i < actors.length; i++) {
            String[] actorPair = actors[i].split(" ");
            Actor a = allActors.get(actorPair[0]);
            Actor b = allActors.get(actorPair[1]);
            bfsShortestPathBetween(a,b);
            System.out.println();
            System.out.println();
        }
        String[] actors1 = {"nm2255973 nm0000460",
                "nm0424060 nm0000243",
                "nm4689420 nm0000365",
                "nm0000288 nm0001401",
                "nm0031483 nm0931324"};
        System.out.println("Oppgave 3");
        for (int i = 0; i < actors.length; i++) {
            String[] actorPair = actors[i].split(" ");
            Actor a = allActors.get(actorPair[0]);
            Actor b = allActors.get(actorPair[1]);
            //oppgave 3
            System.out.println("==========");

            shortestPath(a, b);
        }


        //oppgave 4

        System.out.println("Oppgave 4");
        HashMap<Integer,Integer> compMap = new HashMap<>();
        for (ArrayList<Actor> component : numberOf(allActors)) {
            if(!compMap.containsKey(component.size())) {
                compMap.put(component.size(),1);
            }
            else {
                compMap.put(component.size(), compMap.get(component.size()) + 1);
            }
        }
        for (Map.Entry<Integer,Integer> e : compMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

    }


    static void bfsShortestPathBetween(Actor start,Actor end) {
        graphReset();
        Queue<Actor> queue = new LinkedList<>();
        start.parent = null;
        start.visited = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Actor currentActor = queue.poll();
            for(Edge e : currentActor.coActors) {
                Actor act = e.end;
                if(!act.visited) {
                    act.visited = true;
                    act.parent = currentActor;
                    queue.add(act);
                    if(act.nm.equals(end.nm)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }
        Actor actor = end;
        List<Actor> route = new ArrayList<>();
        while (actor != null) {
            route.add(actor);
            actor = actor.parent;
        }
        Collections.reverse(route);
        HashMap<Actor,Movie> path = new HashMap<>();
        System.out.println(route.get(0).name);
        for (int i = 0; i < route.size() - 1; i++) {
            Actor act = route.get(i);
            ArrayList<Edge> movies = new ArrayList<>();
            for (Edge e : act.coActors) {
                if(e.end.nm.equals(route.get(i+1).nm) && e != null) {
                    path.put(route.get(i+1), e.movie);
                }
            }
        }
        for(Map.Entry<Actor,Movie> e : path.entrySet()) {  //ikke altid i riktig rekke følge
            System.out.println("===[ " + e.getValue().title + " (" + e.getValue().rate
                    + ") ] ===> "+ e.getKey().name);
        }
    }
    static void shortestPath(Actor a,Actor b) {
        graphReset();
        for (Actor actor : allActors.values()) {
            actor.distance = Double.MAX_VALUE;
        }
        a.distance = 0;
        PriorityQueue<Actor> queue = new PriorityQueue<>(Actor::compareTo);
        queue.add(a);
        a.visited = true;
        while (!queue.isEmpty()) {
            Actor currentActor = queue.poll();
            for (Edge e : currentActor.coActors) {
                Actor coActor = e.end;
                if (!coActor.visited) {
                    double weight = 10 - e.movie.rate;
                    double newDistance = currentActor.distance + weight;
                    if (newDistance < coActor.distance) {
                        coActor.distance = newDistance;
                        coActor.parent = currentActor;
                        queue.add(coActor);
                    }
                }

            }
            currentActor.visited = true;
        }
        ArrayList<Actor> path = new ArrayList<>();
        Actor actor = b;
        while (actor != null) {
            path.add(actor);
            actor = actor.parent;
        }
        Collections.reverse(path);
        HashMap<Actor, Movie> result = new HashMap<>();
        for (int i = 0; i < path.size() - 1; i++) {
            Actor act = path.get(i);
            PriorityQueue<Movie> movies = new PriorityQueue<>(Movie::compareTo);
            for (Edge e : act.coActors) {
                if (e.end.nm.equals(path.get(i + 1).nm) && e != null) {
                    if (e.movie != null) {
                        movies.add(e.movie);
                    }
                    result.put(path.get(i + 1), movies.poll());
                }

            }
        }
        System.out.println(a.name);
        for (Map.Entry<Actor, Movie> e : result.entrySet()) { // ikke altid riktig rekke følge
            System.out.println("===[ " + e.getValue().title + " (" + e.getValue().rate
                    + ") ] ===> " + e.getKey().name);
        }
    }

    static void graphReset() {
        for (Actor a : allActors.values()) {
            a.visited = false;
            a.parent = null;
            a.distance = 0;
        }
    }
    static void dfsVisited(HashMap<String,Actor> allActors, Actor a, Stack<Actor> stack) {
        //stack = new Stack<>();
        a.visited = true;
        for (Edge e : a.coActors) {
            Actor coActor = e.end;
            if (!coActor.visited) {
                dfsVisited(allActors,coActor,stack);
            }

        }
        stack.push(a);
        // return stack;
    }
    static Stack<Actor> dfsTopSort(HashMap<String,Actor> allActors){
        Stack<Actor> stack = new Stack<>();
        for (Actor a : allActors.values()) {
            if(!a.visited) {
                dfsVisited(allActors,a,stack);
            }
        }
        return stack;
    }
    static ArrayList<Stack<Actor>> connectedComponents(HashMap<String,Actor> allActors) {
        graphReset();
        Stack<Actor> stack = dfsTopSort(allActors);
        HashSet<Actor> visited = new HashSet<>();
        ArrayList<Stack<Actor>> components = new ArrayList<>();
        Collections.reverse(stack);
        while (!stack.isEmpty()){
            Actor a = stack.pop();
            if(!visited.contains(a)) {
                Stack<Actor> component = new Stack<>();
                dfsVisited(allActors, a, component);
                components.add(component);
            }
        }
        return components;
    }
    static ArrayList<ArrayList<Actor>> numberOf(HashMap<String,Actor> allActors) {
        graphReset();
        ArrayList<ArrayList<Actor>> components = new ArrayList<>();
        for (Actor actor : allActors.values()) {
            ArrayList<Actor> path = new ArrayList<>();
            if (!actor.visited) {
                dfs(actor,path);
                components.add(path);
            }

        }
        return components;
    }
    static void dfs(Actor a,ArrayList<Actor> path) {

        a.visited = true;
        path.add(a);
        for (Edge e : a.coActors) {
            Actor act = e.end;
            if(!act.visited) {
                //while loop
                dfs(act,path);
            }
        }
    }
    static ArrayList<Actor> topologicalSort(Actor a) {
        ArrayList<Actor> result = new ArrayList<>();
        //while loop
        topologicalSortRecursive(a,result);
        return result;
    }
    static void topologicalSortRecursive(Actor start,ArrayList<Actor> stack) {
        start.visited = true;
        stack.add(start);
        for (Edge e : start.coActors) {
            Actor act = e.end;
            if (!act.visited && act != null) {
                //while loop
                topologicalSortRecursive(act,stack);
            }
        }

    }
    static ArrayList<ArrayList<Actor>> components(HashMap<String,Actor> allActors) {
        ArrayList<ArrayList<Actor>> finalResult = new ArrayList<>();
        for (int i = 0; i < allActors.size(); i++) {
            Actor act = allActors.get(i);
            ArrayList<Actor> component = topologicalSort(act);
            finalResult.add(component);
        }
        return finalResult;
    }
}