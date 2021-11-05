package graph;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * An object that stores the set of nodes and the labeled edges that connect them.
 * A Graph cannot contain two nodes store entirely equal data
 * each labeled edge connect between two nodes.
 *          the type of Node maintained by this Graph
 */
public class Graph {
    //Abstraction Funtion:
    //AF(this) = represents the map with Nodes stored inside this map and connection between them.
    //Nodes contain in 'nodes'. There is no same Node in a graph.
    //If there are no node, then the graph is an empty map.
    //Representation Invariant:
    // nodes != null
    private HashMap<String, Node> nodes;

    /**
     * Throws an exception if the representation invariant is violated.
     */
    public void checkRep() {
        assert (nodes != null);
        for (String String : nodes.keySet()) {
            assert (nodes.get(String) != null);
        }
    }

    public Graph() {
        this.nodes = new HashMap<>();
        checkRep();
    }

    /**
     * Adds the specified node in this graph.
     * @spec.requires node != null
     * @param node the other node to be added.
     * @return true iff node is not in the graph and false if it is already in the graph.
     */
    public boolean addNode(Node node) {
        if (!this.containNode(node)) {
            nodes.put(node.getName(),node);
            return true;
        } else {
            return false;
        }
    }
    /**
     *returns a List that contains all the nodes in this graph
     * @return a list that contains all the nodes in graph.
     */
    public HashMap<String,Node> getAllNodes() {
        return this.nodes;
    }

    /**
     * Check whether the specified node contained in this graph.
     * @spec.requires node != null
     * @param node the other node to be added.
     * @return returns whether this graph contains a specified node.
     */
    public boolean containNode(Node node) {
        return nodes.containsValue(node);
    }

    /**
     * adds a new edge from node1 to node2 with lablel String s.
     * @param src origin of edge
     * @param des destination of edge
     * @param  label of the edge
     * @spec.requires node1 != null and node2 != null
     * @return true if the edge can be added into src node.
     */
    public boolean addEdge(Node src, Node des, String label) {
        return src.addEdges(new Edge(des, label));
    }

    /**
     * returns a list of Nodes that start from the node
     * @param node the origin of edge connect to other Nodes
     * @spec.requires node != null and nodes contains node.
     * @return returns a list of all Nodes that have their origin is this Node.
     */
    public ArrayList<Node> children (Node node) {
        ArrayList<Edge> edges =  node.getEdges();
        ArrayList<Node> result = new ArrayList<>();
        for (Edge edge : edges) {
            result.add(edge.getDesNode());
        }
        return result;
    }

    /**
     * returns a list of Edge that start from the node
     * @param node the origin of edge connect to other Nodes
     * @spec.requires node != null
     * @return returns a list of all Edges that have their origin is this Node.
     */
     public ArrayList<Edge> getEdges(Node node) {
         return node.getEdges();
     }

    /**
     * Removes the specified node in this graph and all the edges relates to this node if it is present.
     * @spec.requires node != null
     * @param  node to be removed.
     * @return true iff the set contains the specified element.
     */
    public boolean removeNode(Node node) {
        if (!nodes.containsValue(node)) {
            return false;
        } else {
            nodes.remove(node.getName());
            for (String node1 : nodes.keySet()) {
                ArrayList<Edge> edges = nodes.get(node1).getEdges();
                for (Edge edge : edges) {
                    if (edge.getDesNode().equals(node)) {
                        nodes.get(node1).removeEdges(edge);
                    }
                }
            }
            return true;
        }
    }

    /**
     * Removes the specified edge in this graph and all the edges relates to this node if it is present.
     * @spec.requires label != nul and node1 != null and node2 != null
     * removes an edge from node1 to node2 with lablel .
     * @param src the origin of the edge
     * @param des the destination of the edge
     * @param label the label of the edge.
     * @return true iff the set contains the specified edge.
     */
    public boolean removeEdge(Node src, Node des, String label) {
        if ( nodes.containsValue(src) && nodes.containsValue(des) && src.getEdges().contains(new Edge(des, label))) {
            src.getEdges().remove(new Edge(des, label));
            return true;
        } else {
            return false;
        }
    }



    /**
     * An object that stores value of Node in graph
     * In this case, we are required to store value of Node as String.
     */
    public static class Node {
        private String name;
        private ArrayList<Edge> edgeList;
        //Abstraction Function:
        //Node, p, represents a String object in graph and all the edge outgoing from this object.
        //If there is no Edge in edgeList, then this Node object does not have any edge to other Node.
        // Each edge contains in edgeList must be unique in this Node.
        //Representation Invariant:
        // name != null, edgeList != null or edgeList.size() >= 0 and edgeList has no null
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
         * @return returns the value of this Node
         */
        public String getName() {
            return this.name;
        }

        /**
         * Get the list of edge with src from this node.
         * @return returns the list of edge
         */
        public ArrayList<Edge> getEdges() {
            return this.edgeList;
        }

        /**
         * Adds an edge with src from this node
         * @param edge need to be added
         * @spec.effects adds an edge with src from this node
         * @return true if the edge can be added and if it is not already present.
         */
        public boolean addEdges(Edge edge) {
            if (!edgeList.contains(edge)) {
                this.edgeList.add(edge);
                return true;
            } else {
                return false;
            }
        }

        /**
         * removes an edge from this node
         * @param edge need to be removed
         * @spec.effects removes an edge from this node
         * @return true iff edge can be removed from graph.
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
            if ( obj instanceof Node) {
                Node node = (Node) obj;
                return name.equals(node.getName());
            } else {
                return false;
            }
        }

    }


    /**
     * A object that represent the connection between two Node in graph with the labeled value.
     */
    public static class Edge {
        private Node desNode;
        private String label;
        //Abstraction Funtion:
        //Edge, p, represents the connection between the two Node in graph with a value String label
        //Representation Invariant:
        //desNode != null & label != null
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
         * @return returns the destination node of this edge
         */
        public Node getDesNode() {
            return this.desNode;
        }

        /**
         * Get the label this edge.
         * @return returns the label this edge
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

}

