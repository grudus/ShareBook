import css from './user-account.module.scss';
import {withRouter} from "react-router-dom";
import React, { Component } from "react";
import {getCurrentUser} from "../user/UserApi";
import LetterAvatar from "../group/LetterAvatar";
import Card from "@material-ui/core/Card";
import TextField from "@material-ui/core/TextField";
import { Button } from "@material-ui/core";


class UserAccount extends Component {

    state = { user: {} };

    async componentDidMount() {
        const user = await getCurrentUser();
        this.setState({ user })
    }

    render(){
        const { user } = this.state;

        if (!user || !user.firstName) {
            return <div/>
        }


        return(
            <Card className={css.accountWrapper}>
                <div className={css.account}>
            <div className={css.avatar}>
                <LetterAvatar className={css.letterAvatar} text={user.firstName[0] + user.lastName[0]}/>
            <h2>Witaj, {this.state.user.firstName}!</h2>
            </div>
                <div className={css.date}>
                    <h3>Twoje dane: </h3>
                <div className={css.firstName}>
                    <label>Imię: </label>
                    <strong>{this.state.user.firstName}</strong>
                </div>
                <div className={css.lastName}>
                    <label>Nazwisko: </label>
                    <strong>{this.state.user.lastName}</strong>
                </div>
                <div className={css.email}>
                    <label>Email: </label>
                    <strong>{this.state.user.email}</strong>
                </div><br/><br/>
                    <Button variant="outlined" color="primary" type="submit" >Edytuj dane</Button>
            </div>
                </div>
            </Card>
        )}

}

export default withRouter(UserAccount);