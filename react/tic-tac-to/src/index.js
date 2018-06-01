import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';

class Square extends React.Component {

    constructor(props){
        super(props);
        // this.state = {value:null};

    }
    render() {
        return (
            React.createElement(
                // 'button', {className:'square', onClick:()=>this.setState({value:'X'})}, this.state.value
                'button', { className:'square', onClick:()=>this.props.onClick() }, this.props.value
            )
        //     <button className="square">
        //         {/* TODO */}
        //     </button>
        );
    }
}

class Board extends React.Component {
    counter = 0;
    constructor(props){
        super(props);
        this.state = {squares:Array(9).fill(null)}
    }

    render() {
        const status = "Next player: X";
        return (
            React.createElement('div',
                React.createElement('div', {className:'status'}, status),
                React.createElement('div', {className:'board-row'}, this.renderSquare(0), this.renderSquare(1), this.renderSquare(2)),
                React.createElement('div', {className:'board-row'}, this.renderSquare(3), this.renderSquare(4), this.renderSquare(5)),
                React.createElement('div', {className:'board-row'}, this.renderSquare(6), this.renderSquare(7), this.renderSquare(8))
            )
        );
    }

    renderSquare(i) {
        return <Square
            value={this.state.squares[i]}
            onClick={()=>this.handleClick(i)}
        />;
    }

    handleClick(i){
        const squares = this.state.squares.slice();
        squares[i] = (this.counter++%2==0)?'O':'X';
        this.setState({squares: squares});
    }

}

class Game extends React.Component {
    render() {
        var ajx = new AjaxTest();
        ajx.getIpData();
        return (
            React.createElement('div', {className:'game'},
                React.createElement('div', {className:'game-board'}, <Board />),
                React.createElement('div', {className:'game-board'},
                    React.createElement('div'),
                    React.createElement('ol')
                )
            )
        );
    }
}

class AjaxTest {
    getIpData(){
        fetch("https://ipapi.co/5.53.63.200/json/")
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                    // alert(result);
                    // this.setData({d:result.region});
                },
                (error) => {
                    alert("error");
                }
            )
    }
}

// ========================================

ReactDOM.render(
    <Game/>,
    document.getElementById('root')
);
