import java.util.*;

public class Graph {
    int V;  //numbers of vertices
    String data;
    Vertex[] vertexList;
    int[][] adjMatrix;
    int vertexCount;
    Graph(int V) {
        vertexList = new Vertex[V];
        adjMatrix = new int[V][V];
        vertexCount = 0;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = 0;
            }
        }

    }
    void addVertex(String s) {
        vertexList[vertexCount++] = new Vertex(s);
    }
    void addEdge(int start,int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }
    void displayVertex(int v) {
        System.out.println(vertexList[v].data);
    }
    void DFS(){
        Stack<Integer> stack = new Stack<>();
        vertexList[0].visited = true;
        displayVertex(0);
        stack.push(0);
        while (!stack.isEmpty()) {
            int v = getAdjUnvisitedVertex(stack.peek());
            if(v == -1) {
                stack.pop();
            }
            else {
                vertexList[v].visited = true;
                displayVertex(v);
                stack.push(v);
            }
        }
        for (int i = 0; i < vertexCount; i++) {
            vertexList[i].visited = false;
        }
    }
    int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < vertexCount; i++) {
            if(adjMatrix[v][i] == 1 && vertexList[i].visited== false) {
                return i;
            }
        }
        return -1;
    }
    void BFS() {
        vertexList[0].visited = true;
        displayVertex(0);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int n;
        while (!queue.isEmpty()) {
            int m = queue.poll();
            while ((n = getAdjUnvisitedVertex(m)) != -1) {
                vertexList[n].visited = true;
                displayVertex(n);
                queue.offer(n);
            }
        }
        for (int i = 0; i < vertexCount; i++) {
            vertexList[i].visited = false;
        }
    }
}
class Vertex{
    String data;
    boolean visited;
    Vertex(String d) {
        data = d;
        visited = false;
    }
}
