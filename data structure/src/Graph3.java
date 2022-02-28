import java.util.*;

public class Graph3 {
    HashMap<Node2, LinkedList<Node2>> adj;
    Graph3() {
        adj = new HashMap<>();
    }
    boolean hasEdge(Node2 source,Node2 dest) {
        return adj.containsKey(source) && adj.containsKey(dest) && adj.get(source) != null && adj.get(source).contains(dest);

    }
    void insert(Node2 source,Node2 dest) {
        if(!adj.keySet().contains(source)) {
            adj.put(source,null);
        }
        if(!adj.keySet().contains(dest)) {
            adj.put(dest,null);
        }
        LinkedList<Node2> tmp = adj.get(source);
        if(tmp == null) {
            tmp = new LinkedList<>();
        }
        tmp.add(dest);
        adj.put(source,tmp);
    }
    void traverse() {
        for(Node2 n : adj.keySet()) {
            System.out.println("Traversing: " + n.key);
            LinkedList<Node2> vertices = adj.get(n);
            if(vertices != null) {
                for(Node2 m : adj.get(n)) {
                    System.out.println(m.key);
                }
            }
            System.out.println();
        }
    }
    void BFS(Node2 n) {
        HashSet<Node2> visitedNodes = new HashSet<>();
        Queue<Node2> queue = new LinkedList<>();
        visitedNodes.add(n);
        queue.add(n);
        while (!queue.isEmpty()) {
            System.out.println(queue.peek().key);
            Node2 current = queue.poll();
            if(current != null && adj.get(current) != null) {
                for (int i = 0; i < adj.get(current).size(); i++) {
                    Node2 node = adj.get(current).get(i);
                    if (!visitedNodes.contains(node)) {
                        visitedNodes.add(node);
                        queue.add(node);
                    }
                }
            }

        }

    }
    void dfsVisit(Node2 u,HashSet<Node2> visited,Stack<Node2> stack) {
        visited.add(u);
        List<Node2> E = adj.get(u);
        if(E != null) {
            for (Node2 v : E) {
                if(!visited.contains(v)) {
                    dfsVisit(v,visited,stack);
                }
            }
        }
        stack.push(u);
    }
    Stack<Node2> dfsTopSort() {
        HashSet<Node2> visited = new HashSet<>();
        Stack<Node2> stack = new Stack<>();
        for(Node2 n : adj.keySet()) {
            if(!visited.contains(n)) {
                dfsVisit(n,visited,stack);
            }
        }
        return stack;
    }
    void DFS(Node2 n) {
        List<Node2 > visitedNodes = new ArrayList<>();
        Stack<Node2> stack = new Stack<>();
        stack.push(n);
        visitedNodes.add(n);
        while (!stack.isEmpty()) {
            List<Node2> edges = adj.get(stack.peek());
            if(edges != null) {
                Node2 node2 = edges.stream().filter(edge ->
                        !visitedNodes.contains(edge)).findFirst().orElse(null);
                if(node2 != null) {
                    stack.push(node2);
                    if(!visitedNodes.contains(node2)) {
                        visitedNodes.add(node2);
                    }
                }
                else {
                    stack.pop();
                }
            }
            else {
                stack.pop();
            }
        }
        visitedNodes.stream().forEach(node -> System.out.print(node.key + "->"));
        System.out.println();
    }
    void DFSFull(){
        List<Node2 > visitedNodes = new ArrayList<>();
        for (Node2 n : adj.keySet()) {
            if(!visitedNodes.contains(n)) {
                DFS(n);
            }
        }
    }

