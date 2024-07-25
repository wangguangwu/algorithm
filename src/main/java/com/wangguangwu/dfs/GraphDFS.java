package com.wangguangwu.dfs;

import java.util.*;

/**
 * Graph DFS implementation.
 *
 * @author wangguangwu
 */
public class GraphDFS {

    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private Set<Integer> visited = new HashSet<>();

    public void addEdge(int v, int w) {
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
    }

    /**
     * DFS using recursion.
     *
     * @param v v
     */
    public void dfs(int v) {
        if (visited.contains(v)) {
            return;
        }

        visited.add(v);
        System.out.println(v);

        List<Integer> neighbors = graph.getOrDefault(v, new ArrayList<>());
        Collections.sort(neighbors);
        for (Integer neighbor : neighbors) {
            dfs(neighbor);
        }
    }

    public void clearVisited() {
        visited.clear();
    }

    public void dfsIterative(int start) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Integer v = stack.pop();
            if (!visited.contains(v)) {
                visited.add(v);
                System.out.println(v);

                List<Integer> neighbors = graph.getOrDefault(v, new ArrayList<>());
                Collections.sort(neighbors);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    Integer neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphDFS graphDfs = new GraphDFS();
        graphDfs.addEdge(0, 1);
        graphDfs.addEdge(0, 2);
        graphDfs.addEdge(1, 2);
        graphDfs.addEdge(2, 0);
        graphDfs.addEdge(2, 3);
        graphDfs.addEdge(3, 3);

        System.out.println("Graph DFS traversal starting from vertex 2 (recursive):");
        graphDfs.dfs(2);

        graphDfs.clearVisited();

        System.out.println("\nGraph DFS traversal starting from vertex 2 (iterative):");
        graphDfs.dfsIterative(2);
    }
}
