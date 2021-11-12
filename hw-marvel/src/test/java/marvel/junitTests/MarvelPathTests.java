package marvel.junitTests;

import graph.Graph;
import marvel.MarvelPaths;
import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

public class MarvelPathTests {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(30); // 30 seconds max per method tested
    Graph marvelGraph = MarvelPaths.marvelGraph("csStudent.csv");

    @Test
    public void testNoCharInGraph() {
        assertEquals(0, MarvelPaths.shortestPath(marvelGraph, "John", "Mark").size());
    }

    @Test
    public void testNoPathFound() {
        assertEquals(0,MarvelPaths.shortestPath(marvelGraph, "John", "Jackie").size());
    }

    @Test
    public void testShortestPath() {
        assertEquals(2,MarvelPaths.shortestPath(marvelGraph, "John", "Tom").size());
    }

    @Test
    public void testAlphabeticallyCharOrder() {
        assertEquals("Anthony",MarvelPaths.shortestPath(marvelGraph, "John", "Jasmine").get(1).get(0));
    }

    @Test
    public void testAlphabeticallyLabelOrder() {
        assertEquals("CSE150",MarvelPaths.shortestPath(marvelGraph, "John", "Anthony").get(0).get(2));
    }
}
