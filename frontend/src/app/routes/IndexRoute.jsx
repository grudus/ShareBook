import React from 'react';
import { Route } from "react-router-dom";
import Login from "../auth/Login";
import User from "../user/User";


const IndexRoute = () => (
    <>
        <Route exact path="/" component={User}/>
        <Route exact path="/auth/login" component={Login} />
    </>
);


export default IndexRoute;
