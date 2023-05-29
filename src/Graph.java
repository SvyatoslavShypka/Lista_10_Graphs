import java.util.*;

public class Graph<T> {

    private int size;
    private int[][] adjacencyMatrix;
    private static StringBuilder stringBuilder;
    private List<T> nodes = new LinkedList<>();

    public Graph(List<Edge<T>> edges) {
        // TODO: Przekształcenie krawędzi na macierz sąsiedztwa, odwzorowanie wierzchołka na indeks, itp.
        Set<T> vertexSet = new HashSet<>();
        for (Edge<T> edge : edges) {
            if (vertexSet.add(edge.getSource())) {
                nodes.add(edge.getSource());
            }
            if (vertexSet.add(edge.getDestination())) {
                nodes.add(edge.getDestination());
            }
        }
        size = vertexSet.size();
        adjacencyMatrix = new int[size][size];
        for (Edge<T> edge : edges) {
            int sourceIndex = nodes.indexOf(edge.getSource());
            int destinationIndex = nodes.indexOf(edge.getDestination());
            adjacencyMatrix[sourceIndex][destinationIndex] = edge.getWeight();
        }
/*
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(adjacencyMatrix[i][j] + "\t");
            }
            System.out.println();
        }
*/
    }

    public int getIndex(T value) {
        return nodes.indexOf(value);
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wgłąb od podanego wierzchołka
        boolean[] visited = new boolean[size];
        int startIndex = getIndex(startNode);
        if (startIndex < 0) throw new NoSuchElementException();
        stringBuilder = new StringBuilder();
        String result = dfs(startIndex, visited);
        return result.substring(0, result.length() - 2);
    }

    private String dfs(int vertexIndex, boolean[] visited) {
        visited[vertexIndex] = true;
        stringBuilder.append(nodes.get(vertexIndex) + ", ");

        for (int i = 0; i < size; i++) {
            if (adjacencyMatrix[vertexIndex][i] != 0 && !visited[i]) {
                dfs(i, visited);
            }
        }
        return stringBuilder.toString();
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wszerz od podanego wierzchołka
        boolean[] visited = new boolean[size];
        int startIndex = getIndex(startNode);
        if (startIndex < 0) throw new NoSuchElementException();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIndex);
        visited[startIndex] = true;
        stringBuilder = new StringBuilder();

        while (!queue.isEmpty()) {
            int vertexIndex = queue.poll();
            stringBuilder.append(nodes.get(vertexIndex) + ", ");

            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[vertexIndex][i] != 0 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        String result = stringBuilder.toString();
        return result.substring(0, result.length() - 2);
    }

    public int connectedComponents() {
        // TODO: Liczba składowych spójnych grafu
        DisjointSetForest disjointSet = new DisjointSetForest(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    try {
                        disjointSet.union(i, j);
                    } catch (ItemOutOfRangeException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return disjointSet.countSets();
    }
}
