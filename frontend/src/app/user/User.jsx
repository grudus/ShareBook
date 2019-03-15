import React, { Component } from 'react';
import { getCurrentUser } from "./UserApi";


class User extends Component {

    state = { user: {} };

    componentDidMount() {
        getCurrentUser()
            .then(({data}) => {
                this.setState({ user: data })
            })

    }

    render() {
        return (<div>Hello, {this.state.user.email}</div>);
    }
}


export default User;
