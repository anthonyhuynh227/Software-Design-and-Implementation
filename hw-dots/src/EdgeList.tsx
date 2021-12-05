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

import React, {Component, MouseEventHandler} from 'react';

interface EdgeListProps {
    value:string;
    onChange(edges: string): void;  // called when a new edge list is ready
                                 // once you decide how you want to communicate the edges to the App, you should
                                 // change the type of edges so it isn't `any`
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps> {

    onInputChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        const newEdge:string = event.target.value;
     this.props.onChange(newEdge);
    };


    handleClear = () => {
        this.props.onChange("");
    }


    render() {
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    value={this.props.value}
                    onChange={this.onInputChange}
                    rows={5}
                    cols={30}
                /> <br/>
                <button onClick={ () => this.props.onChange(this.props.value)}>Draw</button>
                <button onClick={this.handleClear}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
