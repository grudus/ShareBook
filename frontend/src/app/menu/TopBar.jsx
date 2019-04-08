import * as PropTypes from "prop-types";
import React, { Component } from 'react';
import LetterAvatar from "../group/LetterAvatar";
import { getCurrentUser } from "../user/UserApi";
import css from "./menu.module.scss";
import MenuList from "./UserDropdownMenu";
import pathsWithoutTopBar from "./pathsWithoutTopBar";


const shouldRender = () => !pathsWithoutTopBar.includes(document.location.pathname);

class TopBar extends Component {
    state = {
        user: null
    };

    async componentDidMount() {
        const user = await getCurrentUser();
        this.setState({ user });
    }

    render() {
        let { children } = this.props;
        const { user } = this.state;

        return (
            user && shouldRender()
                ? (
                    <div>
                        <nav className={css.topBar}>
                            <label className={css.title} width="100">Sharebook</label>
                            <div className={css.rightPart}>
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


export default TopBar;
