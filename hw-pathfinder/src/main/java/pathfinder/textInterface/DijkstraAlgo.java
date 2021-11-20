package pathfinder.textInterface;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * This class using Dijkstra's Algorithm to find the shortestPath in Graph.
 *
 */

public class DijkstraAlgo {
    public static<T> Path<T> shortestPath(Graph<T, Double> graph, T start, T des) {
        PriorityQueue<Path<T>> queue = new PriorityQueue<>(
                (a,b) -> (int) (a.getCost() -  b.getCost())
        );
        HashSet<Graph.Node<T, Double>> finished = new HashSet<>();
        Path<T> startPoint = new Path<>(start);
        Graph.Node<T, Double> startNode = graph.getAllNodes().get(start);
        Graph.Node<T, Double> desNode = graph.getAllNodes().get(des);
        queue.add(startPoint);
        while (!queue.isEmpty()) {
            Path<T> minPath = queue.poll();
            T end = minPath.getEnd();
            Graph.Node<T, Double> endNode = graph.getAllNodes().get(end);
            if ( endNode.equals(desNode)) {
                return minPath;
            }
            if ( finished.contains(endNode)) {
                continue;
            }
            ArrayList<Graph.Edge<T, Double>> edges = endNode.getEdges();
            for (Graph.Edge<T, Double> edge : edges) {
                Graph.Node<T,Double> childNode = edge.getDesNode();
                if (!finished.contains(childNode)) {
                    Path<T> newPath = minPath.extend(childNode.getName(), edge.getLabel());
                    queue.add(newPath);
                }
            }
            finished.add(endNode);
        }
        return new Path<T>(start);
    }
}
