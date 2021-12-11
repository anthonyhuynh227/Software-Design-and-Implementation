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

import React, {Component} from 'react';
import ButtonControl from "./ButtonControl";
import MapView from "./MapView";

import "./App.css";

interface AppState {
    pathStart: string; // beginning of the path to display
    pathEnd: string; // the end of the pay to display
    path: [any, any, any, any][]; // the path to display
    listBuilding: string; // list of buildings in the map.
}

class App extends Component<{}, AppState> {

    constructor(props: {}) {
        super(props);
        this.state = {
            pathStart: '',
            pathEnd: '',
            path: [],
            listBuilding: ""
        };
    }

    updateStartPath = (newStart: string) => {
        this.setState({
            pathStart: newStart
        });
    }

    updateEndPath = (newEnd: string) => {
        this.setState({
            pathEnd: newEnd
        });
    }

    // Gets the path from the start building to end building so that it can be displayed.
    findPath = async () => {
        try {
            // get path the server with pathStart and pathEnd.
            let url = 'http://localhost:4567/find-path?start='+this.state.pathStart+'&end='+this.state.pathEnd;
            let responsePromise = fetch(url);
            let response = await responsePromise;
            // Alert user with invalid input.
            if (!response.ok) {
                alert("INVALID INPUT: "+"\n "+ "LocationName must be abbreviation and capitalized. " +
                    "\n " +" AND names must be on the List of Building" +"\n" +"Click List of Buildings button for more information. " + response.status);
                return;
            }
            let parseObject = await response.json();

            if (parseObject['cost'] === 0) {
                alert("No path found");
            } else {
                // get the actual path field from parseObject
                let path = parseObject['path'];

                // get the start field from parseObject
                let startPath = parseObject['start'];

                let endPath = startPath;
                let tempPath: [any, any, any, any][] = [];
                // let connection = " to "

                for (let i = 0; i < path.length; i++) {
                    let segment = path[i];
                    let start = segment['start'];
                    let end = segment['end'];

                    endPath = end;

                    tempPath.push([start['x'], start['y'], end['x'], end['y']]);
                }

                this.setState({
                    path: tempPath,
                });
            }
        } catch (e) {
            alert('There was an error contacting the server');
            console.log(e);
        }
    }

    handleClear = () => {
        this.setState({
            path: [],
            pathStart: "",
            pathEnd: "",
        });
    }

    // Get the list of buildings in the campus map with their
    // shortName followed by their longName.
    findListBuilding = async () => {
        try {
            let url = 'http://localhost:4567/list-buildings';
            let responsePromise = fetch(url);
            let response = await responsePromise;
            if (!response.ok) {
                alert("Error ---> we are not Ok :(" + response.status);
                return;
            }
            let textPromise = await response.json();

            // Get the array of list buildings.
            let text: string[] = textPromise;
            let result: string = "";
            // Fo through the array and add elements in array to listBuilding.
            for (let line of textPromise) {
                result = result + line + ' \n'
            }
            this.setState({
                listBuilding: result
            });
            alert(this.state.listBuilding);
        } catch (e) {
            alert('There was an error contacting the server');
            console.log(e);
        }
    }

    render() {
        return (
            <div>
                <p id="app-title">UW Campus Map</p>
                <ButtonControl path={this.state.path} onDraw={this.findPath} pathStart={this.state.pathStart} onClear={this.handleClear}
                               pathEnd={this.state.pathEnd} onStartChange={this.updateStartPath}
                               onEndChange={this.updateEndPath} onList={this.findListBuilding} />
                <MapView path={this.state.path} />
            </div>

        );
    }

}

export default App;
