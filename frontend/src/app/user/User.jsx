import React, { Component } from 'react';
import { getCurrentUser } from "./UserApi";


class User extends Component {

    state = { user: {} };

    async componentDidMount() {
        const user = await getCurrentUser();
        this.setState({ user })
    }

    render() {
        return (<div>Hello, {this.state.user.email}</div>);
    }
}


export default User;
