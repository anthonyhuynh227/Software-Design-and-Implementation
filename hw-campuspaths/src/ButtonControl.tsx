

import React, {Component} from 'react';

interface ButtonControlProps {
    path: any[];
    pathStart: string;
    pathEnd: string;
    onStartChange(newStart: string): void;
    onEndChange(newEnd: string): void;
    onDraw(): any,
    onList(): any,
    onClear(): any
}

class ButtonControl extends Component<ButtonControlProps> {

    onInputStartChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        const newStart:string = event.target.value;
        this.props.onStartChange(newStart);
    }

    onInputEndChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        const newEnd:string = event.target.value;
        this.props.onEndChange(newEnd);
    }

    render() {
        return (
            <div id="button-control">
                Start Location <br/>
                <textarea
                    value={this.props.pathStart}
                    onChange={this.onInputStartChange}
                /> <br/>
                Destination <br/>
                <textarea
                    value={this.props.pathEnd}
                    onChange={this.onInputEndChange}
                /> <br/>
                <button onClick={ () => this.props.onDraw()}>Find Path</button>
                <button onClick={() => this.props.onClear()}>Clear</button>
                <button onClick={() => this.props.onList()}>List of Buildings</button>
                <button onClick={() => alert('To find a path between two building, type in the name ' +
                    'of start building and destination building by their shortName. '
                    +"\n" +'You can find short name in List of Buildings button')}>User Guide</button>
            </div>
        )
    }

}

export default ButtonControl;