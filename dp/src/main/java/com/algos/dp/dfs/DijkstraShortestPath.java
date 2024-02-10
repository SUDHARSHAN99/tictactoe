package com.algos.dp.dfs;

import java.util.*;

public class DijkstraShortestPath {

    private static class Node implements Comparable<Node> {
        public int id;
        public int distance;

        public Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static List<Integer> shortestPath(Map<Integer, Map<Integer, Integer>> graph, int source, int destination) {
        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for (Map.Entry<Integer, Map<Integer, Integer>> entry : graph.entrySet()) {
            distances.put(entry.getKey(), Integer.MAX_VALUE);
        }

        distances.put(source, 0);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(source, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (visited.contains(currentNode.id)) {
                continue;
            }

            visited.add(currentNode.id);

            if (currentNode.id == destination) {
                break;
            }

            for (Map.Entry<Integer, Integer> neighborEntry : graph.get(currentNode.id).entrySet()) {
                int neighborId = neighborEntry.getKey();
                int edgeWeight = neighborEntry.getValue();

                if (!visited.contains(neighborId) && distances.get(neighborId) > distances.get(currentNode.id) + edgeWeight) {
                    distances.put(neighborId, distances.get(currentNode.id) + edgeWeight);
                    queue.add(new Node(neighborId, distances.get(neighborId)));
                }
            }
        }

        List<Integer> shortestPath = new ArrayList<>();

        int currentId = destination;
        while (currentId != source) {
            for (Map.Entry<Integer, Integer> neighborEntry : graph.get(currentId).entrySet()) {
                int neighborId = neighborEntry.getKey();
                int edgeWeight = neighborEntry.getValue();

                if (distances.get(neighborId) + edgeWeight == distances.get(currentId)) {
                    shortestPath.add(neighborId);
                    currentId = neighborId;
                    break;
                }
            }
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }

    public static void main(String[] args) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        // Create the graph
        graph.put(0, new HashMap<>());
        graph.get(0).put(1, 4);
        graph.get(0).put(2, 3);
        graph.put(1, new HashMap<>());
        graph.get(1).put(2, 1);
        //graph.put(Integer.valueOf(1)).put(3, 2);
        graph.put(2, new HashMap<>());
        graph.get(2).put(3, 4);
        graph.put(3, new HashMap<>());

        // Find the shortest path from node 0 to node 3
        List<Integer> shortestPath = shortestPath(graph, 0, 3);

        System.out.println("Shortest path from 0 to 3: " + shortestPath);
    }
}

