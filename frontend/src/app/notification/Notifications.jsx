import { IconButton } from "@material-ui/core";
import Badge from "@material-ui/core/Badge";
import ClickAwayListener from "@material-ui/core/ClickAwayListener";
import Grow from "@material-ui/core/Grow";
import Paper from "@material-ui/core/Paper";
import Popper from "@material-ui/core/Popper";
import NotificationIcon from "@material-ui/icons/Notifications";
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import { connectToWebsocket } from "../websocket/websocketClient";
import SingleNotification from "./SingleNotification";
import css from './notifications.module.scss'
import * as NotificationsApi from './NotificationsApi'


class Notifications extends Component {
    state = {
        listShown: false,
        freshNotificationsCount: 3,
        notifications: [{ id: 1, title: "Dupa", text: "Dupa2", linkHref: "https://google.com" }],
    };

    componentDidMount() {
        console.log("Downloading notifications");
        this.downloadNotifications();

        connectToWebsocket("/user/queue/notifications", (message) => {
            this.downloadNotifications();
        });
    }

    showNotificationsList = () => {
        this.setState({ listShown: true });
    };

    closeNotificationsList = () => {
        this.setState({ listShown: false });
    };

    downloadNotifications = async () => {
        const notifications = await NotificationsApi.getNotifications();

        const freshNotificationsCount = notifications.filter(n => !n.visited)
            .length;

        this.setState({ notifications, freshNotificationsCount })
    };

    seeNotification = async (notification) => {
        await NotificationsApi.seeNotification(notification.id);
        if (notification.linkHref.includes(document.location.origin)) {
            const link = notification.linkHref.replace(document.location.origin, "");

            if (!notification.visited)
                this.setState(state => ({ freshNotificationsCount: state.freshNotificationsCount - 1 }));
            this.props.history.push(link);
        } else
            document.location.href = notification.linkHref;
    };

    displayNotifications = () => {
        return (<ul className={css.notificationList}>
            {this.state.notifications.map(notification => (
                <li className={css.singleNotificaton} key={notification.id}>
                    <SingleNotification
                        onClick={() => this.seeNotification(notification)}
                        notification={notification}
                    />
                </li>
            ))}
        </ul>)
    };


    render() {
        const { listShown, freshNotificationsCount } = this.state;

        return (
            <>
                <IconButton
                    onClick={() => this.showNotificationsList()}
                    buttonRef={node => {
                        this.anchorEl = node;
                    }}>
                    <Badge color="secondary" badgeContent={freshNotificationsCount}>
                        <NotificationIcon className={css.notificationButton} color="action"/>
                    </Badge>
                </IconButton>

                <Popper open={listShown} anchorEl={this.anchorEl} transition disablePortal>
                    {({ TransitionProps, placement }) => (
                        <Grow
                            {...TransitionProps}
                            id="menu-list-grow"
                            style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
                        >
                            <Paper>
                                <ClickAwayListener onClickAway={this.closeNotificationsList}>
                                    {this.displayNotifications()}
                                </ClickAwayListener>
                            </Paper>
                        </Grow>
                    )}
                </Popper>

            </>

        );
    }
}


export default withRouter(Notifications);
