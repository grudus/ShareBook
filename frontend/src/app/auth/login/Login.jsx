import { Button } from "@material-ui/core";
import Card from "@material-ui/core/Card";
import TextField from "@material-ui/core/TextField";
import React, { Component } from 'react';
import { login } from "../AuthApi";
import css from './login.module.scss';
import img from '../res/loginimg.png'


class Login extends Component {

    state = {
        email: '',
        password: '',
    };

    handleChange = (field) => (event) => {
        this.setState({ [field]: event.target.value });
    };

    submitForm = (e) => {
        e.preventDefault();
        login(this.state.email, this.state.password)
    };

    render() {
        return (
            <div className={css.pageWrapper}>
                <Card className={css.loginWrapper}>
                    <img src={img} alt="Img" className={css.img} />
                    <form noValidate autoComplete="on" className={css.form} onSubmit={this.submitForm}>
                        <TextField
                            label="email"
                            value={this.state.email}
                            onChange={this.handleChange('email')}
                            margin="normal"
                        />

                        <TextField
                            label="password"
                            value={this.state.password}
                            type="password"
                            onChange={this.handleChange('password')}
                            margin="normal"
                        />
                        <Button variant="contained"
                                color="primary"
                                className={css.button}
                                type="submit">
                            Login
                        </Button>
                    </form>
                </Card>
            </div>
        );
    }

}


export default Login;
