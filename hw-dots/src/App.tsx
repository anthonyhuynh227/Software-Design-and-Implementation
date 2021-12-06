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
import {ifError} from "assert";

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
        this.setState({
            edgeString: newEdge
        });
    };

    handleDraw = (newEdge: string ) => {
        let arrayLine:any[] = newEdge.split('\n');
        let map:[number, number, number, number, string][] = []
        let clear: boolean = true;
        let message:string = "";
        let countLine: number = 0;
        for (let line of arrayLine) {
            countLine = countLine+1;
            let a:any[] = []
            let part:any[] = line.split(' ');
            if (part.length > 3 ) {
                clear = false;
                message = message + 'Line '+countLine+': Extra portion of a line, or extra space.' + '\n';
            } else if (part.length < 3) {
                clear = false;
                message = message + 'Line '+countLine+': Missing portion of a line \n';
            }
            for (let element of part) {
                const e:any[] = element.split(',');
                for (let k of e) {
                    a.push(k);
                }
                for (let i = 0; i < a.length -1; i++) {
                    if (a[i] > this.state.gridSize) {
                        clear = false;
                        message = message + 'Line '+countLine+': Grid size must be at least' +a[i]+'.' + '\n';
                    }
                }
            }
            map.push([parseInt(a[0]),parseInt(a[1]),parseInt(a[2]), parseInt(a[3]),a[4]]);
        }
        if (!clear) {
            alert(message);
        } else {
            this.setState({
                edges:map
        });}
    };

    render() {
        const canvas_size = 500;
        return (
            <div>
                <p id="app-title">Connect the Dots!</p>
                <GridSizePicker value={this.state.gridSize.toString()} onChange={this.updateGridSize}/>
                <Grid edges={this.state.edges} size={this.state.gridSize} width={canvas_size} height={canvas_size}/>
                <EdgeList value={this.state.edgeString}  onChange={this.updateEdgeString} onDraw={this.handleDraw}/>
            </div>

        );
    }

}

export default App;
