package graph;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * An object that stores the set of nodes and the labeled edges that connect them.
 * A Graph cannot contain two nodes store entirely equal data
 * each labeled edge connect between two nodes.
 *          the type of Node maintained by this Graph
 */
public class Graph<E,T> {
    //Abstraction Funtion:
    //AF(this) = represents the map with Nodes stored inside this map and connection between them.
    //Nodes contain in 'nodes'. There is no same Node in a graph.
    //If there are no node, then the graph is an empty map.
    //Representation Invariant:
    // nodes != null
    private HashMap<E, Node<E,T>> nodes;

    /**
     * Throws an exception if the representation invariant is violated.
     */
    public void checkRep() {
        assert (nodes != null);
        for (E String : nodes.keySet()) {
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
    public boolean addNode(Node<E,T> node) {
        if (!this.containNode(node)) {
            nodes.put( node.getName(),node);
            return true;
        } else {
            return false;
        }
    }
    /**
     *returns a List that contains all the nodes in this graph
     * @return a list that contains all the nodes in graph.
     */
    public HashMap<E,Node<E,T>> getAllNodes() {
        return this.nodes;
    }

    /**
     * Check whether the specified node contained in this graph.
     * @spec.requires node != null
     * @param node need to be checked.
     * @return returns whether this graph contains a specified node.
     */
    public boolean containNode(Node<E,T> node) {
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
    public boolean addEdge(Node<E,T> src, Node<E,T> des, T label) {
        return src.addEdges(new Edge<E,T>(des,label));
    }

    /**
     * returns a list of Nodes that start from the node
     * @param node the origin of edge connect to other Nodes
     * @spec.requires node != null and nodes contains node.
     * @return returns a list of all Nodes that have their origin is this Node.
     */
    public ArrayList<Node<E,T>> children (Node<E,T> node) {
        ArrayList<Edge<E,T>> edges =  node.getEdges();
        ArrayList<Node<E,T>> result = new ArrayList<>();
        for (Edge<E,T> edge : edges) {
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
     public ArrayList<Edge<E,T>> getEdges(Node<E,T> node) {
         return node.getEdges();
     }




    /**
     * An object that stores value of Node in graph
     * In this case, we are required to store value of Node as String.
     */
    public static class Node<E, T> {
        private E name;
        private ArrayList<Edge<E,T>> edgeList;
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
        public Node(E name) {
            this.name = name;
            edgeList = new ArrayList<Edge<E,T>>();
        }

        /**
         * Get the value of this Node
         * @return returns the value of this Node
         */
        public E getName() {
            return this.name;
        }

        /**
         * Get the list of edge with src from this node.
         * @return returns the list of edge
         */
        public ArrayList<Edge<E,T>> getEdges() {
            return this.edgeList;
        }

        /**
         * Adds an edge with src from this node
         * @param edge need to be added
         * @spec.effects adds an edge with src from this node
         * @return true if the edge can be added and if it is not already present.
         */
        public boolean addEdges(Edge<E,T> edge) {
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
        public boolean removeEdges(Edge<E,T> edge) {
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
                Node<?,?> node = (Node<?,?>) obj;
                return name.equals(node.getName());
            } else {
                return false;
            }
        }

    }


    /**
     * A object that represent the connection between two Node in graph with the labeled value.
     */
    public static class Edge<E, T> {
        private Node<E,T> desNode;
        private T label;
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
        public Edge(Node<E,T> desNode, T label) {
            this.desNode = desNode;
            this.label = label;
        }

        /**
         * Get the destination node of this edge.
         * @return returns the destination node of this edge
         */
        public Node<E,T> getDesNode() {
            return this.desNode;
        }

        /**
         * Get the label this edge.
         * @return returns the label this edge
         */
        public T getLabel() {
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
                Edge<?,?> edge = (Edge<?,?>) obj;
                return this.label.equals(edge.label) && this.desNode.equals(edge.getDesNode());
            } else {
                return false;
            }
        }

    }

}

