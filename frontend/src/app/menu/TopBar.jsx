import React from 'react';
import LetterAvatar from "../group/LetterAvatar";
import css from "./menu.module.scss";
import MenuList from "../group/Menu";


const TopBar = () => (
    <div className={css.topBar}>
        <label className={css.title} width="100">Sharebook</label>
        <MenuList/>
        <LetterAvatar name="A"/>
    </div>
);


export default TopBar;
