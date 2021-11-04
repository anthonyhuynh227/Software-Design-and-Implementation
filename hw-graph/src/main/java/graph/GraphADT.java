package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * An object that stores the set of nodes and the labeled edges that connect them.
 * A Graph cannot contain two nodes store entirely equal data
 * each labeled edge connect between two nodes.
 *          the type of Node maintained by this Graph
 */
public abstract class GraphADT {
    public GraphADT() {
    }

    /**
     * Adds the specified node in this graph.
     * @spec.requires node != null
     * @param node the other node to be added.
     * @return true iff node is not in the graph and false if it is already in the graph.
     */
    public abstract boolean addNode(Node node);

    /**
     * Check whether the specified node contained in this graph.
     * @spec.requires node != null
     * @param node the other node to be added.
     * @return returns whether this graph contains a specified node.
     */
    public abstract boolean containNode(Node node);

    /**
     *returns a List that contains all the nodes in this graph
     * @return a list that contains all the nodes in graph.
     */
    public abstract HashSet<Node> getAllNodes();

    /**
     * adds a new edge from node1 to node2 with lablel String s.
     * @param node1 origin of edge
     * @param node2 destination of edge
     * @param s label of the edge
     * @spec.requires node1 != null && node2 != null
     */
    public abstract boolean addEdge(Node node1, Node node2, String s);

    /**
     * returns a list of edges that start from the node
     * @param node the origin of edge need to be returned
     * @spec.requires node != null
     * @return returns a list of all edges that have their origin is node
     */
    public abstract ArrayList<Edge> neighbors(Node node);

    /**
     * Removes the specified node in this graph and all the edges relates to this node if it is present.
     * @spec.requires node != nul
     * @param  node to be removed.
     * @return true iff the set contains the specified element.
     */
    public abstract boolean removeNode(Node node );

    /**
     * Removes the specified edge in this graph and all the edges relates to this node if it is present.
     * @spec.requires label != nul && node1 != null && node2 != null
     * removes an edge from node1 to node2 with lablel .
     * @param src the origin of the edge
     * @param des the destination of the edge
     * @param label the label of the edge.
     * @return true iff the set contains the specified edge.
     */
    public abstract boolean removeEdge(Node src, Node des, String label);
}
