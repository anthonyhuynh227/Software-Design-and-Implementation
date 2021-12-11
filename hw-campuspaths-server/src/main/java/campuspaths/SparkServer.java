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

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        // TODO: Create all the Spark Java routes you need here.
        // find a path between the given intem(?start=...&&end=...), if there is one.
        CampusMap campusMap = new CampusMap();
        Spark.get("find-path", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String start = request.queryParams("start");
                String end = request.queryParams("end");
                if (start == null || end == null) {
                    Spark.halt(400, "must have start or end");
                }
                if (!campusMap.shortNameExists(start) || !campusMap.shortNameExists(end)) {
                    Spark.halt(400, "must have valid start shortName or end shortName");
                }
                // Here, the Path object has a whole bunch of internal
                // files that contain extra info about the path.
                Path<Point> path = campusMap.findShortestPath(start,end);
                Gson gson = new Gson();
                return gson.toJson(path);
            }
        });

        // get list of buildings with their shortName followed by their longName.
        Spark.get("list-buildings", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                Map<String, String> listBuilding = campusMap.buildingNames();
                ArrayList<String> result = new ArrayList<>();
                // Converts Map to ArrayList of buildings.
                // Each element in ArrayList is shortName followed by longName.
                for (Map.Entry<String, String> set : listBuilding.entrySet()) {
                    StringBuilder listName = new StringBuilder();
                    listName.append(set.getKey());
                    listName.append(": ");
                    listName.append(set.getValue());
                    String line = listName.toString();
                    result.add(line);
                }
                // return listName as an array.
                Gson gson = new Gson();
                return gson.toJson(result);
            }
        });
    }

}
