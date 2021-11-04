package graph;

/**
 * A object that represent the connection between two Node in graph with the labeled value.
 */
public class Edge {
    private Node desNode;
    private String label;

    /**
     * @param desNode the destination of this edge
     * @param label the label of this edge
     * @spec.effects construct a new labeled edge t between current node and desNode
     * with t.desNode = desNode, t.label = label;
     */
    public Edge(Node desNode, String label) {
        this.desNode = desNode;
        this.label = label;
    }

    /**
     * Get the destination node of this edge.
     * @returns returns the destination node of this edge
     */
    public Node getDesNode() {
        return this.desNode;
    }

    /**
     * Get the label this edge.
     * @returns returns the label this edge
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Standard hashCode function.
     *
     * @return an int that all objects equal to this will also return
     */
    public int hashCode() {
        return label.hashCode();
    }

    /**
     * Standard equality operation.
     *
     * @param obj the object to be compared for equality
     * @return true iff 'obj' is an instance of a Edge and 'this' and 'obj' represent the same
     * Edge.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge edge = (Edge) obj;
            return this.label.equals(edge.label) && this.desNode.equals(edge.getDesNode());
        } else {
            return false;
        }
    }
}
