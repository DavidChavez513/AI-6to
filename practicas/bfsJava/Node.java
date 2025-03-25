import java.util.HashMap;
import java.util.Map;

public class Node {
    String cityName;
    Node parent;
    boolean visited;
    Map<Node, Integer> distances;
    Map<String, Node> neighbors;

    public Node(String name) {
        this.cityName = name;
        this.neighbors = new HashMap<>();
        this.distances = new HashMap<>();

        this.distances.put(this, 0);

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setParent(Node _parent) {
        this.parent = _parent;
    }

    public Node getParent() {
        return this.parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addNeighbors(Node neighbor, int dist) {
        this.neighbors.put(neighbor.cityName, neighbor);
        this.distances.put(neighbor, dist);
    }

    public Node getNeighbor(String city) {
        return this.neighbors.get(city);
    }

}