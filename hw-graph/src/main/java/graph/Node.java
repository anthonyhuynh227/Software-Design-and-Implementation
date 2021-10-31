package graph;

/**
 * An object that stores value of Node in graph
 * In this case, we are required to store value of Node as String.
 */
public class Node {
    private String value;

    /**
     * @param s the value of Node
     * @spec.requires s != null
     * @spec.effects construct a new Node t with t.value = s
     */
    public Node(String s);

    /**
     * Get the value of this Node
     * @returns returns the value of this Node
     */
    public static String getValue();
}
