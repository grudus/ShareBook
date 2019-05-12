import Link from "@material-ui/core/Link";
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
    };

    render() {
        const { notification } = this.props;

        return (
            <article>
                <a href={notification.linkHref}>
                    <h6>{notification.title}</h6>
                    <p>{notification.text}</p>
                </a>
            </article>
        );
    }
}


export default SingleNotification;
