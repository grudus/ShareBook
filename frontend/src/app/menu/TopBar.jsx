import React from 'react';
import LetterAvatar from "../group/LetterAvatar";
import css from "./menu.module.scss";
import MenuList from "./UserDropdownMenu";
import pathsWithoutTopBar from "./pathsWithoutTopBar";


const shouldRender = () => !pathsWithoutTopBar.includes(document.location.pathname);

const TopBar = ({ children }) => (
    shouldRender()
        ? (
            <div>
                <nav className={css.topBar}>
                    <label className={css.title} width="100">Sharebook</label>
                    <div className={css.rightPart}>
                        <MenuList/>
                        <LetterAvatar name="A"/>
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


export default TopBar;
