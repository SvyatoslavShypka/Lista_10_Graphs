import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) {
        var edges = new LinkedList<Edge<Integer>>();

        edges.add(new Edge<>(0, 2, 2));
        edges.add(new Edge<>(0, 4, 5));
        edges.add(new Edge<>(4, 2, 3));
        edges.add(new Edge<>(4, 1, 6));
        edges.add(new Edge<>(2, 1, 4));
        edges.add(new Edge<>(4, 3, 1));
        edges.add(new Edge<>(3, 1, 3));
        edges.add(new Edge<>(3, 5, 4));
        edges.add(new Edge<>(5, 0, 3));

        edges.add(new Edge<>(6, 7, 3));
        edges.add(new Edge<>(7, 8, 2));
        edges.add(new Edge<>(8, 6, 4));

        edges.add(new Edge<>(9, 10, 2));

        var graph = new Graph<>(edges);
        System.out.println(graph.depthFirst(4));
        System.out.println(graph.breadthFirst(4));

    }
}
