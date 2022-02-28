import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    static Stack<Node> stack;
    TopologicalSort() {
        stack = new Stack<>();
    }
    static class Node{
        String data;
        boolean visited;
        List<Node> neighbours;
        Node(String data) {
            this.data = data;
            neighbours = new ArrayList<>();
        }
        void addNeighbours(Node neighbour) {
            neighbours.add(neighbour);
        }

    }
    public void topologicalSort(Node start) {
        //Stack<Node> stack = new Stack<>();
        for (int i = 0; i < start.neighbours.size(); i++) {
            Node n = start.neighbours.get(i);
            if(n != null && !n.visited) {
                topologicalSort(n);
                n.visited = true;
            }
        }
        stack.push(start);
        //return stack;
    }

    public static void main(String[] args) {
        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");
        Node n5 = new Node("E");
        n1.neighbours.add(n2);
        n1.neighbours.add(n3);
        n2.neighbours.add(n1);
        n2.neighbours.add(n4);
        n3.neighbours.add(n1);
        n3.neighbours.add(n4);
        n4.neighbours.add(n2);
        n4.neighbours.add(n3);
        n4.neighbours.add(n5);
        n1.neighbours.add(n5);
        n4.neighbours.add(n3);
        n5.neighbours.add(n4);
        n5.neighbours.add(n1);
        TopologicalSort topological = new TopologicalSort();
        topological.topologicalSort(n5);
        Stack<Node> result = topological.stack;
        while (!result.empty()) {
            System.out.println(stack.pop().data);
        }

    }

}
