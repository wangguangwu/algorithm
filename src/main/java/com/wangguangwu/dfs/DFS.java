package com.wangguangwu.dfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Regular DFS Implementation using Recursion.
 *
 * @author wangguangwu
 */
public class DFS {

    public void dfs(List<Integer> elements, int start) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(elements, start, visited);
    }

    public void dfsHelper(List<Integer> elements, int current, Set<Integer> visited) {
        if (visited.contains(current)) {
            // If the current node has already been visited, return
            return;
        }

        visited.add(current);
        // Process the current node
        // eg, print its value
        System.out.println(elements.get(current));

        // Recursively visit all adjacent nodes
        // eg, assume adjacency by index +/- 1
        if (current - 1 >= 0) {
            // Visit left neighbor
            dfsHelper(elements, current - 1, visited);
        }
        if (current + 1 < elements.size()) {
            // Visit right neighbor
            dfsHelper(elements, current + 1, visited);
        }
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("DFS traversal:");
        dfs.dfs(elements, 0);
    }
}
