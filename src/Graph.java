import java.util.*;

public class Graph<T> {

    private int size;
    private T[] vertices;
    private int[][] adjacencyMatrix;
    private static StringBuilder stringBuilder;
    private MyNumberInStringComparator myNumberInStringComparator = new MyNumberInStringComparator();

    public Graph(List<Edge<T>> edges) {
        // TODO: Przekształcenie krawędzi na macierz sąsiedztwa, odwzorowanie wierzchołka na indeks, itp.
        Set<T> vertexSet = new HashSet<>();
        for (Edge<T> edge : edges) {
            vertexSet.add(edge.getSource());
            vertexSet.add(edge.getDestination());
        }

        size = vertexSet.size();
        vertices = (T[]) vertexSet.toArray();
        Arrays.sort(vertices, myNumberInStringComparator);

        adjacencyMatrix = new int[size][size];
        for (Edge<T> edge : edges) {
            int sourceIndex = Arrays.binarySearch(vertices, edge.getSource(), myNumberInStringComparator);
            int destinationIndex = Arrays.binarySearch(vertices, edge.getDestination(), myNumberInStringComparator);
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

    public String depthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wgłąb od podanego wierzchołka
        boolean[] visited = new boolean[size];
        int startIndex = Arrays.binarySearch(vertices, startNode, myNumberInStringComparator);
        if (startIndex < 0) throw new NoSuchElementException();
        stringBuilder = new StringBuilder();
        String result = dfs(startIndex, visited);
        return result.substring(0, result.length() - 2);
    }

    private String dfs(int vertexIndex, boolean[] visited) {
        visited[vertexIndex] = true;
        stringBuilder.append(vertices[vertexIndex] + ", ");

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
        int startIndex = Arrays.binarySearch(vertices, startNode, myNumberInStringComparator);
        if (startIndex < 0) throw new NoSuchElementException();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIndex);
        visited[startIndex] = true;
        stringBuilder = new StringBuilder();

        while (!queue.isEmpty()) {
            int vertexIndex = queue.poll();
            stringBuilder.append(vertices[vertexIndex] + ", ");

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
//                if (adjacencyMatrix[i][j] != 0) {
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

    public class MyNumberInStringComparator implements Comparator<T> {

        private static List<String> numbers = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof Integer) {
                return ((Integer) o1).compareTo((Integer) o2);
            }
            return Integer.compare(numbers.indexOf((String) o1),numbers.indexOf((String) o2));
        }
    }
}
