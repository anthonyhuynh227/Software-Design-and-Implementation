package graph;

/**
 * A object that represent the connection between two Node in graph with the labeled value.
 */
public class Edge {
    private Node node1;
    private Node node2;
    private String value;

    /**
     * @param node1 the origin of edge
     * @param node2 the destination of this edge
     * @param s the label of this edge
     * @spec.effects construct a new labeled edge t between node1 and node2
     * with t.node1 = node1, t.node2 = node2, t.value = s;
     */
    public Edge(Node node1, Node node2, String s);

}
