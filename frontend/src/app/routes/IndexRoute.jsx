import React from 'react';
import { Route } from "react-router-dom";
import Login from "../auth/login/Login";
import Register from "../auth/register/Register";
import Group from "../group/Group";
import User from "../user/User";


const IndexRoute = () => (
    <>
        <Route exact path="/" component={User}/>
        <Route exact path="/groups" component={Group}/>
        <Route exact path="/auth/login" component={Login} />
        <Route exact path="/auth/register" component={Register} />
    </>
);


export default IndexRoute;
