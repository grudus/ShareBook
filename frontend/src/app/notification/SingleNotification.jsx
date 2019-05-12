import * as PropTypes from "prop-types";
import React, { Component } from 'react';


class SingleNotification extends Component {
    static propTypes = {
        notification: PropTypes.shape({
            title: PropTypes.string,
            text: PropTypes.string,
            linkHref: PropTypes.string,
            visited: PropTypes.bool,
            createdAt: PropTypes.string,
        }).isRequired,
        onClick: PropTypes.func.isRequired,
    };

    render() {
        const { notification, onClick } = this.props;

        return (
            <article onClick={onClick}>
                <h6>{notification.title}</h6>
                <p>{notification.text}</p>
            </article>
        );
    }
}


export default SingleNotification;
