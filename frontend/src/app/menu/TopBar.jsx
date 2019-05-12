import * as PropTypes from "prop-types";
import React, { Component } from 'react';
import { Link, withRouter } from "react-router-dom";
import LetterAvatar from "../group/LetterAvatar";
import Notifications from "../notification/Notifications";
import { getCurrentUser } from "../user/UserApi";
import css from "./menu.module.scss";
import MenuList from "./UserDropdownMenu";
import pathsWithoutTopBar from "./pathsWithoutTopBar";


class TopBar extends Component {

    state = {
        user: null,
        shouldRender: false
    };

    async componentDidMount() {
        await this.onRouteChanged(document.location.pathname)
    }

    async componentDidUpdate(prevProps) {
        if (this.props.location !== prevProps.location) {
            await this.onRouteChanged(this.props.location.pathname);
        }
    }

    async onRouteChanged(location) {
        const prevShouldRender = this.state.shouldRender;
        const shouldRenderNow = !pathsWithoutTopBar.includes(location);

        if (!prevShouldRender && shouldRenderNow)
            await this.updateCurrentUser();

        this.setState({ shouldRender: shouldRenderNow });
    }


    async updateCurrentUser() {
        const user = await getCurrentUser();
        this.setState({ user });
    }

    render() {
        let { children } = this.props;
        const { user, shouldRender } = this.state;

        return (
            user && shouldRender
                ? (
                    <div>
                        <nav className={css.topBar}>
                            <Link to="/">
                                <h2 className={css.title}>Sharebook</h2>
                            </Link>
                            <div className={css.rightPart}>
                                <div className={css.notificationButton}>
                                <Notifications></Notifications>
                                </div>
                                <MenuList userName={user.firstName + " " + user.lastName}/>
                                <LetterAvatar text={user.firstName[0] + user.lastName[0]}/>
                            </div>
                        </nav>
                        <div className={css.children}>
                            {children}
                        </div>
                    </div>
                )
                :
                <>
                    {children}
                </>
        );
    }
}

TopBar.propTypes = { children: PropTypes.any }


export default withRouter(TopBar);
