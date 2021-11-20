package marvel;

import graph.Graph;

import java.util.*;

public class MarvelPaths {

    public static Graph<String, String> marvelGraph(String csvFile) {
        ArrayList<ArrayList<String>> resultFile = MarvelParser.parseData(csvFile);
        Graph<String, String> Marvel = new Graph<String, String>();
        // adds all the character into graph
        for (int i = 0; i < resultFile.size(); i++) {
            ArrayList<String> eachLine = resultFile.get(i);
            Marvel.addNode(new Graph.Node<String, String>(eachLine.get(0)));
        }
        // creates a HashMap where each key movie contains a set of character in that movie.
        HashMap<String, ArrayList<String>> listMovie = new HashMap<>();
        for (int i = 0; i < resultFile.size(); i++) {
            if (!listMovie.containsKey(resultFile.get(i).get(1))) {
                listMovie.put(resultFile.get(i).get(1), new ArrayList<>());
                listMovie.get(resultFile.get(i).get(1)).add(resultFile.get(i).get(0));
            } else {
                listMovie.get(resultFile.get(i).get(1)).add(resultFile.get(i).get(0));
            }
        }

        // Adds all the edges between character in each movie.
        for (String key : listMovie.keySet()) {
            ArrayList<String> character = listMovie.get(key);
            for (int i = 0; i < character.size(); i++) {
                for (int j = i + 1; j < character.size(); j++) {
                    Marvel.addEdge(Marvel.getAllNodes().get(character.get(i)), Marvel.getAllNodes().get(character.get(j)), key);
                    Marvel.addEdge(Marvel.getAllNodes().get(character.get(j)), Marvel.getAllNodes().get(character.get(i)), key);
                }
            }
        }
        return Marvel;

    }
    public static ArrayList<ArrayList<String>> shortestPath(Graph<String, String> Marvel, String start, String dest){
        LinkedList<String> queue = new LinkedList<>();
        HashMap<String, ArrayList<ArrayList<String>>> map = new HashMap<>();
        queue.add(start);
        map.put(start, new ArrayList<ArrayList<String>>());
        while ( !queue.isEmpty()) {
            String n = queue.poll();
            if (n.equals(dest)) {
                return map.get(n);
            }
            ArrayList<Graph.Edge<String, String>> edges = Marvel.getEdges(Marvel.getAllNodes().get(n));
            Collections.sort(edges, (s1,s2) -> s1.getDesNode().getName().equals(s2.getDesNode().getName())
                        ? s1.getLabel().compareTo(s2.getLabel()) : s1.getDesNode().getName().compareTo(s2.getDesNode().getName()));
            for ( Graph.Edge<String, String> edge : edges) {
                String temp = edge.getDesNode().getName();
                if (!map.containsKey(temp)) {
                    ArrayList<String> temPath = new ArrayList<>();
                    temPath.add(n);
                    temPath.add(temp);
                    temPath.add(edge.getLabel());
                    ArrayList<ArrayList<String>> path1 = map.get(n);
                    ArrayList<ArrayList<String>> path2 = new ArrayList<>();
                    for (ArrayList<String> array : path1) {
                        path2.add(array);
                    }
                    path2.add(temPath);
                    map.put(temp, path2);
                    queue.add(temp);
                }
            }
        }
        return new ArrayList<>();
    }
    public static void main(String[] args) {
        Graph<String, String> graph = MarvelPaths.marvelGraph("csStudent.csv");
        Scanner console = new Scanner(System.in);
        boolean userInputCorrect = false;
        String start = null;
        String dest = null;
        int userContinue = 0;
        do {
            do {
                System.out.print("Start Character: ");
                start = console.next();
                if (!graph.getAllNodes().containsKey(start)) {
                    System.out.println("Unknown Character: " + start);
                    userInputCorrect = true;
                } else {
                    userInputCorrect = false;
                }
            } while (userInputCorrect);
            do {
                System.out.print("Dest Character: ");
                dest = console.next();
                if (!graph.getAllNodes().containsKey(dest)) {
                    System.out.println("Unknown Character: " + dest);
                    userInputCorrect = true;
                } else {
                    userInputCorrect = false;
                }
            } while (userInputCorrect);
            ArrayList<ArrayList<String>> result = shortestPath(graph, start, dest);
            if (result.isEmpty()) {
                System.out.println("There is no path from " + start + " to " + dest);
                System.out.print("Do you want to continue finding path? Please enter 1 for YES or 0 for NO:" );
                userContinue = console.nextInt();
            } else {
                System.out.println("Path from " + start + " to " + dest + ":" + result);
                System.out.print("Do you want to continue finding path? Please enter 1 for YES or 0 for NO:" );
                userContinue = console.nextInt();
            }
        } while (userContinue == 1);
    }
}
