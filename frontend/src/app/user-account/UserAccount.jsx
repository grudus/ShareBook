import css from './user-account.scss';
import {withRouter} from "react-router-dom";
import React, { Component } from "react";
import {getCurrentUser} from "../user/UserApi";
import LetterAvatar from "../group/LetterAvatar";
import Card from "@material-ui/core/Card";
import TextField from "@material-ui/core/TextField";


class UserAccount extends Component {

    state = { user: {} };

    async componentDidMount() {
        const user = await getCurrentUser();
        this.setState({ user })
    }

    render(){
        const { user } = this.state;
        return(
            <Card className="accountWrapper">
            <div className="avatar">
                <LetterAvatar text={user.firstName + user.lastName}/>
            <h2>Witaj, {this.state.user.firstName}!</h2>
            </div>
                <div className="date">
                <div>
                    <label>Imię: </label>
                    <strong>{this.state.user.firstName}</strong>
                </div>
                <div>
                    <label>Nazwisko: </label>
                    <strong>{this.state.user.lastName}</strong>
                </div>
                <div>
                    <label>Email: </label>
                    <strong>email</strong>
                </div>
                <div>
                    <label>Hasło: </label>
                    <strong>password</strong>
                </div>
            </div>
            </Card>
        )}

}

export default withRouter(UserAccount);