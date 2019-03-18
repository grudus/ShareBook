import Card from "@material-ui/core/Card";
import css from './register.module.scss';
import TextField from "@material-ui/core/TextField";
import img from "../res/loginimg.png";
import { Button, Typography } from "@material-ui/core";
import { Link } from "react-router-dom";
import React, { Component } from "react";
import { register } from "../AuthApi";


class Register extends Component {

    state = {
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: '',
        conditions: false,
    };

    handleChange = (field) => (event) => {
        this.setState({ [field]: event.target.value });
    };

    submitForm = async (e) => {
        e.preventDefault();
        try {
            await register(this.state);
            alert("Success")
        } catch (e) {
            alert("Invalid credentials")
        }
    };

    render() {
        return (
            <div>
                <Card className={css.registerCard}>
                    <img src={img} alt="Img" className={css.img}/>
                    <div className={css.registerWrapper}>
                        <div className={css.titleWrapper}>
                            <h2 className={css.title}>ShareBook</h2>
                            <Typography component="h5" variant="h5" align="center">
                                Welcome, Please sign up your account.
                            </Typography>
                        </div>
                        <form noValidate autoComplete="on" className={css.form} onSubmit={this.submitForm}>
                            <div className={css.fullName}>
                                <div className={css.fullName1}>
                                    <TextField
                                        required
                                        label="First Name"
                                        value={this.state.firstName}
                                        onChange={this.handleChange('firstName')}
                                        margin="normal"
                                    />
                                </div>
                                <div className={css.fullName2}>
                                    <TextField
                                        required
                                        label="Last Name"
                                        value={this.state.lastName}
                                        onChange={this.handleChange('lastName')}
                                        margin="normal"

                                    />
                                </div>
                            </div>
                            <TextField
                                required
                                label="Email"
                                value={this.state.email}
                                onChange={this.handleChange('email')}
                                margin="normal"
                            />
                            <TextField
                                required
                                label="Password"
                                value={this.state.password}
                                onChange={this.handleChange('password')}
                                type="password"
                                margin="normal"
                            />
                            <TextField
                                required
                                label="Confirm Password"
                                value={this.state.confirmPassword}
                                onChange={this.handleChange('confirmPassword')}
                                type="password"
                                margin="normal"
                            />
                            <div className={css.conditionsWrapper}>
                                <input type="checkbox" className={css.checkbox}/>
                                <label>
                                    I agree with terms and conditions
                                </label>
                            </div>
                            <div className={css.buttonWrapper}>
                                <Button variant="contained"
                                        color="primary"
                                        className={css.button}
                                        type="submit">
                                    Sign up
                                </Button>
                            </div>
                            <div className={css.signIn}>
                                <Link to="/auth/login">
                                    Already have an account? Sign in.
                                </Link>
                            </div>
                        </form>
                    </div>
                </Card>
            </div>
        );
    }
}

export default Register;
