import java.util.*;

public class Question1b {
    public static List<Integer> findImpactedDevices(int[][] edges, int targetDevice) {
        // create an adjacency list representation of the graph
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            adjacencyList.putIfAbsent(x, new ArrayList<>());
            adjacencyList.putIfAbsent(y, new ArrayList<>());
            adjacencyList.get(x).add(y);
            adjacencyList.get(y).add(x);
        }

        // remove the edge that connects the failed device to its neighbor
        List<Integer> neighbors = adjacencyList.get(targetDevice);
        neighbors.remove((Integer) targetDevice);
        adjacencyList.put(targetDevice, new ArrayList<>());

        // perform a BFS starting from node 0 to find all the connected nodes
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited.add(node);
            List<Integer> nodeNeighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
            for (int neighbor : nodeNeighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        // any node that is not visited during the BFS is disconnected from the gateway
        List<Integer> impactedDevices = new ArrayList<>();
        for (int node : adjacencyList.keySet()) {
            if (!visited.contains(node)) {
                impactedDevices.add(node);
            }
        }

        return impactedDevices;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int targetDevice = 4;
        List<Integer> impactedDevices = findImpactedDevices(edges, targetDevice);
        System.out.println(impactedDevices);
    }
}