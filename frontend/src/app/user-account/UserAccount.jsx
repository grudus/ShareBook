import css from './user-account.scss';
import {withRouter} from "react-router-dom";
import React, { Component } from "react";


class UserAccount extends Component {

    render(){
        return(
            <div className={css.hello}>
            <h2>Hello USER</h2>
            </div>
        )}

}

export default withRouter(UserAccount);