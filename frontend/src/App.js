import React, { Component } from 'react';
import { Route } from "react-router-dom";
import './App.css';
import Login from "./app/auth/login/Login";
import Register from "./app/auth/register/Register";
import logo from './logo.svg';

class App extends Component {
    render() {
        return (
            <div className="App">

                <Route path="/auth/login" exact component={Login}/>
                <Route path="/auth/register" exact component={Register}/>

                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <p>
                        Edit <code>src/App.js</code> and save to reload.
                    </p>
                    <a
                        className="App-link"
                        href="https://reactjs.org"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        Learn React
                    </a>
                </header>
            </div>
        );
    }
}

export default App;
