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

package marvel.scriptTestRunner;

import graph.Graph;
import marvel.MarvelPaths;

import java.io.*;
import java.util.*;

/**
 * This class implements a testing driver which reads test scripts from
 * files for testing Graph, the Marvel parser, and your BFS algorithm.
 */
public class MarvelTestDriver {
    private final Map<String, Graph<String, String>> graphs = new HashMap<String, Graph<String, String>>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.require r != null && w != null
     * @spec.effect Creates a new GraphTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public MarvelTestDriver(Reader r, Writer w) {
        // TODO: Implement this, reading commands from `r` and writing output to `w`.
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
                case "LoadGraph":
                    loadGraph(arguments);
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
        Graph<String, String> graphName1 = new Graph<String, String>();
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
        Graph<String, String> a = graphs.get(graphName);
        a.addNode(new Graph.Node<String, String>(nodeName));
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
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         String edgeLabel) {
        // TODO Insert your code here.
        Graph<String, String> a = graphs.get(graphName);
        a.addEdge(a.getAllNodes().get(parentName),a.getAllNodes().get(childName) , edgeLabel);
        output.println("added edge "+edgeLabel+" from "+parentName+" to "+childName+" in "+graphName);
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
        Graph<String, String> a = graphs.get(graphName);
        HashMap<String, Graph.Node<String, String>> listNode = a.getAllNodes();
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
        Graph<String, String> a = graphs.get(graphName);
        HashMap<String, Graph.Node<String, String>> nodes = a.getAllNodes();
        Graph.Node<String, String> node = nodes.get(parentName);
        ArrayList<Graph.Edge<String, String>> edges = node.getEdges();
        ArrayList<String> names = new ArrayList<>();
        for (Graph.Edge<String, String> edge : edges) {
            names.add(edge.getDesNode().getName()+"("+edge.getLabel()+")");
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

    private void loadGraph(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String fileName = arguments.get(1);

        loadGraph(graphName, fileName);
    }

    private void loadGraph(String graphName, String fileName) {
        // TODO Insert your code here.
        Graph<String, String> graphName1 = MarvelPaths.marvelGraph(fileName);
        graphs.put(graphName, graphName1);
        output.println("loaded graph "+graphName);
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
        Graph<String, String> a = graphs.get(graphName);
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
            ArrayList<ArrayList<String>> result = MarvelPaths.shortestPath(a, node_a, node_b);
            if (result.isEmpty()) {
                output.println("path from "+node_a+" to "+node_b+":");
                output.println("no path found");
            } else {
                output.println("path from "+node_a+" to "+node_b+":");
                for (int i = 0; i < result.size(); i++) {
                    output.println(result.get(i).get(0)+" to "+result.get(i).get(1)+" via "+result.get(i).get(2));
                }
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
