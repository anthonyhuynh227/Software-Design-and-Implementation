package graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * An object that stores value of Node in graph
 * In this case, we are required to store value of Node as String.
 */
public abstract class Node {
    private String name;
    private ArrayList<Edge> edgeList;

    /**
     * @param name of the node.
     * @spec.requires name != null
     * @spec.effects construct a node with specified name.
     */
    public Node(String name) {
        this.name = name;
        edgeList = new ArrayList<>();
    }

    /**
     * Get the value of this Node
     * @returns returns the value of this Node
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the list of edge with src from this node.
     * @returns returns the list of edge
     */
    public ArrayList<Edge> getEdges() {
        return this.edgeList;
    }

    /**
     * Adds an edge with src from this node
     * @spec.effects adds an edge with src from this node
     * @return true if the edge can be added and if it is not already present.
     */
    public void addEdges(Edge edge) {
        if (!edgeList.contains(edge)) {
            this.edgeList.add(edge);
            return true;
        } else {
            return false;
        }
    }

    /**
     * removes an edge from this node
     * @spec.effects removes an edge from this node
     */
    public boolean removeEdges(Edge edge) {
        if (edgeList.contains(edge)) {
            this.edgeList.remove(edge);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also return
     */
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Standard equality operation.
     *
     * @param obj the object to be compared for equality
     * @return true iff 'obj' is an instance of a Node and 'this' and 'obj' represent the same
     * Node.
     */
     public boolean equals(Object obj) {
         if ( obj instanceof Node ) {
             Node node = (Node) obj;
             return name.equals(node.getName());
         } else {
             return false;
         }
     }

}
