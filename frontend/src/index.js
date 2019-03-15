import { MuiThemeProviderOld } from "@material-ui/core/es/styles/MuiThemeProvider";
import React from 'react';
import ReactDOM from 'react-dom';
import BrowserRouter from "react-router-dom/es/BrowserRouter";
import IndexRoute from "./app/routes/IndexRoute";
import './index.css';
import * as serviceWorker from './serviceWorker';
import createMuiTheme from './app/theme/createMuiTheme'

const theme = createMuiTheme();


ReactDOM.render(
    <BrowserRouter>
        <MuiThemeProviderOld theme={theme}>
            <IndexRoute/>
        </MuiThemeProviderOld>
    </BrowserRouter>,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
