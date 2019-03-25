import Card from "@material-ui/core/Card";
import css from './register.module.scss';
import TextField from "@material-ui/core/TextField";
import img from "../res/loginimg.png";
import { Button, Typography } from "@material-ui/core";
import { Link, withRouter } from "react-router-dom";
import React, { Component } from "react";
import { register } from "../AuthApi";

const formValid = ({ formErrors, ...rest }) => {
    let valid = true;

    Object.values(formErrors).forEach(val => {
        val && val.length > 0 && (valid = false);
    });

    Object.values(rest).forEach(val => {
        val == null && (valid = false);
    });

    return valid;
};


const emailRegex = RegExp(/^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[A-Za-z]+$/);

class Register extends Component {

    constructor(props) {
        super(props);

        this.state = {
            firstName: null,
            lastName: null,
            email: null,
            password: null,
            confirmPassword: null,
            conditions: false,
            formErrors: {
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                confirmPassword: "",
                conditions: false,
            }
        };
    }

    passwordMatches = (confirm) =>
        this.state.password === confirm;

    handleChange2 = () => (event) => {
        event.preventDefault();
        const { name, value } = event.target;
        let formErrors = { ...this.state.formErrors };


        switch (name) {
            case "firstName":
                formErrors.firstName = value.length < 1 ? "First name is required" : "";
                break;
            case "lastName":
                formErrors.lastName = value.length < 1 ? "Last name is required" : "";
                break;
            case "email":
                formErrors.email = emailRegex.test(value) ? "" : "Invalid email address";
                break;
            case "password":
                formErrors.password = value.length < 6 && value.length > 0 ? "Minimum 6 characters required" : "";
                break;
            case "confirmPassword":
                formErrors.confirmPassword = !this.passwordMatches(value) ? "Hasła nie są tożsame" : "";
                break;
            default:
                break;
        }
        this.setState({ formErrors, [name]: value }, () => console.log(this.state));
    };

    submitForm = async (e) => {
        e.preventDefault();
        await register(this.state);
        this.props.history.push("/auth/login")
    };

    render() {
        const { formErrors } = this.state;
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
                                        className={formErrors.firstName.length > 0 ? "error" : null}
                                        label="First Name"
                                        type="text"
                                        name="firstName"
                                        noValidate
                                        onChange={this.handleChange2('firstName')}
                                    />
                                    {formErrors.firstName.length > 0 && (
                                        <span className={css.error}>{formErrors.firstName}</span>
                                    )}
                                </div>
                                <div className={css.fullName2}>
                                    <TextField
                                        required
                                        className={formErrors.lastName.length > 0 ? "error" : null}
                                        label="Last Name"
                                        type="text"
                                        name="lastName"
                                        noValidate
                                        onChange={this.handleChange2('lastName')}
                                    />
                                    {formErrors.lastName.length > 0 && (
                                        <span className={css.error}>{formErrors.lastName}</span>
                                    )}
                                </div>
                            </div>
                            <TextField
                                required
                                className={formErrors.email.length > 0 ? "error" : null}
                                label="Email"
                                type="email"
                                name="email"
                                noValidate
                                onChange={this.handleChange2('email')}
                            />
                            {formErrors.email.length > 0 && (
                                <span className={css.error}>{formErrors.email}</span>
                            )}
                            <TextField
                                required
                                className={formErrors.password.length > 6 ? "error" : null}
                                label="Password"
                                name="password"
                                noValidate
                                onChange={this.handleChange2('password')}
                                type="password"
                                margin="normal"
                            />
                            {formErrors.password.length > 0 && (
                                <span className={css.error}>{formErrors.password}</span>
                            )}
                            <TextField
                                required
                                label="Confirm Password"
                                className={formErrors.passwordMatches = false ? "error" : null}
                                onChange={this.handleChange2('confirmPassword')}
                                type="password"
                                noValidate
                                name="confirmPassword"
                                margin="normal"
                            />
                            {formErrors.confirmPassword.length > 0 && (
                                <span className={css.error}>{formErrors.confirmPassword}</span>
                            )}
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

export default withRouter(Register);
