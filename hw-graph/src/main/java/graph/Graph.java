package graph;

import java.util.ArrayList;
import java.util.HashSet;

public class Graph extends GraphADT {
    private HashSet<Node> nodes;

    public Graph() {
        this.nodes = new HashSet<>();
    }

    @Override
    public boolean addNode(Node node) {
        if (!this.containNode(node)) {
            nodes.add(node);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public HashSet<Node> getAllNodes() {
        return this.nodes;
    }

    @Override
    public boolean containNode(Node node) {
        return nodes.contains(node);
    }

    @Override
    public boolean addEdge(Node src, Node des, String label) {
        src.addEdges(new Edge(des, label));
    }

    @Override
    public ArrayList<Edge> neighbors (Node node) {
        return node.getEdges();
    }

    @Override
    public boolean removeNode(Node node) {
        if (!nodes.contains(node)) {
            return false;
        } else {
            nodes.remove(node);
            for (Node node : nodes) {
                ArrayList<Edge> edges = node.getEdges();
                for (Edge edge : edges) {
                    if (edge.getDesNode().equals(node)) {
                        node.removeEdges(edge);
                    }
                }
            }
        }
    }

    @Override
    public boolean removeEdge(Node src, Node des, String label) {
        if ( nodes.contains(src) && nodes.contains(des) && src.getEdges().contains(new Edge(des, label))) {
            src.getEdges().remove(new Edge(des, label));
            return true;
        } else {
            return false;
        }
    }

}
