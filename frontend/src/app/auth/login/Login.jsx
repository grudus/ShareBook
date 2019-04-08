import { Button, Checkbox, FormControlLabel, Typography } from "@material-ui/core";
import Card from "@material-ui/core/Card";
import TextField from "@material-ui/core/TextField";
import React, { Component } from 'react';
import { Link, withRouter } from "react-router-dom";
import { saveCurrentUser } from "../../storage/userStorage";
import { login } from "../AuthApi";
import css from './login.module.scss';
import img from '../res/loginimg.png'


class Login extends Component {

    state = {
        email: '',
        password: '',
        rememberMe: false,
    };

    handleChange = (field) => (event) => {
        if (field === 'rememberMe')
            this.setState(state => ({ ...state, rememberMe: !state.rememberMe }));
        else
            this.setState({ [field]: event.target.value });
    };

    submitForm = async (e) => {
        e.preventDefault();
        try {
            await login(this.state.email, this.state.password, this.state.rememberMe);
            this.props.history.push("/groups");
        } catch (e) {
            alert("Invalid credentials")
        }

    };

    render() {
        return (
            <div>
                <Card className={css.loginCard}>
                    <img src={img} alt="Img" className={css.img}/>
                    <div className={css.loginWrapper}>
                        <div className={css.titleWrapper}>
                            <h2 className={css.title}>ShareBook</h2>
                            <Typography component="h5" variant="h5" align="center">
                                Welcome back. Please login to your account
                            </Typography>
                        </div>
                        <form noValidate autoComplete="on" className={css.form} onSubmit={this.submitForm}>
                            <TextField
                                required
                                label="email"
                                value={this.state.email}
                                onChange={this.handleChange('email')}
                                margin="normal"
                            />

                            <TextField
                                required
                                label="password"
                                value={this.state.password}
                                type="password"
                                onChange={this.handleChange('password')}
                                margin="normal"
                            />
                            <div className={css.rememberWrapper}>
                                <FormControlLabel control={
                                    <Checkbox value='true' onChange={this.handleChange('rememberMe')}/>
                                } label="Remember me"/>
                                <Link to={"#"}>
                                    Forgot password
                                </Link>
                            </div>
                            <div className={css.buttonWrapper}>
                                <Button variant="contained"
                                        color="primary"
                                        className={css.button}
                                        type="submit">
                                    Login
                                </Button>
                                <Button variant="outlined" className={css.button}>
                                    <Link to="/auth/register">
                                        Sign up
                                    </Link>
                                </Button>
                            </div>
                        </form>
                    </div>
                </Card>
            </div>
        );
    }

}


export default withRouter(Login);
