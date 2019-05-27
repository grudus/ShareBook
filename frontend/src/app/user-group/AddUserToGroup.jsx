import React, { Component } from 'react';
import PropTypes from 'prop-types';
import * as GroupApi from '../group/GroupApi'
import css from './AddUserToGroup.scss'
import TextField from "@material-ui/core/TextField";
import { Button } from "@material-ui/core";


class AddUserToGroup extends Component {
    static propTypes = {
        groupId: PropTypes.number.isRequired,
    };

    state = {
        email: '',
        formErrors: {
            email:''
        }

    };

    addUser = async (e) => {
        e.preventDefault();
        try {
            await GroupApi.addUserToGroup(this.props.groupId, this.state.email);
        } catch (err) {
            const errors = err.data.codes;
            console.log("Error from server:", errors)
        }
        this.setState({ email: '' });
    };



    handleChange = () => (e) => {
        e.preventDefault();
        this.setState({ email: e.target.value })
        const { errors, value } = e.target;
        const formErrors = { ...this.state.formErrors };

        switch (errors) {
            case "email":
                formErrors.email="INVALID_EMAIL" ? "Nieprawidłowy format": "";
                break;
            case "email":
                formErrors.email="USER_ALREADY_EXISTS_IN_GROUP" ? "Użytkownik należy już do tej grupy": "";
                break;
            case "email":
                formErrors.email="USER_DOES_NOT_EXISTS" ? "Użytkownik nie istnieje": "";
                break;
            case "email":
                formErrors.email="UNAUTHORIZED" ? "Brak wymaganych uprawnień": "";
                break;
            default:
                break;
        }
        this.setState({ formErrors, [errors]: value }, () => console.log(this.state));
    };

    render() {
        const { email } = this.state;
        const { formErrors } = this.state;
        return (
            <div className={css.addUsers}>
            <form onSubmit={this.addUser}>
                <TextField
                    label="Email użytkownika"
                    value={email}
                    onChange={this.handleChange('email')}
                    /><br/><br/>
                {formErrors.email.length > 0 && (
                    <span className={css.error}>{formErrors.email}</span>
                )}
                <Button variant="outlined" className={css.addButton} color="primary" type="submit" >Dodaj</Button>
            </form>
            </div>

        );
    }
}


export default AddUserToGroup;
