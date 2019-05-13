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
        email: ''
    };

    onChange = (e) => {
        this.setState({ email: e.target.value })
    };

    addUser = async (e) => {
        e.preventDefault();
        try {
            const response = await GroupApi.addUserToGroup(this.props.groupId, this.state.email);
            console.log(response)
        } catch (e) {
            console.log("Błąd", e)
        }
        this.setState({ email: '' });
    };

    render() {
        const { email } = this.state;

        return (
            <div className={css.addUsers}>
            <form onSubmit={this.addUser}>
                <TextField label="Email użytkownika" value={email} onChange={this.onChange} /><br/><br/>
                <Button variant="outlined" className={css.addButton} color="primary" type="submit">Dodaj</Button>
            </form>
            </div>

        );
    }
}


export default AddUserToGroup;
