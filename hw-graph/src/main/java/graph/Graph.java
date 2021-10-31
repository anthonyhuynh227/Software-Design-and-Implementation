package graph;

import java.util.List;

/**
 * An object that stores the set of nodes and the labeled edges that connect them.
 * A Graph cannot contain two nodes store entirely equal data
 * each labeled edge connect between two nodes.
 * @param Node
 *          the type of Node maintained by this Graph
 * @param Edge
 *          the type of Edge connect two nodes in this graph
 */
public abstract class Graph {

    /**
     * Adds the specified node in this graph.
     * @spec.requires node != null && !graph.contains(node)
     * @param node the other node to be added.
     */
    public abstract void addNode(Node node );

    /**
     * Check whether the specified node contained in this graph.
     * @spec.requires node != null
     * @param node the other node to be added.
     * @return returns whether this graph contains a specified node.
     */
    public abstract boolean containNode(Node node );

    /**
     *returns a List that contains all the nodes in this graph
     * @return a list that contains all the nodes in graph.
     */
    public abstract List<Node>();

    /**
     * adds a new edge from node1 to node2 with lablel String s.
     * @param node1 origin of edge
     * @param node2 destination of edge
     * @param s label of the edge
     * @spec.requires node1 != null && node2 != null
     */
    public abstract void addEdge(Node node1, Node node2, String s);

    /**
     * returns the label of edge from node1 to node2 or null if there is no such edge.
     * @param node1 origin of edge
     * @param node2 destination of edge
     * @spec.requires node1 != null && node2 != null
     * @retun return the label of the edge from node1 to node2
     */
    public abstract String getEdges(Node node1, Node node2);

    /**
     * returns a list of edges that start from the node
     * @param node the origin of edge need to be returned
     * @spec.requires node != null
     * @return retuns a list of all edges that have their origin is node
     */
    public abstract List<Edge> neighbors(Node node);

    /**
     * Removes the specified node in this graph and all the edges relates to this node.
     * @spec.requires node != null && graph.contains(node)
     * @param node the other node to be removed.
     */
    public abstract void removeNode(Node node );

    /**
     * removes a new edge from node1 to node2 with lablel String s.
     * @param node1 origin of edge
     * @param node2 destination of edge
     * @param s label of the edge
     * @spec.requires node1 != null && node2 != null
     */
    public abstract void removeEdge(Node node1, Node node2, String s);
}