    HashMap<Node2,Integer> outDeg(Graph3 g) {
        HashMap<Node2,Integer> outDegs = new HashMap<>();
        for(Node2 n : g.adj.keySet()) {
            if(g.adj.get(n) == null) {
                outDegs.put(n,0);
            }else {
                outDegs.put(n,g.adj.get(n).size());
            }
        }
        return outDegs;
    }
    boolean hasCycle() {
        return false;
    }
    HashMap<Node2,Integer> inDeg() {
        HashMap<Node2, Integer> inDegs = new HashMap<>();
        for (Node2 n : adj.keySet()) {
            inDegs.put(n,0);
        }
        for (Node2 n : adj.keySet()) {
            if(adj.get(n) != null) {
                for (int i = 0; i < adj.get(n).size(); i++) {
                    if (inDegs.containsKey(adj.get(n).get(i))) {
                        inDegs.put(adj.get(n).get(i), inDegs.get(adj.get(n).get(i)) + 1);
                    }
                }
            }
        }

        return inDegs;
    }
    void stronglyConnectedComponents(){
        Stack<Node2> stack = dfsTopSort();
        Stack<Node2> stack1 = new Stack<>();
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        HashSet<Node2> visited = new HashSet<>();
        ArrayList<Stack<Node2>> components = new ArrayList<>();
        for (Node2 node : stack1) {
            if(!visited.contains(node)) {
                Stack<Node2> component = new Stack<>();
                dfsVisit(node,visited,component);
                components.add(component);
            }
        }
        for (Stack<Node2> s : components) {
            for (Node2 n : s) {
                System.out.print(n.key + " ");
            }
            System.out.println();
        }
    }
    void topologicalSort() {
        ArrayList<Node2> output = new ArrayList<>();
        Stack<Node2> S = new Stack<>();
        int n = adj.size();
        for (Node2 node2 : inDeg().keySet()) {
            if(inDeg().get(node2) == 0) {
                S.push(node2);
            }
        }
        int i = 1;
        while (!S.isEmpty()) {
            Node2 v = S.pop();
            output.add(v);
            i++;
            if(adj.get(v) != null) {
                for (int j = 0; j < adj.get(v).size(); j++) {
                    Node2 current = adj.get(v).get(j);
                    inDeg().put(current,inDeg().get(current) - 1);
                    if(inDeg().get(current) == 0) {
                        S.push(current);
                    }
                }
            }
        }
        if(i > n) {
            for (int j = output.size() - 1; j > 0; j--) {
                System.out.print(output.get(j).key + " ");
            }
        }
        /*
        else {
            System.out.println("Graph has cycle!");
        }

         */
    }

    public static void main(String[] args) {
        Graph3 g = new Graph3();
        Node2 newYork = new Node2("JFK");
        Node2 boston = new Node2("BOS");
        Node2 sanFransisco = new Node2("SFO");
        Node2 miami = new Node2("MIA");
        Node2 dallas = new Node2("DFW");
        Node2 losAngeles = new Node2("LAX");
        Node2 orlando = new Node2("ORD");
        g.insert(losAngeles,orlando);
        g.insert(dallas,sanFransisco);
        g.insert(dallas,orlando);
        //g.insert(orlando,dallas);
        g.insert(miami,losAngeles);
        g.insert(miami,dallas);
        g.insert(newYork,miami);
        g.insert(newYork,dallas);
        g.insert(newYork,sanFransisco);
        g.insert(newYork,boston);
        //g.insert(orlando,losAngeles);
        //g.insert(boston,newYork);
        //g.DFS(newYork);
        //g.DFSFull();
        g.BFS(newYork);

        HashMap<Node2,Integer> outDeg = g.outDeg(g);
        for (Map.Entry<Node2,Integer> e : outDeg.entrySet()) {
            System.out.println(e.getKey().key + " : " + e.getValue());
        }
        g.stronglyConnectedComponents();
        Graph3 gReverse = new Graph3();
        gReverse.insert(orlando,losAngeles);
        gReverse.insert(sanFransisco,dallas);
        gReverse.insert(orlando,dallas);
        gReverse.insert(dallas,orlando);
        gReverse.insert(losAngeles,orlando);
        gReverse.insert(losAngeles,miami);
        gReverse.insert(dallas,miami);
        gReverse.insert(miami,newYork);
        gReverse.insert(dallas,newYork);
        gReverse.insert(sanFransisco,newYork);
        gReverse.insert(boston,newYork);
        /*
        Stack<Node2> stack = g.dfsTopSort();
        Stack<Node2> stack1 = new Stack<>();
        while (!stack.isEmpty()) {
            stack1.push(stack.pop());
        }
        HashSet<Node2> visited = new HashSet<>();
        ArrayList<Stack<Node2>> components = new ArrayList<>();
        for (Node2 node : stack1) {
            if(!visited.contains(node)) {
                Stack<Node2> component = new Stack<>();
                gReverse.dfsVisit(node,visited,component);
                components.add(component);
            }
        }
        for (Stack<Node2> s : components) {
            for (Node2 n : s) {
                System.out.print(n.key + " ");
            }
            System.out.println();
        }

         */
        g.topologicalSort();
        System.out.println(g.inDeg().get(miami));
    }
}
class Node2{
    String key;
    Node2(String k) {
        key = k;
    }
}
