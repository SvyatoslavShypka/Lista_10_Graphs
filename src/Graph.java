import java.util.List;
import java.util.NoSuchElementException;

public class Graph<T> {
    public Graph(List<Edge<T>> edges) {
        // TODO: Przekształcenie krawędzi na macierz sąsiedztwa, odwzorowanie wierzchołka na indeks, itp. 
    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wgłąb od podanego wierzchołka
        return "";
    }

    public String breadthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wszerz od podanego wierzchołka
        return "";
    }

    public int connectedComponents() {
        // TODO: Liczba składowych spójnych grafu
        return -1;
    }
}
