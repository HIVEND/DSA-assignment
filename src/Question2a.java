import java.util.*;

    public class Question2a {
            static int[] values; // An array to store the values of each species
            static int[] parent; // An array to store the parent of each species
            static int[] ancestor; // An array to store the nearest relative prime ancestor of each species
            static Map<Integer, List<Integer>> graph; // A map to store the graph information, where the key is the node and the value is a list of its neighbors

            static int gcd(int a, int b) { // A function to find the greatest common divisor between two values
                return b == 0 ? a : gcd(b, a % b);
            }

            static void dfs(int node) { // A DFS function to traverse the graph
                for (int neighbor : graph.get(node)) { // For each neighbor of the current node
                    if (ancestor[neighbor] == -1) { // If the neighbor has not been visited
                        ancestor[neighbor] = node; // Set the ancestor of the neighbor to the current node
                        dfs(neighbor); // Recursively call the DFS function on the neighbor
                    }
                }
            }

            static int[] getAncestor(int[] values, int[][] edges) {
                int n = values.length; // Store the number of species
                Question2a.values = values; // Set the values array
                parent = new int[n]; // Initialize the parent array
                ancestor = new int[n]; // Initialize the ancestor array
                graph = new HashMap<>(); // Initialize the graph map

                for (int i = 0; i < n; i++) {
                    parent[i] = i; // Set each node to be its own parent initially
                    ancestor[i] = -1; // Set the ancestor of each node to -1 initially
                    graph.put(i, new ArrayList<>()); // Put each node in the graph map with an empty list of neighbors
                }

                for (int[] edge : edges) { // For each edge in the input edges array
                    int u = edge[0]; // Get the first node in the edge
                    int v = edge[1]; // Get the second node in the edge
                    graph.get(u).add(v); // Add the second node as a neighbor of the first node
                    graph.get(v).add(u); // Add the first node as a neighbor of the second node
                }

                for (int i = 0; i < n; i++) {
                    if (ancestor[i] == -1) { // If the node has not been visited
                        ancestor[i] = i; // Set the ancestor of the node to itself
                        dfs(i); // Call the DFS function starting from the node
                    }
                }

                for (int i = 0; i < n; i++) {
                    int node = i;
                    while (node != ancestor[node]) { // While the node is not its own ancestor
                        int ancestorNode = ancestor[node];
                        if (gcd(values[node], values[ancestorNode]) == 1) { // If the values of the node and its ancestor are relative primes
                            parent[i] = ancestorNode;
                            break;
                        }
                        node = ancestorNode;
                    }
                }

                int[] result = new int[n];
                for (int i = 0; i < n; i++) {
                    result[i] = parent[i] == i ? -1 : parent[i];
                }

                return result;
            }

            public static void main(String[] args) {
                int[] values = {3, 2, 6, 6, 4, 7, 12};
                int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};

                int[] result = getAncestor(values, edges);
                System.out.println(Arrays.toString(result));
        }
    }