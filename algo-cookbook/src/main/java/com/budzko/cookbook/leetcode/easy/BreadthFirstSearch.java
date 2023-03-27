package com.budzko.cookbook.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 0);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(4, 2);
        graph.addEdge(4, 3);
        System.out.println(traverseGraph(1, graph));
        System.out.println(bfs(1, 4, graph));
    }

    private static List<Integer> bfs(int initialVertex, int targetVertex, Graph graph) {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(initialVertex);
        visited.add(initialVertex);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            result.add(vertex);
            Set<Integer> relatedVertices = graph.verticesMap.get(vertex);
            if (relatedVertices != null) {
                relatedVertices.forEach(relatedVertex -> {
                    if (visited.add(relatedVertex)) {
                        queue.add(relatedVertex);
                    }
                });
            }

        }
        return result;
    }

    private static List<Integer> traverseGraph(int initialVertex, Graph graph) {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(initialVertex);
        visited.add(initialVertex);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            result.add(vertex);
            Set<Integer> relatedVertices = graph.verticesMap.get(vertex);
            if (relatedVertices != null) {
                relatedVertices.forEach(relatedVertex -> {
                    if (visited.add(relatedVertex)) {
                        queue.add(relatedVertex);
                    }
                });
            }
        }
        return result;
    }

    public static class Graph {
        private final Map<Integer, Set<Integer>> verticesMap = new HashMap<>();
        private final int vertexCount;

        public Graph(int vertexCount) {
            this.vertexCount = vertexCount;

        }

        public int getVertexCount() {
            return vertexCount;
        }

        void addEdge(int v, int u) {
            Set<Integer> vs = verticesMap.computeIfAbsent(v, key -> new HashSet<>());
            vs.add(u);
        }
    }
}
