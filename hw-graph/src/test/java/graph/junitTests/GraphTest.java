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
    private static Graph<String,String> graph() { return new Graph<String, String>();}
    private static Graph.Node<String, String> node(String s) {return new Graph.Node<String, String>(s);}
    private static Graph.Edge<String, String> edge(Graph.Node<String, String> node, String e) {return new Graph.Edge<String, String>(node,e);}

    @Test
    public void testEmptyGraph() {
        assertEquals(0, graph().getAllNodes().size());
    }

    @Test
    public void testAddNode() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode(node("A"));
        assertTrue(graph.containNode(node("A")));
    }

    @Test
    public void testAddEdge() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode(node("A"));
        graph.addNode(node("B"));
        graph.addEdge(graph.getAllNodes().get("A"),graph.getAllNodes().get("B"), "This is Edge AB");
        assertEquals(edge(graph.getAllNodes().get("B"), "This is Edge AB") , graph.getAllNodes().get("A").getEdges().get(0));
    }

    @Test
    public void testChildren() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode(node("A"));
        graph.addNode(node("B"));
        graph.addEdge(graph.getAllNodes().get("A"),graph.getAllNodes().get("B"), "This is Edge AB");
        assertEquals(graph.children(graph.getAllNodes().get("A")).get(0), graph.getAllNodes().get("B"));
    }

    @Test
    public void testAddDuplicateNode() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode(node("A"));
        assertFalse(graph.addNode(node("A")));
    }

    @Test
    public void testAddDuplicateEdge() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode(node("A"));
        graph.addNode(node("B"));
        graph.addEdge(graph.getAllNodes().get("A"),graph.getAllNodes().get("B"), "This is Edge AB");
        assertFalse(graph.addEdge(graph.getAllNodes().get("A"),graph.getAllNodes().get("B"), "This is Edge AB")
);
    }

    @Test
    //Test add an Edge with same Nodes but different label.
    public void testEdgeWithSameNodes() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode(node("A"));
        graph.addNode(node("B"));
        graph.addEdge(graph.getAllNodes().get("A"),graph.getAllNodes().get("B"), "This is Edge AB");
        assertTrue(graph.addEdge(graph.getAllNodes().get("A"),graph.getAllNodes().get("B"), "This is Edge AB2")
);
    }

    @Test
    // Test an loop edge.
    public void testLoopEdge() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode(node("A"));
        assertTrue(graph.addEdge(graph.getAllNodes().get("A"),graph.getAllNodes().get("A"), "This is Edge AA"));
    }
}
