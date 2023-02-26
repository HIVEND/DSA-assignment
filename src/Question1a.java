import java.util.Arrays;
import java.util.PriorityQueue;

    public class Question1a {
        static class Edge {
            int to, time, cost;
            Edge(int to, int time, int cost) {
                this.to = to;
                this.time = time;
                this.cost = cost;
            }
        }

        static class Node implements Comparable<Node> {
            int node, cost, time;
            Node(int node, int cost, int time) {
                this.node = node;
                this.cost = cost;
                this.time = time;
            }
            public int compareTo(Node other) {
                return Integer.compare(this.cost, other.cost);
            }
        }

        static int cheapestRoute(int[][] edges, int[] charges, int source, int destination, int time_constraint) {
            int n = charges.length;
            int[] cost = new int[n];
            Arrays.fill(cost, Integer.MAX_VALUE);
            cost[source] = charges[source];
            int[] time = new int[n];
            Arrays.fill(time, Integer.MAX_VALUE);
            boolean[] visited = new boolean[n];
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.offer(new Node(source, cost[source], 0));

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.node == destination) {
                    return node.cost;
                }
                if (visited[node.node]) {
                    continue;
                }
                visited[node.node] = true;
                for (int[] edge : edges) {
                    int from = edge[0];
                    int to = edge[1];
                    int t = edge[2];
                    if (from == node.node && node.time + t <= time_constraint) {
                        if (cost[to] > node.cost + charges[to]) {
                            cost[to] = node.cost + charges[to];
                            time[to] = node.time + t;
                            queue.offer(new Node(to, cost[to], time[to]));
                        }
                    }
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            int[][] edges = {{0, 1, 5}, {0, 3, 2}, {1, 2, 5}, {3, 4, 5}, {4, 5, 6}, {2, 5, 5}};
            int[] charges = {10, 2, 3, 25, 25, 4};
            int source = 0;
            int destination = 5;
            int time_constraint = 14;
            System.out.println(cheapestRoute(edges, charges, source, destination, time_constraint));
    }
}
