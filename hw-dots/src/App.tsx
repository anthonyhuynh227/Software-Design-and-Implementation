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
import EdgeList from "./EdgeList";
import Grid from "./Grid";
import GridSizePicker from "./GridSizePicker";

// Allows us to write CSS styles inside App.css, any any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    gridSize: number;  // size of the grid to display
    edgeString: string;
    edges: [number, number, number, number,string][]
}

class App extends Component<{}, AppState> { // <- {} means no props.

    constructor(props: any) {
        super(props);
        this.state = {
            gridSize: 4,
            edgeString: "",
            edges: []
        };
    }

    updateGridSize = (newSize: number) => {
        this.setState({
            gridSize: newSize
        });
    };

    updateEdgeString = (newEdge: string) => {
        const arrayLine = newEdge.split('\n');
        let map:[number, number, number, number, string][] = []
        for (let line of arrayLine) {
            let a:any[] = []
            const part = line.split(' ');
            for (let element of part) {
                const e = element.split(',');
                a.push(e);
            }
            map.push([parseInt(a[0]),parseInt(a[1]),parseInt(a[2]), parseInt(a[3]),a[4]]);
        }
        this.setState({
            edgeString: newEdge,
            edges:map
        });
    };


    render() {
        const canvas_size = 500;
        return (
            <div>
                <p id="app-title">Connect the Dots!</p>
                <GridSizePicker value={this.state.gridSize.toString()} onChange={this.updateGridSize}/>
                <Grid edges={this.state.edges} size={this.state.gridSize} width={canvas_size} height={canvas_size}/>
                <EdgeList value={this.state.edgeString}  onChange={this.updateEdgeString}/>
            </div>

        );
    }

}

export default App;
