import Snackbar from "@material-ui/core/Snackbar";
import css from './user-account.module.scss';
import { withRouter } from "react-router-dom";
import React, { Component } from "react";
import { getCurrentUser } from "../user/UserApi";
import LetterAvatar from "../group/LetterAvatar";
import Card from "@material-ui/core/Card";
import TextField from "@material-ui/core/TextField";
import { Button } from "@material-ui/core";
import { updateUser } from "../user/UserApi";


class UserAccount extends Component {

    state = { user: null, showMessage: false };

    async componentDidMount() {
        const user = await getCurrentUser();
        this.setState({ user })
    }

    submitForm = async (e) => {
        e.preventDefault();
        try {
            await updateUser(this.state.user);
            this.setState({showMessage: true})
        } catch (e) {
            alert("Invalid credentials")
        }

    };

    updateFirstName = (event) => {
        const newUsername = event.target.value;
        this.setState(state => ({
            ...state,
            user: {
                ...state.user,
                firstName: newUsername,
            }
        }))
    }

    updateLastName = (event) => {
        const newUsername = event.target.value;
        this.setState(state => ({
            ...state,
            user: {
                ...state.user,
                lastName: newUsername,
            }
        }))
    }

    updateEmail = (event) => {
        const newUsername = event.target.value;
        this.setState(state => ({
            ...state,
            user: {
                ...state.user,
                firstName: newUsername,
            }
        }))
    }

    goToChartsPage = (event) => {
        this.props.history.push("/activity");
    };

    render() {
        const { user } = this.state;

        if (!user) {
            return <div/>
        }


        return (
            <Card className={css.accountWrapper}>
                <div className={css.account}>
                    <div className={css.avatar}>
                        <LetterAvatar className={css.letterAvatar} text={user.firstName[0] + user.lastName[0]}/>
                        <h2>Witaj, {this.state.user.firstName}!</h2>
                    </div>
                    <div className={css.date}>
                        <h3>Twoje dane: </h3>
                        <div className={css.fieldWrapper}>
                            <div className={css.label}>
                                <label>Imię: </label>
                            </div>
                            <TextField
                                id="outlined-name"
                                className={css.textField}
                                value={this.state.user.firstName}
                                onChange={this.updateFirstName}
                                margin="normal"
                                type="text"
                                variant="outlined"
                            />
                        </div>
                        <div className={css.fieldWrapper}>
                            <div className={css.label}>
                                <label>Nazwisko: </label>
                            </div>
                            <TextField
                                id="outlined-name"
                                className={css.textField}
                                value={this.state.user.lastName}
                                onChange={this.updateLastName}
                                margin="normal"
                                type="text"
                                variant="outlined"
                            />
                        </div>
                        <div className={css.fieldWrapper}>
                            <div className={css.label}>
                                <label>Email: </label>
                            </div>
                            <TextField
                                id="outlined-name"
                                className={css.textField}
                                value={this.state.user.email}
                                onChange={this.updateEmail}
                                margin="normal"
                                type="text"
                                variant="outlined"
                            />
                        </div>
                        <br/><br/>
                        <div className={css.edit}>
                        <Button variant="outlined" color="primary" onClick={this.submitForm} type="submit">Edytuj
                            dane</Button>
                        </div>
                        <div className={css.charts}>
                            <Button variant="outlined" color="primary" type="submit" onClick={this.goToChartsPage}>Twoja aktywność</Button>
                        </div>
                    </div>
                </div>


                <Snackbar
                    anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
                    open={this.state.showMessage}
                    onClose={() => this.setState({showMessage: false})}
                    message={<span id="message-id">Twoje dane zostały zapisane!</span>}
                    autoHideDuration={2000}
                />

            </Card>
        )
    }

}

export default withRouter(UserAccount);
