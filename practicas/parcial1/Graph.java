import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Graph {
    Map<String, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public Node addNode(String city) {
        Node newCity = new Node(city);
        this.nodes.put(city, newCity);
        return newCity;
    }

    public void addEdge(Node city1, Node city2, int distance) {
        if (city1 != null && city2 != null) {
            city1.addNeighbors(city2, distance);
        }
    }

    public Node getNode(String city) {
        return city != null ? this.nodes.get(city) : null;
    }

    public static Graph makeGraph(String[] cities) {
        Graph newGraph = new Graph();
        for (String string : cities) {
            // Two spaces for make a visual divisor
            newGraph.addNode(string);
        }
        return newGraph;
    }

    // hardcoded
    public static Graph controlCase1() {
        String[] cities = {
                "Oradea", "Zerind", "Arad", "Timisoara", "Lugoj", "Mehadia",
                "Drobeta", "Craiova", "Rimnicu Vilcea", "Sibiu", "Fagaras",
                "Pitesti", "Bucharest", "Giurgiu", "Neamt", "Iasi", "Vaslui",
                "Urziceni", "Hirsova", "Eforie"
        };

        Graph newGraph = new Graph();
        for (String string : cities) {
            // Two spaces for make a visual divisor
            newGraph.addNode(string);
        }

        newGraph.controlCase1Connections(newGraph);

        return newGraph;
    }

    public void makeConnections() {
        if (nodes.size() <= 1)
            return;

        Random rand = new Random();

        int maxConnections = nodes.size() - 1;
        int maxDistance = 45;

        String[] cityNames = nodes.keySet().toArray(new String[0]);

        for (Node node : nodes.values()) {
            int connections = rand.nextInt(maxConnections) + 1; // Mínimo una conexión
            while (node.neighbors.size() < connections) {
                Node neighbor = nodes.get(cityNames[rand.nextInt(nodes.size())]);

                if (!neighbor.cityName.equals(node.cityName) && !node.neighbors.containsKey(neighbor.cityName)) {
                    int distance = rand.nextInt(maxDistance) + 1; // Distancia mínima de 1
                    node.addNeighbors(neighbor, distance);
                    neighbor.addNeighbors(node, distance); // Conexión bidireccional
                }
            }
        }
    }

    // búsqueda en profundidad
    public Stack<Node> DFS(String init, String target) {
        Node actualNode = this.nodes.get(init);
        Stack<Node> path = new Stack<>(); // Lista abierta
        Stack<Node> visited = new Stack<>(); // Lista Cerrada

        path.push(actualNode);

        while (!path.empty()) {
            actualNode = path.pop();
            visited.push(actualNode);

            if (!actualNode.cityName.equals(target)) {
                for (Node node : actualNode.neighbors.values()) {
                    if (node.isVisited())
                        continue;

                    node.setParent(actualNode);
                    path.push(node);
                }

                actualNode.setVisited(true);
                continue;
            }
            return formatPath(actualNode);
        }

        return new Stack<>();
    }

    // Búsqueda en amplitud
    public Stack<Node> BFS(String init, String target) {

        Node actualNode = this.nodes.get(init);
        Queue<Node> pending = new LinkedList<>();

        pending.add(actualNode);

        while (!pending.isEmpty()) {
            actualNode = pending.remove();
            actualNode.setVisited(true);

            if (actualNode.cityName.equals(target)) {
                return formatPath(actualNode);
            }

            for (Node node : actualNode.neighbors.values()) {
                if (node.isVisited())
                    continue;
                node.setVisited(true);
                pending.add(node);
                node.setParent(actualNode);
            }
        }

        return new Stack<>();
    }

    public Stack<Node> dijkstra(String init, String target) {

        Node current = this.nodes.get(init);
        Queue<Node> pending = new LinkedList<>();
        Map<Node, Integer> fullMapDistances = new HashMap<>();

        // Node aux = null;
        pending.add(current);
        fullMapDistances.put(current, 0);

        while (!current.cityName.equals(target)) {
            current = pending.remove();
            current.setVisited(true);
            
            Node cityClosed = null;
            for (Node neighbor : current.neighbors.values()) {
                pending.add(neighbor);
            }
        }

        System.out.println("Control check");

        return formatPath(current);
    }

    private Stack<Node> formatPath(Node target) {
        Stack<Node> path = new Stack<>();
        while (target != null) {
            path.push(target);
            target = target.parent;
        }
        return path;
    }

    // hardcoded
    private void controlCase1Connections(Graph newGraph) {
        newGraph.addEdge(newGraph.getNode("Oradea"), newGraph.getNode("Zerind"), 71);
        newGraph.addEdge(newGraph.getNode("Zerind"), newGraph.getNode("Oradea"), 71);

        newGraph.addEdge(newGraph.getNode("Zerind"), newGraph.getNode("Arad"), 75);
        newGraph.addEdge(newGraph.getNode("Arad"), newGraph.getNode("Zerind"), 75);

        newGraph.addEdge(newGraph.getNode("Oradea"), newGraph.getNode("Sibiu"), 151);
        newGraph.addEdge(newGraph.getNode("Sibiu"), newGraph.getNode("Oradea"), 151);

        newGraph.addEdge(newGraph.getNode("Arad"), newGraph.getNode("Sibiu"), 140);
        newGraph.addEdge(newGraph.getNode("Sibiu"), newGraph.getNode("Arad"), 140);

        newGraph.addEdge(newGraph.getNode("Arad"), newGraph.getNode("Timisoara"), 118);
        newGraph.addEdge(newGraph.getNode("Timisoara"), newGraph.getNode("Arad"), 118);

        newGraph.addEdge(newGraph.getNode("Timisoara"), newGraph.getNode("Lugoj"), 111);
        newGraph.addEdge(newGraph.getNode("Lugoj"), newGraph.getNode("Timisoara"), 111);

        newGraph.addEdge(newGraph.getNode("Lugoj"), newGraph.getNode("Mehadia"), 70);
        newGraph.addEdge(newGraph.getNode("Mehadia"), newGraph.getNode("Lugoj"), 70);

        newGraph.addEdge(newGraph.getNode("Mehadia"), newGraph.getNode("Drobeta"), 75);
        newGraph.addEdge(newGraph.getNode("Drobeta"), newGraph.getNode("Mehadia"), 75);

        newGraph.addEdge(newGraph.getNode("Drobeta"), newGraph.getNode("Craiova"), 120);
        newGraph.addEdge(newGraph.getNode("Craiova"), newGraph.getNode("Drobeta"), 120);

        newGraph.addEdge(newGraph.getNode("Sibiu"), newGraph.getNode("Fagaras"), 99);
        newGraph.addEdge(newGraph.getNode("Fagaras"), newGraph.getNode("Sibiu"), 99);

        newGraph.addEdge(newGraph.getNode("Sibiu"), newGraph.getNode("Rimnicu Vilcea"), 80);
        newGraph.addEdge(newGraph.getNode("Rimnicu Vilcea"), newGraph.getNode("Sibiu"), 80);

        newGraph.addEdge(newGraph.getNode("Rimnicu Vilcea"), newGraph.getNode("Pitesti"), 97);
        newGraph.addEdge(newGraph.getNode("Pitesti"), newGraph.getNode("Rimnicu Vilcea"), 97);

        newGraph.addEdge(newGraph.getNode("Rimnicu Vilcea"), newGraph.getNode("Craiova"), 146);
        newGraph.addEdge(newGraph.getNode("Craiova"), newGraph.getNode("Rimnicu Vilcea"), 146);

        newGraph.addEdge(newGraph.getNode("Fagaras"), newGraph.getNode("Bucharest"), 211);
        newGraph.addEdge(newGraph.getNode("Bucharest"), newGraph.getNode("Fagaras"), 211);

        newGraph.addEdge(newGraph.getNode("Pitesti"), newGraph.getNode("Bucharest"), 101);
        newGraph.addEdge(newGraph.getNode("Bucharest"), newGraph.getNode("Pitesti"), 101);

        newGraph.addEdge(newGraph.getNode("Craiova"), newGraph.getNode("Pitesti"), 138);
        newGraph.addEdge(newGraph.getNode("Pitesti"), newGraph.getNode("Craiova"), 138);

        newGraph.addEdge(newGraph.getNode("Bucharest"), newGraph.getNode("Giurgiu"), 90);
        newGraph.addEdge(newGraph.getNode("Giurgiu"), newGraph.getNode("Bucharest"), 90);

        newGraph.addEdge(newGraph.getNode("Bucharest"), newGraph.getNode("Urziceni"), 85);
        newGraph.addEdge(newGraph.getNode("Urziceni"), newGraph.getNode("Bucharest"), 85);

        newGraph.addEdge(newGraph.getNode("Urziceni"), newGraph.getNode("Hirsova"), 98);
        newGraph.addEdge(newGraph.getNode("Hirsova"), newGraph.getNode("Urziceni"), 98);

        newGraph.addEdge(newGraph.getNode("Hirsova"), newGraph.getNode("Eforie"), 86);
        newGraph.addEdge(newGraph.getNode("Eforie"), newGraph.getNode("Hirsova"), 86);

        newGraph.addEdge(newGraph.getNode("Urziceni"), newGraph.getNode("Vaslui"), 142);
        newGraph.addEdge(newGraph.getNode("Vaslui"), newGraph.getNode("Urziceni"), 142);

        newGraph.addEdge(newGraph.getNode("Vaslui"), newGraph.getNode("Iasi"), 92);
        newGraph.addEdge(newGraph.getNode("Iasi"), newGraph.getNode("Vaslui"), 92);

        newGraph.addEdge(newGraph.getNode("Iasi"), newGraph.getNode("Neamt"), 87);
        newGraph.addEdge(newGraph.getNode("Neamt"), newGraph.getNode("Iasi"), 87);
    }

}
