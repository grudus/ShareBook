import React, { Component } from 'react';
import PropTypes from 'prop-types';
import * as GroupApi from '../group/GroupApi'


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
        await GroupApi.addUserToGroup(this.props.groupId, this.state.email);
        this.setState({ email: '' });
    };

    render() {
        const { email } = this.state;

        return (
            <form onSubmit={this.addUser}>
                <label>Add user:
                    <input value={email} onChange={this.onChange}/>
                </label>
                <button type="submit">Dodaj</button>
            </form>
        );
    }
}


export default AddUserToGroup;
