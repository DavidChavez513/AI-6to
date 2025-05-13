import java.util.Random;
import java.util.Stack;

public class Main {

    static String[] usCities = {
        "NY", "LA", "CHI", "HOU", "PHX",
        "PHI", "SA", "SD", "DAL", "SJ",
        "AUS", "JAX", "FW", "COL", "CHA",
        // "SF", "IND", "SEA", "DEN", "DC",
        // "BOS", "ELP", "NAS", "DET", "OKC",
        // "POR", "LV", "MEM", "LOU", "BAL",
        // "MIL", "ABQ", "TUC", "FRE", "SAC",
        // "KC", "LB", "MES", "ATL", "CS",
        // "VB", "RAL", "OMA", "MIA", "OAK",
        // "MIN", "TUL", "WIC", "NO", "ARL"
    };

    static String[] controlCase1 = {
        "Oradea", "Zerind", "Arad", "Timisoara", "Lugoj", "Mehadia", 
        "Drobeta", "Craiova", "Rimnicu Vilcea", "Sibiu", "Fagaras", 
        "Pitesti", "Bucharest", "Giurgiu", "Neamt", "Iasi", "Vaslui", 
        "Urziceni", "Hirsova", "Eforie"
    };

    static void printPath(Stack<Node> path, String city1, String city2, String method) {

        System.out.println("Search by "+ method +" -> Fly from " + city1 + " to " + city2);
        
        if (path.empty()) {
            System.out.println("No path exists");
        }

        while (!path.empty()) {
            System.out.print(path.pop().cityName + " -> ");
        }
        System.out.println("End travel");

    }
    public static void main(String[] args) {

        Random rand = new Random();

        int cities = rand.nextInt(controlCase1.length) + 1;
        String[] citiesAvailables = new String[cities];

        for (int i = 0; i < cities; i++) {
            citiesAvailables[i] = usCities[rand.nextInt(usCities.length)];
        }

        // Graph spaceGraph = Graph.makeGraph(citiesAvailables);
        // spaceGraph.makeConnections();
        
        Graph spaceGraph = Graph.controlCase1();
        Graph spaceGraph2 = Graph.controlCase1();
        Graph spaceGraph3 = Graph.controlCase1();
        
        int cityA = 0, cityB = 0;
        String aNodeName = "";
        String bNodeName = "";

        do {
            cityA = rand.nextInt(cities);
            cityB = rand.nextInt(cities);
        } while (cityA == cityB);

        aNodeName = controlCase1[cityA];
        bNodeName = controlCase1[cityB];

        Stack<Node> path = spaceGraph.BFS(aNodeName, bNodeName);
        printPath(path, aNodeName, bNodeName, "BFS");

        path = spaceGraph2.DFS(aNodeName, bNodeName);
        printPath(path, aNodeName, bNodeName, "DFS");

        path = spaceGraph3.dijkstra(aNodeName, bNodeName);
        printPath(path, aNodeName, bNodeName, "Hill Climbing");

    }
}