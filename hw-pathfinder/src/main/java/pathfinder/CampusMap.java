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

package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;
import pathfinder.textInterface.DijkstraAlgo;

import java.util.*;

/**
 * This represents a campus map with building and points and labeled edges that
 * connects them in this map. Point can represent building coordinate.
 */

public class CampusMap implements ModelAPI {
    // AF(this) =
    // each Node in graph is the Point in CampusMap and each edge is the label edge between two Points.
    // each key in listBuilding is the shortName of building and each value is its coordinate in the CampusMap.
    // each key in buildingNames is the shortName and each value is its longName.
    // shortestPath between two buildings in CampusMap => findShortTestPath(start, des)

    // Rep Invariant:
    // graph != null
    // && listBuilding != null
    // && buildingName != null

    /**
     * A HashMap contains all buildings in this CampusMap as key and value is their coordinate.
     */
    private HashMap<String, Point> listBuilding;

    /**
     * A HashMap contains shortName and longName of each building in CampusMap.
     */
    private HashMap<String, String> buildingNames;

    /**
     * A Graph to represent entire CampusMap with Node is the Point and Edge is the distance between Point.
     */
    private Graph<Point, Double> graph;

    /**
    * Constructs a new map with the data from campus_paths.csv and campus_buildings.csv
    * */
    public CampusMap() {
        List<CampusPath> pathList = CampusPathsParser.parseCampusPaths("campus_paths.csv");
        Graph<Point, Double> graph = new Graph<>();
        HashSet<Point> points = new HashSet<>();
        for (int i = 0; i < pathList.size(); i++) {
            Point start = new Point(pathList.get(i).getX1(),pathList.get(i).getY1());
            Point end = new Point(pathList.get(i).getX2(),pathList.get(i).getY2());
            if (!points.contains(start)) {
                points.add(start);
                Graph.Node<Point, Double> startNode = new Graph.Node<>(start);
                graph.addNode(startNode);
            }
            if (!points.contains(end)) {
                points.add(end);
                Graph.Node<Point, Double> endNode = new Graph.Node<>(end);
                graph.addNode(endNode);
            }
            graph.addEdge(graph.getAllNodes().get(start),graph.getAllNodes().get(end),pathList.get(i).getDistance());
        }
        this.graph = graph;
        List<CampusBuilding> buildingList = CampusPathsParser.parseCampusBuildings("campus_buildings.csv");
        HashMap<String, String> names = new HashMap<>();
        HashMap<String, Point> nameAndPoint = new HashMap<>();
        for (int i = 0; i < buildingList.size(); i++) {
            names.put(buildingList.get(i).getShortName(),buildingList.get(i).getLongName());
            nameAndPoint.put(buildingList.get(i).getShortName(), new Point(buildingList.get(i).getX(),buildingList.get(i).getY()));
        }
        this.buildingNames = names;
        this.listBuilding = nameAndPoint;
    }

    @Override
    public boolean shortNameExists(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        if (buildingNames.containsKey(shortName)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String longNameForShort(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        if (!shortNameExists(shortName)) {
            throw new IllegalArgumentException();
        } else {
            return buildingNames.get(shortName);
        }
    }

    @Override
    public Map<String, String> buildingNames() {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        return buildingNames;
    }

    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        if (startShortName == null || endShortName == null || !listBuilding.containsKey(startShortName) || !listBuilding.containsKey(endShortName)) {
            throw new IllegalArgumentException();
        }
        Point start = listBuilding.get(startShortName);
        Point des = listBuilding.get(endShortName);
        Path<Point> result = DijkstraAlgo.shortestPath(graph, start, des);
        return result;
    }

}
