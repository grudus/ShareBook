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
            <div>
            <form onSubmit={this.addUser}>
                <div>
                <label>Dodaj użytkownika do grupy:</label>
                </div>
                <div>
                    <input value={email} onChange={this.onChange}/>
                <button type="submit">Dodaj</button>
                </div>
            </form>
            </div>
        );
    }
}


export default AddUserToGroup;
