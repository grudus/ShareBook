import * as PropTypes from "prop-types";
import React, { Component } from 'react';
import css from './notifications.module.scss'

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
            <div className={css.singleNotification}>
            <article onClick={onClick}>
                <h5 className={css.notificationLabel}>{notification.title}</h5>
                <p className={css.notificationText}>{notification.text}</p>
            </article>
            </div>
        );
    }
}


export default SingleNotification;
