import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import RegistrationForm from './RegistrationForm';
import RegistrationFormMaterial from './RegistrationFormMaterial'
import MessengerMaterial from './MessengerMaterial'

class App extends Component {
  render() {
    return (
      <div className="App">
        {/*<header className="App-header">*/}
          {/*<img src={logo} className="App-logo" alt="logo" />*/}
          {/*<h1 className="App-title">Welcome to React</h1>*/}
        {/*</header>*/}
        {/*<p className="App-intro">*/}
          {/*Test Form*/}
        {/*</p>*/}
        {/*<RegistrationForm/>*/}
        {/*<RegistrationFormMaterial/>*/}
        <MessengerMaterial/>

      </div>

    );
  }
}

export default App;
