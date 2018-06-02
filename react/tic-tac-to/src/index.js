import ReactDOM from 'react-dom';
import React from 'react';
import Game from './board-game.js';
import AjaxTest from './AjaxTest';
import './style/index.css';

var ajaxTest = new AjaxTest();
ajaxTest.getIpData();

ReactDOM.render(
    <Game/>,
    document.getElementById('root')
);
