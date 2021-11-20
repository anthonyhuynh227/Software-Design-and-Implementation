/*
 * Copyright (C) 2021 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder.scriptTestRunner;

import graph.Graph;
import marvel.MarvelPaths;
import pathfinder.datastructures.Path;
import pathfinder.textInterface.DijkstraAlgo;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

    private final Map<String, Graph<String, Double>> graphs = new HashMap<String, Graph<String, Double>>();
    private final PrintWriter output;
    private final BufferedReader input;
    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        // TODO: Implement this, reading commands from `r` and writing output to `w`.
        // See GraphTestDriver as an example.
        // See GraphTestDriver as an example.
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @throws IOException if the input or output sources encounter an IOException
     * @spec.effect Executes the commands read from the input and writes results to the output
     **/
    // Leave this method public
    public void runTests() throws IOException {
        // TODO: Implement this.
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while(st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }
    private void executeCommand(String command, List<String> arguments) {
        try {
            switch(command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch(Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }

    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        // TODO Insert your code here.
        Graph<String, Double> graphName1 = new Graph<String, Double>();
        graphs.put(graphName, graphName1);
        output.println("created graph "+graphName);
        // graphs.put(graphName, ___);
        // output.println(...);
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        // TODO Insert your code here.
        Graph<String, Double> a = graphs.get(graphName);
        a.addNode(new Graph.Node<String, Double>(nodeName));
        output.println("added node "+nodeName+" to "+graphName);
        // ___ = graphs.get(graphName);
        // output.println(...);
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName =  arguments.get(2);
        Double edgeLabel = Double.parseDouble(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel) {
        // TODO Insert your code here.
        Graph<String, Double> a = graphs.get(graphName);
        a.addEdge(a.getAllNodes().get(parentName),a.getAllNodes().get(childName) , edgeLabel);
        String first = String.format("added edge %.3f ", edgeLabel);
        output.println(first +" from "+parentName+" to "+childName+" in "+graphName);
        // ___ = graphs.get(graphName);
        // output.println(...);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        // TODO Insert your code here.
        Graph<String, Double> a = graphs.get(graphName);
        HashMap<String, Graph.Node<String, Double>> listNode = a.getAllNodes();
        ArrayList<String> result = new ArrayList<>();
        for (String name : listNode.keySet()) {
            result.add(name);
        }
        Collections.sort(result);
        String b = "";
        for (String string : result) {
            b = b+" "+string;
        }
        output.println(graphName+ " contains:"+b);
        // ___ = graphs.get(graphName);
        // output.println(...);
    }

    private void listChildren(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        // TODO Insert your code here.
        Graph<String, Double> a = graphs.get(graphName);
        HashMap<String, Graph.Node<String, Double>> nodes = a.getAllNodes();
        Graph.Node<String, Double> node = nodes.get(parentName);
        ArrayList<Graph.Edge<String, Double>> edges = node.getEdges();
        ArrayList<String> names = new ArrayList<>();
        for (Graph.Edge<String, Double> edge : edges) {
            String line = String.format("(%.3f",edge.getLabel());
            StringBuilder each = new StringBuilder();
            each.append(edge.getDesNode().getName());
            each.append(line);
            each.append(")");
            names.add(each.toString());
        }
        Collections.sort(names);
        String b = "";
        for (String string : names) {
            b = b+" "+string;
        }
        output.println("the children of "+parentName+" in "+graphName+" are:"+b);
        // ___ = graphs.get(graphName);
        // output.println(...);
    }

    private void findPath(List<String> arguments) {
        if(arguments.size() != 3) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String node_a = arguments.get(1);
        String node_b = arguments.get(2);
        findPath(graphName, node_a, node_b);
    }

    private void findPath(String graphName, String node_a, String node_b) {
        // TODO Insert your code here.
        Graph<String, Double> a = graphs.get(graphName);
        if (!a.containNode(a.getAllNodes().get(node_a)) && !a.containNode(a.getAllNodes().get(node_b)) ) {
            output.println("unknown: "+node_a);
            output.println("unknown: "+node_b);
        } else if (!a.containNode(a.getAllNodes().get(node_a))) {
            output.println("unknown: "+node_a);
        } else if (!a.containNode(a.getAllNodes().get(node_b))) {
            output.println("unknown: "+node_b);
        } else if ( node_a.equals(node_b)) {
            output.println("path from "+node_a+" to "+node_b+":");
        } else {
            Path<String> result = DijkstraAlgo.shortestPath(a, node_a, node_b);
            if (result.getCost() == 0) {
                output.println("path from "+node_a+" to "+node_b+":");
                output.println("no path found");
            } else {
                output.println("path from "+node_a+" to "+node_b+":");
                Iterator<Path<String>.Segment> iterator = result.iterator();
                Double totalCost = 0.00;
                while (iterator.hasNext()) {
                    Path<String>.Segment next = iterator.next();
                    totalCost += next.getCost();
                    StringBuilder line = new StringBuilder();
                    line.append(next.getStart());
                    line.append(" to ");
                    line.append(next.getEnd());
                    String eachLine = String.format(" with weight %.3f", next.getCost());
                    line.append(eachLine);
                    output.println(line);
                }
                String lastLine = String.format("total cost: %.3f", totalCost);
                output.println(lastLine);
            }
        }
        // ___ = graphs.get(graphName);
        // output.println(...);
    }
    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
