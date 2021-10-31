package graph.junitTests;

import graph.Graph;
import org.junit.*;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;
/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * Graph class.
 */
public class GraphTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested
    private static Node node(String s) {return new Node(s)};
    private static Edge edge(Node a, Node b, String s) {return new Edge(a,b,s)};
    private static Graph graph() {return new Graph()};

    @Test
    public void testaddNode() {
        assertEquals(node("A"), graph().addNode(node("A")));
    }

    @Test
    public void testaddEdge() {
        assertEquals(edge(a,b,c), graph().getEdges(a,b));
    }

    @Test
    public void testremoveNode() {
        assertFalse(Node("A"), graph().addNode(node("A") );
    }

    @Test
    public void testChildren() {
        assertEquals(node("B"), graph().addEdge("a", "B", "s"););
    }
}
