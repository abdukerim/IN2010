import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Graph2 {
    static HashMap<String,Node> createGraph(String source,String dest) { //(String source,String dest)
        HashMap<String,Node> graph = new HashMap<>();
        add(graph,source,dest);
        return graph;
    }
    static void add(HashMap<String,Node> graph,String source,String dest){
        Node s = getNode(graph,source);
        Node d = getNode(graph,dest);
        s.adj.add(d);

    }
    static Node getNode(HashMap<String,Node> graph,String key) {
        if(graph.containsKey(key)) {
            return graph.get(key);
        }
        else {
            Node node = new Node(key);
            graph.put(key,node);
            return node;
        }
    }
    static boolean hasPath(HashMap<String,Node> graph,String s,String d) {
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(getNode(graph,s));
        while (!queue.isEmpty()) {
            Node parent = queue.remove();
            if(parent.key.equals(d)) {
                return true;
            }
            if(visited.contains(parent.key)) {
                continue;
            }
            visited.add(parent.key);
            for(Node children : parent.adj) {
                queue.add(children);
            }
        }
        return false;
    }
}
class Node{
    String key;
    LinkedList<Node> adj;
    Node(String key) {
        this.key = key;
        adj = new LinkedList<>();
    }
}
